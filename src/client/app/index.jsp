<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- viewport set to for desired rendering and touch zooming -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- to ensure the best rendering mode for IE -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Chronopolis Dashboard</title>

    <!-- favicon display -->
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

    <!-- bootstrap compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- bootstrap theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <!-- local CSS -->
    <link rel="stylesheet" href="css/chrondash.css">
</head>

<body ng-app="chrondashApp">
<!-- nsf-logo is only for NCAR/NSF header. -->
<div class="pageContainer">
    <div class="pageHeaderImage hidden-xs">
        <div class="container">
            <div class="nsf-logo">
                <img src="images/nsf.png" width="300" height="60">
            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#!/home">Chronopolis Dashboard</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar" ng-controller="headerController">
            <ul class="nav navbar-nav">
                <li ng-class="{active: isActive('/home')}"><a href="#!/home">Home</a></li>
                <li ng-class="{active: isActive('/aceSummary')}"><a href="#!/aceSummary">Summary</a></li>
                <li ng-class="{active: isActive('/aceDepositor?environment=production')}"><a
                        href="#!/aceDepositor?environment=production">Chron Depositors</a></li>
                <li ng-class="{active: isActive('/aceDepositor?environment=test')}"><a
                        href="#!/aceDepositor?environment=test">Test Depositors</a></li>
                <li ng-class="{active: isActive('/dpnDepositor')}"><a href="#!/dpnDepositor">DPN Depositors</a></li>
                <li ng-class="{active: isActive('/ingestStatus?environment=production')}"><a
                        href="#!/ingestStatus?environment=production">Ingest</a></li>
                <li ng-class="{active: isActive('/ingestStatus?environment=test')}"><a
                        href="#!/ingestStatus?environment=test">Test Ingest</a></li>
                <li ng-class="{active: isActive('/about')}"><a href="#!/about">About</a></li>
            </ul>
        </div>
    </div>
</nav>

<!--
    Bootstrap container for the partial page view

    ng-cloak hides the Angular html template while the application is loading,
    preventing flicker by the browser.
-->
<div class="container" ng-cloak>
    <!-- load the partial view for the current route into the layout template -->
    <div ng-view></div>
</div>

<!-- orgFooter is only for NCAR/NSF footer. -->
<div class="container">
<div id="orgFooter">
</div>
</div>

<!-- load AngularJS, followed by its dependent modules via CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-sanitize.js"></script>

<!-- load jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- load bootstrap's compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- load the AngularJS application files, starting with the app definition -->
<script src="js/app.js"></script>

<!-- load the controllers and services -->
<script src="controllers/about.js"></script>
<script src="controllers/header.js"></script>
<script src="controllers/home.js"></script>
<script src="controllers/ingestStatus.js"></script>
<script src="controllers/aceDepositor.js"></script>
<script src="controllers/dpnDepositor.js"></script>
<script src="controllers/aceSummary.js"></script>
<script src="js/alertMessage.js"></script>
<!-- orgnav.js is only for NCAR/NSF header/footer.  Remove to change branding. -->
<script src="js/orgnav.js"></script>
<script src="services/database.js"></script>
<script src="services/formatting.js"></script>
<script src="services/navigation.js"></script>

<!-- inject values needed from the server environment -->
<script>
    /*
     * The base URL for server API calls. This allows the application to
     * handle different application contexts. For testing, this can be set
     * to "/api/v1/".
     */
    var urlBase = "${pageContext.request.contextPath}/api/v1/";
</script>

</body>
</html>