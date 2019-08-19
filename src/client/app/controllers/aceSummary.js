/*
 * Turn on debugging by setting DEBUG in app.js
 */
'use strict';

app.controller('aceSummaryController', function ($scope, database, navigation, formatting) {

    $scope.loading = [];
    $scope.status = "";

    $scope.prodStorageStatus;
    $scope.testStorageStatus;
    $scope.dpnStorageStatus;

    /*
     * Indicates whether there has been a failure getting the storage details.
     * This flag is used by the alertMessage directive to determine whether or
     * not to display the alert.
     */
    $scope.showAlertMessage = false;

    getServers("production")
        // wait until the servers list has been obtained
        .then(function (response) {
            getAceSummary(response.data, "production")
                .then(function (response) {
                    $scope.prodStorageStatus = response.data;
                });
        });

    getServers("test")
        // wait until the servers list has been obtained
        .then(function (response) {
            getAceSummary(response.data, "test")
                .then(function (response) {
                    $scope.testStorageStatus = response.data;
                });
        });

    getServers("dpn")
        // wait until the servers list has been obtained
        .then(function (response) {
            getAceSummary(response.data, "dpn")
                .then(function (response) {
                    $scope.dpnStorageStatus = response.data;
                });
        });


    /*
     * Get a list of all servers.
     */
    function getServers(environment) {
        if (DEBUG) {
            console.log("aceSummaryController: getServers() environment = " + environment);
        } // if

        var promise = database.getServerList(environment);
        promise.then(function (response) {
            if (DEBUG) {
                console.log("aceSummaryController: getServers(): data=" + JSON.stringify(response.data) + ", environment=" + environment);
            } // if

            /*
             * Show a failure message if there's no data
             */
            $scope.showAlertMessage = (typeof data === 'undefined' || data.length === 0);
        })
        .catch(function onError (response) {
            $scope.showAlertMessage = true;
            $scope.status = 'Unable to load server status: ' + response.data.message;
        });
        return promise;
    } // getServers()

    /*
     * Get the storage state for all servers.
     */
    function getAceSummary(servers, environment) {
        $scope.loading[environment] = true;
        if (DEBUG) {
            console.log("aceSummaryController: getAceSummary(): servers=" + JSON.stringify(servers) + "environment=" + environment);
        } // if

        $scope.showAlertMessage = false;       // turn off failure flag

        var promise = database.getAceSummary(servers);
        promise.then(function onSuccess (response) {
            if (DEBUG) {
                console.log("aceSummaryController: getAceSummary(): data=" + JSON.stringify(response.data));
            } // if

            // cache the length for speed
            for (var i = 0, len = response.data.length; i < len; i++) {
                response.data[i].bytes = formatting.bytesToUnits(response.data[i].bytes, 2);
                response.data[i].bags = formatting.addCommas(response.data[i].bags);
                response.data[i].files = formatting.addCommas(response.data[i].files);
            } // for

            /*
             * Show a failure message if there's no data
             */
            $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
        })
        .catch(function onError (response) {
            $scope.showAlertMessage = true;
            $scope.status = 'Unable to load server summary status: ' + response.data.message;
        })
        .finally(function () {
            $scope.loading[environment] = false;
        });
        return promise;
    } // getAceSummary()

});