<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">		
		<link rel="stylesheet" href="css/chrondash.css">
		<title>Chronopolis Dashboard</title>
	</head>
	<body ng-app="chrondashApp">

		<!-- cd-header is for use by themes -->
		<header id="cd-header" class="container" ng-cloak></header>

		<nav id="cd-navigation" class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container">
				<a id="cd-navigation-brand" class="navbar-brand" href="#!/home">Chronopolis Dashboard</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav" ng-controller="headerController">
					<ul class="navbar-nav">
						<li class="nav-item" ng-class="{active: isActive('/home')}">
							<a class="nav-link" href="#!/home">Home</a>
						</li>
						<li class="nav-item" ng-class="{active: isActive('/aceSummary')}">
							<a class="nav-link" href="#!/aceSummary">Summary</a>
						</li>
						<li class="nav-item" ng-class="{active: isActive('/aceDepositor?environment=production')}">
							<a class="nav-link" href="#!/aceDepositor?environment=production">Chron Depositors</a>
						</li>
						<li class="nav-item" ng-class="{active: isActive('/aceDepositor?environment=test')}">
							<a class="nav-link" href="#!/aceDepositor?environment=test">Test Depositors</a>
						</li>
						<li class="nav-item" ng-class="{active: isActive('/ingestStatus?environment=production')}">
							<a class="nav-link" href="#!/ingestStatus?environment=production">Ingest</a>
						</li>
						<li class="nav-item" ng-class="{active: isActive('/ingestStatus?environment=test')}">
							<a class="nav-link" href="#!/ingestStatus?environment=test">Test Ingest</a>
						</li>
						<li class="nav-item" ng-class="{active: isActive('/about')}">
							<a class="nav-link" href="#!/about">About</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>

	<!--
			Bootstrap container for the partial page view
			   - ng-cloak hides the Angular html template while the application is loading,
			     preventing flicker by the browser.
	-->
	<!-- load the partial view for the current route into the layout template -->
	<main id="cd-main" class="container" ng-view></main>

	<!-- cd-footer is for use by themes -->
	<footer id="cd-footer" class="container" ng-cloak></footer>

	<!-- load AngularJS, followed by its dependent modules via CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-sanitize.js"></script>

	<!-- load bootstrap's compiled and minified JavaScript (includes jquery) -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

	<!-- load the AngularJS application files, starting with the app definition -->
	<script src="js/app.js"></script>

	<!-- load the controllers and services -->
	<script src="controllers/about.js"></script>
	<script src="controllers/header.js"></script>
	<script src="controllers/home.js"></script>
	<script src="controllers/ingestStatus.js"></script>
	<script src="controllers/aceDepositor.js"></script>
	<script src="controllers/aceSummary.js"></script>
	<script src="services/database.js"></script>
	<script src="services/formatting.js"></script>
	<script src="services/navigation.js"></script>
	<script src="js/alertMessage.js"></script>

	<!-- load the theme. otherwise, remove to use default bootsrap skin. -->
	<script src="js/theme-ucsd.js"></script>
	
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
