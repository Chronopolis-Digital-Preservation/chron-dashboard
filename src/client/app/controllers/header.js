/*
 * Turn on debugging by setting DEBUG in app.js
 */
'use strict';

app.controller('headerController', function ($scope, $location) {
    /*
     * Determine whether the current location contains a specified location.
     */
    $scope.isActive = function (viewLocation) {
        /*
         * Test whether the view's location is part of the current path. When
         * navigation takes place using the navigation bar, there will be an
         * exact match. But when the navigation takes place programmatically,
         * additional parameters may be appended to the path. Both cases
         * should return a truthy value.
         */
       // return $location.path().indexOf(viewLocation) >= 0;

        var active = (viewLocation === $location.url());

       /* if (DEBUG) {
            console.log(" headerController: location=" + JSON.stringify($location));
            console.log(" headerController: viewLocation=" + viewLocation);
            console.log(" headerController: $location.path()=" + $location.path());
            console.log(" headerController: $location.url()=" + $location.url());
            console.log("headerController active=" + active );
        }
        */
        return active;
    };
});