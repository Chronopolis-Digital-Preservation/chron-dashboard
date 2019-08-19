/*
 * Turn on debugging by setting DEBUG in app.js
 */
'use strict';

app.controller('ingestStatusController', function ($scope, $routeParams, database, navigation, formatting) {
    var servers;// list of servers from database

    // TODO: dynamic
    var defaultServer = "UCSD";     // default server dropdown option
    var defaultTestServer = "TEST-UCSD";  // This doesn't currently exist

    var defaultBagStatus = "ACTIVE";   // default bag status dropdown option

    // production, test, or dpn
    var environment = $routeParams.environment;
    $scope.environment = environment;

    $scope.loading;
    $scope.serverSelectionList;     // list of server nodes for the dropdown list
    $scope.bagStatusSelectionList;  // list of all bag statuses for the drop down list
    $scope.selectedServer;          // server node selected in the dropdown list
    $scope.selectedBagStatus;       // bag status selected in the dropdown list
    $scope.ingestStatus;
    $scope.status = "";
    /*
     * Indicates whether there has been a failure getting the storage details.
     * This flag is used by the alertMessage directive to determine whether or
     * not to display the alert.
     */
    $scope.showAlertMessage = false;

    getServers()
        // wait until the servers list has been obtained
        .then(function (response) {
            // return the promise to support sequential chaining
            return getBagStatusList();
        })
        // wait until the bag status list has been obtained
        .then(function (response) {
            // create a new array with all elements that pass the test in the function
            $scope.selectedBagStatus = $scope.bagStatusSelectionList.filter(function findBagStatusFilter(bagStatus) {
                // set the default bag status
                return bagStatus.status === defaultBagStatus;
            })[0];

            // $routeParams lets you retrieve the current set of route parameters
            setNodeSelection(servers, $routeParams.node);


            getIngestStatus($scope.selectedServer, $scope.selectedBagStatus, $scope.environment);
        });

    /*
     * Descrip: change the server URL, effectively reloading the page for a
     *    a new server. This function is called when a new server node is
     *    selected.
     */
    $scope.changeServer = function () {
        var server = $scope.selectedServer;

        if (DEBUG) {
            console.log("ingestStatusController: changeServer():" + JSON.stringify($scope.selectedServer));
        } // if

        navigation.goToDetails(server.node);
    } // changeServer()

    /*
     * Descrip: change the bag status. This function is called when a new bag status is
     *    selected.
     */
    $scope.changeBagStatus = function () {

        if (DEBUG) {
            console.log("ingestStatusController: changeBagStatus():" + JSON.stringify($scope.selectedBagStatus));
        } // if

        getIngestStatus($scope.selectedServer, $scope.selectedBagStatus);
    } // changeBagStatus()

    /*
     * Get a list of all servers.
     */
    function getServers() {
        if (DEBUG) {
            console.log("ingestStatusController: getServers()");
            console.log("testIngestStatusController: environment from routeParams =" + environment);
            console.log("testIngestStatusController: scope.environment=" + $scope.environment);
        } // if



        var promise = database.getIngestServerList($scope.environment)
            .then(function (response) {
                if (DEBUG) {
                    console.log("ingestStatusController: getServers(): data=" + JSON.stringify(response.data));
                } // if
                servers = response.data;
                $scope.serverSelectionList = response.data;
                /*
                 * Show a failure message if there's no data
                 */
                $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
            })
            .catch(function (response) {
                $scope.showAlertMessage = true;
                $scope.status = 'Unable to load server status: ' + response.data.message;
            });
        return promise;
    } // getServers()

    /*
     * Get a list of all bag statuses.
     */
    function getBagStatusList() {
        if (DEBUG) {
            console.log("ingestStatusController: getBagStatusList() environment=" + $scope.environment);
        } // if

        var promise = database.getBagStatusList($scope.environment)
            .then(function (response) {
                if (DEBUG) {
                    console.log("ingestStatusController: getBagStatusList(): data=" + JSON.stringify(response.data));
                } // if
                $scope.bagStatusSelectionList = response.data;
            })
            .catch(function (response) {
                $scope.status = 'Unable to load bag status: ' + response.data.message;
            });
        return promise;
    } // getBagStatusList()

    /*
     * Get the storage details for the selected server.
     */
    function getIngestStatus(server, bagStatus) {
        $scope.loading = true;

        if (DEBUG) {
            console.log("ingestStatusController: getIngestStatus(): server=" + JSON.stringify(server) +
                " bagStatus=" + JSON.stringify(bagStatus) + " environment=" + $scope.environment + " loading=" + $scope.loading);
        } // if

        $scope.showAlertMessage = false;       // turn off failure flag

        database.getIngestStatus(server.node, bagStatus.status, $scope.environment)
            .then(function (response) {
                if (DEBUG) {
                     console.log("ingestStatusController: getIngestStatus(): data=" + JSON.stringify(response.data));
                }

                // cache the length for speed
                for (var i = 0, len = response.data.length; i < len; i++) {
                    response.data[i].bytes = formatting.bytesToUnits(response.data[i].bytes, 2);
                    response.data[i].files = formatting.addCommas(response.data[i].files);
                } // for
                $scope.ingestStatus = response.data;
                /*
                 * Show a failure message if there's no data
                 */
                $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
            })
            .catch(function (response) {
                $scope.showAlertMessage = true;
                $scope.status = 'Unable to load server details: ' + response.message;
            }).finally(function () {
                $scope.loading = false;
            });
    } // getIngestStatus()

    /*
     * Create the server node selection list and select the option in the dropdown
     * list.
     */
    function setNodeSelection(servers, selectedNode) {
        if (DEBUG) {
            console.log("ingestStatusController: setNodeSelection(): servers=" + servers);
            console.log("ingestStatusController: setNodeSelection(): selectedNode=" + selectedNode);

            for (var i = 0, len = servers.length; i < len; i++) {
                console.log("ingestStatusController: setNodeSelection(): server.node=" + servers[i].node);

            }
        } // if

        $scope.serverSelectionList = servers;

        $scope.selectedServer = $scope.serverSelectionList.filter(function findServerFilter(server) {
            if (selectedNode) {

                console.log("ingestStatusController: setNodeSelection() got selectedNode:" + selectedNode);
                return server.node === selectedNode;
            } else {
                /*
                 * This case occurs when the Storage Details tab is selected.
                 */

                if ($scope.environment == "test") {
                    console.log("ingestStatusController: setNodeSelection() using default node:" + JSON.stringify(defaultTestServer));
                    return server.node === defaultTestServer;
                }
                else {
                    console.log("ingestStatusController: setNodeSelection() using default node:" + JSON.stringify(defaultServer));
                    return server.node === defaultServer;
                }
            } // if
        })[0];

    } // setNodeSelection()

});