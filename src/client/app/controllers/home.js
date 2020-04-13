/*
 * Turn on debugging by setting DEBUG in app.js
 */
"use strict";

/*
 This behavior probably ought to go into a DIRECTIVE instead of a filter.
 */
app.filter('statusDisplay', function() {
    return function (status, ingestUrl) {

        var displayHtmlBlank = '';
       // var displayHtmlOnline = '<span class="glyphicon glyphicon-ok green"></span> <a href="' + ingestUrl + '" target="_blank">Online</a>';
       // var displayHtmlOffline = '<span class="glyphicon glyphicon-remove red"></span><a href="'+ ingestUrl + '" target="_blank">Offline</a>';

        // TODO: Note:  Removed link as it was not pointing to ingestUrl.  Try to figure out why later.
        var displayHtmlOnline = '<span class="badge badge-success">Online</span>';
        var displayHtmlOffline = '<span class="badge badge-danger">Offline</span>';

        if (status == null) {
            return displayHtmlBlank;
        } else if (status == 'true') {
            return displayHtmlOnline;
        } else if (status == 'false') {
            return displayHtmlOffline;
        }

    } // onlineIngestStatusDisplay()
});

app.directive

app.controller('homeController', function ($scope, database, formatting) {

    $scope.status = "";
    $scope.loading = [];
    $scope.prodServerStatus;
    $scope.testServerStatus;
    $scope.dpnServerStatus;

    $scope.formatting = formatting;

    /*
     * Indicates whether there has been a failure getting the storage details.
     * This flag is used by the alertMessage directive to determine whether or
     * not to display the alert.
     */
    $scope.showAlertMessage = false;

    getServers("production")
        // wait until the servers list has been obtained
        .then(function (response) {

            getServerStatus(response.data, "production")
                .then(function (response) {

                 /*   for (var i = 0, len = response.data.length; i < len; i++) {
                        var ingestDisplay = formatting.onlineIngestStatusDisplay(response.data[i].ingestStatus, response.data[i].ingestUrl);
                        console.log("HOME INGEST DISPLAY: " + ingestDisplay);

                    } // for
                    */

                    $scope.prodServerStatus = response.data;
                });
        });

    getServers("test")
        // wait until the servers list has been obtained
        .then(function (response) {
            getServerStatus(response.data, "test")
                .then(function (response) {
                    $scope.testServerStatus = response.data;
                });
        });

    getServers("dpn")
        // wait until the servers list has been obtained
        .then(function (response) {
            getServerStatus(response.data, "dpn")
                .then(function (response) {
                    $scope.dpnServerStatus = response.data;
                });
        });

    /*
     * Get a list of servers.
     */
    function getServers(environment) {
        if (DEBUG) {
            console.log("homeController: getServers()");
        } // if

        var promise = database.getServerList(environment);
        promise.then(function (response) {
            if (DEBUG) {
                console.log("homeController: getServers(): ---->>> data=" + JSON.stringify(response.data));
            } // if

            /*
             * Show a failure message if there's no data
             */
            $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
        }).catch(function (response) {
            $scope.showAlertMessage = true;
            $scope.status = 'Unable to load server status: ' + response.data.message;
        });
        return promise;
    } // getServers()

    /*
     * Get the status of all servers.
     */
    function getServerStatus(servers, environment) {
        $scope.loading[environment] = true;
        if (DEBUG) {
            console.log("homeController: getServerStatus(): servers=" + JSON.stringify(servers));
        } // if

        $scope.showAlertMessage = false;       // turn off failure flag

        var promise = database.getServerStatus(servers);
        promise.then(function (response) {
            if (DEBUG) {
                console.log("homeController: getServerStatus(): data=" + JSON.stringify(response.data));
            } // if
            /*
             * Show a failure message if there's no data
             */
            $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
        }).catch(function (response) {
            $scope.showAlertMessage = true;
            $scope.status = 'Unable to load server status: ' + response.data.message;
        }).finally (function () {
            $scope.loading[environment] = false;
        });
        return promise;
    } // getServerStatus()

    /*
     * Get a list of INGEST servers.
     */
    function getIngestServers(environment) {
        if (DEBUG) {
            console.log("homeController: getIngestServers()");
        } // if

        var promise = database.getIngestServerList(environment);
        promise.then(function (response) {
            if (DEBUG) {
                console.log("homeController: getIngestServers(): ---->>> data=" + JSON.stringify(response.data));
            } // if

            /*
             * Show a failure message if there's no data
             */
            $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
        }).catch(function (response) {
            $scope.showAlertMessage = true;
            $scope.status = 'Unable to load server status: ' + response.data.message;
        });
        return promise;
    } // getServers()

});