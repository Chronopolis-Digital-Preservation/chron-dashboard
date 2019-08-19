/*
 * Formatting and conversion services.
 *
 * Turn on debugging by setting DEBUG in app.js
 */
"use strict";

app.factory('formatting', function () {
    var formatting = {};

    /*
     * Description: add commas to a number and returns a string.
     *
     * Code derived from:
     *    http://stackoverflow.com/questions/2901102/how-to-print-a-number-
     *    with-commas-as-thousands-separators-in-javascript#2901298
     *
     * Input: none.
     */
    formatting.addCommas = function (number) {
        /*
         * Assigning the regex to a variable "compiles" the expression, improving
         * performance
         */
        var pattern = /\B(?=(\d{3})+(?!\d))/g;
        var parts;

        // if there's no number, return an empty string
        if (!number) {
            return "";
        } // if

        /*
         * Convert number to a string, and then break it into two parts: before
         * and after the decimal point.
         */
        parts = number.toString().split(".");

        // add commas to the first part
        parts[0] = parts[0].replace(pattern, ",");

        // rejoin the integer and fractional parts
        return parts.join(".");
    } // addCommas()

    /*
     * Description: convert raw bytes into human-readable units with a size
     *    abbreviation.
     *
     * Input:
     *    bytes - raw bytes to convert
     *    decimals - number of digits to
     */
    formatting.bytesToUnits = function (bytes, decimals) {

        var sizesIndex;
        var sizes = ["Bytes", "KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"];

        if (bytes == "") {
            return "";
        }
        if (bytes == 0) {
            return "0 Bytes";
        } // if

        sizesIndex = Math.floor(Math.log(bytes) / Math.log(1024));
        // make sure the index is in a valid range
        if (sizesIndex > sizes.length - 1) {
            return "OutOfRange";
        } // if
        return (bytes / Math.pow(1024, sizesIndex)).toFixed(decimals) + " " + sizes[sizesIndex];
    } // bytesToUnits()

    return formatting;
});