/*
 * Navigation services.
 *
 * Turn on debugging by setting DEBUG in app.js
 */
"use strict";

app.factory('navigation', ['$location', function ($location) {
    var navigation = {};
    var ingestStatusUrl = "/ingestStatus";      // URL for storage details view

    /*
     * Change the URL to go to the storage details screen and pass along the
     * selected node.
     *
     * Input:
     *    storage - a storage object, used to determine the selected node
     */
    navigation.goToDetails = function (node) {
        if (DEBUG) {
            console.log("navigation: goToDetails(): node=" + JSON.stringify(node));
        } // if

        // change the path
        $location.path(ingestStatusUrl + "/" + node);
    }; // goToDetails()

    return navigation;
}]);