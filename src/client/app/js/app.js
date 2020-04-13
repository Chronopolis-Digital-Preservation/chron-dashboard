/*
 * Root module for the Chronopolis Dashboard application
 */
'use strict';

/*
 * Set DEBUG to true to turn on console debugging messages.
 */
var DEBUG = true;

// declare app-level module which depends on views and components
var app = angular.module('chrondashApp', [
    "ngRoute",               // route provider
    "ngSanitize"
]);


// set up routing
app.config(function ($routeProvider, $locationProvider) {

   // $locationProvider.hashPrefix("");  // This was changed in 1.6 from default "" to default to !  Put it back so URL routing works.
   /* $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });

    $locationProvider.hashPrefix('');
    */

    // routing for the home/status page
    $routeProvider.when('/home', {
        templateUrl: 'views/home.html',
        controller: 'homeController'
    });
    $routeProvider.when('/aceDepositor', {
        templateUrl: 'views/aceDepositor.html',
        controller: 'aceDepositorController'
    });

    // routing for the storage summary page
    $routeProvider.when('/aceSummary', {
        templateUrl: 'views/aceSummary.html',
        controller: 'aceSummaryController'
    });

    // routing for the storage details page
    $routeProvider.when('/ingestStatus', {
        templateUrl: 'views/ingestStatus.html',
        controller: 'ingestStatusController'
    });

    /*
     * Routing for the storage details page when clicking on
     * table row. The node is passed to the details page.
     */
    $routeProvider.when('/ingestStatus/:node', {
        templateUrl: 'views/ingestStatus.html',
        controller: 'ingestStatusController'
    });

    // routing for the about page
    $routeProvider.when('/about', {
        templateUrl: 'views/about.html',
        controller: 'aboutController'
    });
    $routeProvider.otherwise({redirectTo: '/home'});
});

// handle cross-domain (CORS) requests
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);
