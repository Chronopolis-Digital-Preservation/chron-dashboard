/*
 * Turn on debugging by setting DEBUG in app.js
 */
"use strict";

app.controller('aceDepositorController', function ($scope, $routeParams, database) {

    // production, test, or dpn
    var environment = $routeParams.environment;
    $scope.loading;
    $scope.environment = environment;

    $scope.depositors = [];
    $scope.servers;
    $scope.nodes = [];

    /* TODO - dynamic */
    // getDepositors('ncar');
    // getDepositors('ucsd');
    // getDepositors('umiacs');

    getServers(environment)
    // wait until the servers list has been obtained
        .then(function (response) {
            getAllDepositors($scope.servers);

        });


    /*
     * Get a list of all servers. (TODO: same as aceSummary.js)
     */
    function getServers(environment) {
        $scope.loading = true;
        if (DEBUG) {
            console.log("aceDepositor.js: getServers()");
        } // if

        var promise = database.getServerList(environment)
            .then(function onSuccess (response) {
                if (DEBUG) {
                    console.log("aceDepositor: getServers(): data=" + JSON.stringify(response.data));
                } // if
                $scope.servers = response.data;
                /*
                 * Show a failure message if there's no data
                 */
                $scope.showAlertMessage = (typeof response.data === 'undefined' || response.data.length === 0);
            })
            .catch(function onError (response) {
                $scope.showAlertMessage = true;
                $scope.status = 'Unable to load server status: ' + response.data.message;
            })
            .finally(function () {
                $scope.loading = false;
            });
        return promise;
    } // getServers()

    /*
     * Get the storage state for all servers.
     */
    function getAllDepositors(servers) {
        if (DEBUG) {
            console.log("aceDepositorController: getAllDepositors(): servers=" + JSON.stringify(servers));
        } // if

        $scope.showAlertMessage = false;       // turn off failure flag

        // Loop over servers and get Depositors per node
        for (var i = 0; i < servers.length; i++) {

            var server = servers[i];
            console.log("aceDepositor.js working on server: " + JSON.stringify(server));

            // TODO: better way?
            var node = server["node"];
            console.log("aceDepositor.js node: " + node);

            // Add to scope.nodes
            $scope.nodes.push(node);
            // $scope.addData(node, data.nodes);

            getDepositors(node);
        }


    } // getAllDepositors()


    function getDepositors(node) {

        var promise = database.getDepositors(node)
            .then(function (response) {
                if (DEBUG) {
                    console.log(">>>>aceDepositor: getDepositors(): node= " + node + ", data=" + JSON.stringify(response.data.depositors));
                }
                $scope.addDepositors(node, response.data.depositors);
            })
            .catch (function onError (response) {
            });
        return promise;
    }

    $scope.addDepositors = function(node, depositors) {

        angular.forEach(depositors, function(value, key) {

            if (DEBUG) {
                console.log("---node=" + node);
                console.log("---key=" + key);
                console.log("---value=" + JSON.stringify(value));
                console.log("---depositors=" + JSON.stringify(depositors));
            }

            var depositor = getDepositorFromDepositors(value.name);

            // TODO:  Not sure this is the correct way of making this check.
            // If the depositor is null, then create a new depositor and add it to the scoped depositors array.
            // How would uncle bob write this code?
            if (depositor === undefined) {

                depositor = {
                    name: value.name
                };
                $scope.depositors.push(depositor);
            }

            // Set/Update the node value on the depositor.
            // How would uncle bob write this code?
            depositor[node] = {};
            depositor[node].bags = value.bags;
            depositor[node].files = value.files;
            depositor[node].bytes = value.bytes;
        });
    }

    function getDepositorFromDepositors(depositorName) {

        var depositor;

        var index;
        for (index = 0; index < $scope.depositors.length; index++) {

            if ($scope.depositors[index].name === depositorName) {
                depositor = $scope.depositors[index];
                break;
            }
        }
        return depositor;
    }
});