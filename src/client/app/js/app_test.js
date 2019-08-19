'use strict';

describe('app.js: Routing', function () {

    it('should map routes to controllers', function() {
        module('chrondashApp');

        inject(function($route) {

            expect($route.routes['/home'].controller).toBe('homeController');
            expect($route.routes['/home'].templateUrl).
                toEqual('views/home.html');

            expect($route.routes['/aceSummary'].controller).toBe('aceSummaryController');
            expect($route.routes['/aceSummary'].templateUrl).
                toEqual('views/aceSummary.html');

            expect($route.routes['/ingestStatus/:node'].controller).
                toEqual('ingestStatusController');
            expect($route.routes['/ingestStatus/:node'].templateUrl).
                toEqual('views/ingestStatus.html');

            // otherwise redirect to
            expect($route.routes[null].redirectTo).toEqual('/home')
        });
    });

});