/*
 * Turn on debugging by setting DEBUG in app.js
 */
'use strict';

/*
 * Directive to wrap an error message in a Bootstrap dismissible alert. The
 * directive requires that the variable showAlertMessage be declared in
 * $scope and set to true/false.
 *
 * Usage:
 *    <alert-message>message text and formatting</alert-message>
 */
app.directive('alertMessage', function() {
    var directive = {};

    directive.restrict = 'E';   // restrict this directive to HTML elements
    // process the HTML elements in the directive body
    directive.transclude = true;
    /*
     * The the ng-transclude attribute indicates the element in which the HTML
     * is to be inserted.
     */
    directive.template =
			'<div ng-transclude class="alert alert-warning alert-dismissible fade show" role="alert" ng-show="showAlertMessage">' +
				'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
					'<span aria-hidden="true">&times;</span>' +
				'</button>' +
			'</div>';

    return directive;
});