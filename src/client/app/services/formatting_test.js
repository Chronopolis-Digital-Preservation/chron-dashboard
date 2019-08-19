'use strict';

describe('formatting.js', function () {

    var formatting;         // service

    // excuted before each "it" is run.
    beforeEach(function () {

        // load the module.
        module('chrondashApp');

        // inject the service for testing
        inject(function (_formatting) {
            formatting = _formatting;
        });
    });

    describe('addCommas', function () {

        // check to see if it has the expected function
        it('should have an addCommas function', function () {
            expect(angular.isFunction(formatting.addCommas)).toBe(true);
        });

    });

});