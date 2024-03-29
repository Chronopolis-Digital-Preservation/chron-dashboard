# chron-dashboard

This project contains an HTML5/AngularJS application and is preconfigured to install the Angular
framework and a bunch of development and testing tools.

## Getting Started

To get you started you can simply clone the chron-dashboard repository and install the dependencies.

### Prerequisites

We use a number of node.js tools to initialize and test chron-dashboard. You must have node.js and
its package manager (npm) installed.  You can get them from [http://nodejs.org/](http://nodejs.org/).

### Clone chron-dashboard

Clone the chron-dashboard repository using [git][git]:

```
git clone https://github.com/UCAR/chron-dashboard.git
cd chron-dashboard
```

If you just want to start a new project without the chron-dashboard commit history then you can do:

```bash
git clone --depth=1 https://github.com/UCAR/chron-dashboard.git <your-project-name>
```

The `depth=1` tells git to only pull down one commit worth of historical data.

### Install Dependencies

There are two kinds of dependencies in this project: tools and angular framework code.  The tools
help manage and test the application.

* The tools are obtained with `npm`, the [node package manager][npm].
* The angular code is retrieved via `bower`, a [client-side code package manager][bower].

`npm` is preconfigured to automatically run `bower` so you can simply do:

```
npm install
```

Behind the scenes this will also call `bower install`.  You should find that you have two new
folders in your project.

* `node_modules` - contains the npm packages for the tools
* `app/bower_components` - contains the angular framework files

*Note that the `bower_components` folder would normally be installed in the app folder but
chron-dashboard changes this location through the `.bowerrc` file.  Putting it in the app folder
would allow bower to maintain the versions for the application as well as the tests.*

### Run the Application

The project is preconfigured with a simple development web server.  The simplest way to start
this server is:

```
npm start
```

Now browse to the app at `http://localhost:8000/app/index.html`.


## Directory Layout

```
app/                --> all of the source files for the application
  controllers/          --> all controller modules
  css/                  --> all css
  images/               --> image files
  js/                   --> top-level JavaScript code, including directives
    app.js                  --> main application module
  php/                  --> PHP scripts used for local testing
  services/             --> all service modules
  views/                --> HTML code for partial page views
  index.html            --> app layout file (the main html template file of the app)
.bowerrc            --> bower initialization script
karma.conf.js       --> config file for running unit tests with Karma
package.json        --> Node.js configuration script
e2e-tests/          --> end-to-end tests
  protractor-conf.js    --> Protractor config file
  scenarios.js          --> end-to-end scenarios to be run by Protractor
```

## Testing

There are two kinds of tests in the chron-dashboard application: Unit tests and end-to-end tests.

### Running Unit Tests

The unit tests are written in [Jasmine][jasmine], which is run with the
[Karma Test Runner][karma]. There's a Karma configuration file to run them.
* the configuration is found at `karma.conf.js`
* the unit tests are found next to the code they are testing and are named as `..._test.js`.

The easiest way to run the unit tests is to use the supplied npm script:

```
npm test
```

This script will start the Karma test runner to execute the unit tests. Moreover, Karma will sit and
watch the source and test files for changes and then re-run the tests whenever any of them change.
This is the recommended strategy; if your unit tests are being run every time you save a file then
you receive instant feedback on any changes that break the expected code functionality.

You can also ask Karma to do a single run of the tests and then exit.  This is useful if you want to
check that a particular version of the code is operating as expected.  The project contains a
predefined script to do this:

```
npm run test-single-run
```


### End to end testing

These tests are run with the [Protractor][protractor] End-to-End test runner.  It uses
native events and has special features for Angular applications.

* the configuration is found at `e2e-tests/protractor-conf.js`
* the end-to-end tests are found in `e2e-tests/scenarios.js`

Protractor simulates interaction with a web app and verifies that the application responds
correctly. So the web server needs to be serving up the application, so that Protractor
can interact with it.

```
npm start
```

In addition, since Protractor is built upon WebDriver we need to install this.  The chron-dashboard
project comes with a predefined script to do this:

```
npm run update-webdriver
```

This will download and install the latest version of the stand-alone WebDriver tool.

Once the development web server hosting our application is up and running and
WebDriver is updated, you can run the end-to-end tests using the supplied npm script:

```
npm run protractor
```

This script will execute the end-to-end tests against the application being hosted on the
development server.


## Updating Angular

You can update the tool dependencies by running:

```
npm update
```

This will find the latest versions that match the version ranges specified in the `package.json` file.

You can update the Angular dependencies by running:

```
bower update
```

This will find the latest versions that match the version ranges specified in
the `bower.json` file.

### Running the App during Development

The chron-dashboard project comes preconfigured with a local development
webserver.  It is a node.js tool called [http-server][http-server].  You
can start this webserver with `npm start` but you may choose to install the
tool globally:

```
sudo npm install -g http-server
```

Then you can start your own development web server to serve static files from a folder by
running:

```
http-server -a localhost -p 8000
```

Alternatively, you can choose to configure your own webserver, such as apache or nginx. Just
configure your server to serve the files under the `app/` directory.


### Running the App in Production

This really depends on how complex your app is and the overall infrastructure of your system, but
the general rule is that all you need in production are all the files under the `app/` directory.
Everything else should be omitted.

Angular apps are really just a bunch of static html, css and js files that just need to be hosted
somewhere they can be accessed by browsers.
