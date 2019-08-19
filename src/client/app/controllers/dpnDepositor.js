/*
 * Turn on debugging by setting DEBUG in app.js
 */
"use strict";

app.controller('dpnDepositorController', function ($scope, database) {

    $scope.loading;
    $scope.depositors = [];

    /* TODO - dynamic */
    getDepositors('dpn');

    function getDepositors(node) {

        $scope.loading = true;
        var promise = database.getDepositors(node)
            .then(function onSuccess (response) {
                if (DEBUG) {
                    console.log("---dpnDepositor: getDepositors(): data=" + JSON.stringify(response.data.depositors));
                    $scope.addData(node, response.data.depositors);
                }
            })
            .catch(function onError (response) {
            })
            .finally(function () {
                $scope.loading = false;
            });
        return promise;
    }

    $scope.addData = function(node, depositors) {

        angular.forEach(depositors, function(value, key) {

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