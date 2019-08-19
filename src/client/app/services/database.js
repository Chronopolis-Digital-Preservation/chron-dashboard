/*
 * Database data services.
 *
 * Input:
 *    urlBase - the base URL for server API calls. This variable is retrieved
 *        from the server in index.jsp and placed in global scope.
 *
 * Turn on debugging by setting DEBUG in app.js
 */
"use strict";

app.factory('database', ['$http', '$interpolate', function ($http, $interpolate) {
    var database = {};
    //var urlBase = "http://localhost/chrondash";         // base URL for scripts

    database.getDepositors = function(node) {

        console.log ("Database.getDepositors node="  + node);

        // The $interpolate returns a function.
        var expression = $interpolate(urlBase + "node/{{node}}/ace/depositor.json");

        // Pass the subtitution values into the expression function to get the proper output.
        var url = expression({ node: node});

        // The above code could also be written like the following:
        // var url = $interpolate(urlBase + "node/{{node}}/ace/depositor.json")({ node: node});

        // Please see the following links for more information on $interpolate:
        // https://docs.angularjs.org/api/ng/service/$interpolate
        // http://stackoverflow.com/questions/31230596/is-it-possible-to-use-parameterized-url-templates-with-angular-http-service
        // http://excellencenodejsblog.com/angularjs-compile-parse-interpolate/

        var request = {
            method: "GET",
            url: url
        };

        return $http(request);
    };


    /*
     * Descrip: get a list of all servers per environment.
     *
     * Input: production, test or dpn.
     */
    database.getServerList = function (environment) {
        var url = urlBase + "getNodeList.json";
        var request = {
            method: "GET",
            url: url,
            params: {environment: environment}
        };

        return $http(request);
    }; // getServers()

    /*
     * Descrip: get a list of all servers per environment.
     *
     * Input: production, test or dpn.
     */
    database.getIngestServerList = function (environment) {
        var url = urlBase + "getIngestNodeList.json";
        var request = {
            method: "GET",
            url: url,
            params: {environment: environment}
        };

        return $http(request);
    }; // getServers()


    /*
     * Descrip: get a list of all servers per environment.
     *
     * Input: production, test or dpn.
     */
    database.getAllServers = function () {
        var url = urlBase + "getAllNodes.json";
        var request = {
            method: "GET",
            url: url,
            params: {}
        };

        return $http(request);
    }; // getServers()

    /*
     * Descrip: get the statuses for a list of servers.
     *
     * Input:
     *    servers - an array of server objects
     */
    database.getServerStatus = function (servers) {
        var url = urlBase + "getNodeOnlineStatus.json";
        var request = {
            method: "POST",
            url: url,
            data: servers
        };

        return $http(request);
    }; // getServerStatus()


    /*
     * Descrip: get the storage info for a list of servers.
     *
     * Input:
     *    servers - an array of server objects
     */
    database.getAceSummary = function (servers) {
        var url = urlBase + "getAceBagSummary.json";
        var request = {
            method: "POST",
            url: url,
            data: servers
        };

        return $http(request);
    }; // getAcSummary()

    /*
     * Descrip: get the storage details for a server node and bag status.
     *
     * Input:
     *    node - node name for a server
     *    status - bag status
     */
    database.getIngestStatus = function (node, status, environment) {
        var url = urlBase + "getIngestBagSummary.json";

        var request = {
            method: "POST",
            url: url,
            data: {node: node, status: status},
            params: {environment: environment}
        };

        return $http(request);
    }; // getIngestStatus()

    /*
     * Descrip: get a list of all bag statuses.
     *
     * Input: none.
     */
    database.getBagStatusList = function (environment) {
        var url = urlBase + "getIngestBagStatusList.json";
        var request = {
            method: "GET",
            url: url,
            params: {environment: environment}
        };

        return $http(request);
    }; // getBagStatusList()

    /*
     * Descrip: get dashboard version information.
     *
     * Input: none.
     */
    database.getVersion = function () {
        var url = urlBase + "getVersion.json";
        var request = {
            method: "GET",
            url: url,
            params: {}
        };

        return $http(request);
    }; // getVersion()

    return database;
}]);