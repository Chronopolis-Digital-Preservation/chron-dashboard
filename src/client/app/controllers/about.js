/*
 * Turn on debugging by setting DEBUG in app.js
 */
"use strict";

app.controller('aboutController', function ($scope, database) {
    $scope.version;
    $scope.serverStatus;
    $scope.status = "";
    /*
     * Indicates whether there has been a failure getting the ingest status.
     * This flag is used by the alertMessage directive to determine whether or
     * not to display the alert.
     */
    $scope.showAlertMessage = false;

    getVersion();

    /*
     * Get a list of all servers.
     */
    function getVersion() {
        if (DEBUG) {
            console.log("aboutController: getVersion()");
        } // if

        var promise = database.getVersion()
            .then(function onSuccess (response) {
                if (DEBUG) {
                    console.log("aboutController: getVersion(): data=" + JSON.stringify(response.data));
                } // if
                $scope.version = response.data;
                /*
                 * Show a failure message if there's no data
                 */
                $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
            }).catch(
            function onError (response) {
                $scope.showAlertMessage = true;
                $scope.status = 'Unable to load server status: ' + response.data.message;
            });
        return promise;
    } // getVersion()

});