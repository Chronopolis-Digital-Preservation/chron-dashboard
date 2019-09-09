# Chronopolis Dashboard

Chronopolis Dashboard to view status of Chronopolis nodes

## Development Environment

We recommend using either Docker or Podman to run a local instance of the
dashboard application for local development.

1. Clone the repo: `git clone git@gitlab.com:chronopolis/chron-dashboard.git`
1. Change to the project directory: `cd chron-dashboard`
1. Populate a `.env` file with [Chronopolis node information][./DEPLOYMENT.md]
1. Build the container image: `docker build -t dashboard` or `podman build -t
   dashboard`
1. Run an instance of the application: `docker run --rm --env-file=.env -p
   8080:8080 dashboard:latest`
1. Browse to http://localhost:8080/dashboard

## Deployment

### Requirements for Container Image deployment
- A container image tool such as Docker or Podman
- Optionally, Kubernetes

You will need to deploy the built Docker image from the Gitlab repository.

This can be found at: `registry.gitlab.com/chronopolis/chron-dashboard/dashboard`

At the moment, you will want to use the `latest` tag.

### Requirements for traditional deployment
- Java8
- Tomcat8+

Drop the dashboard.war file into your Java Web Server's application directory.

## Environment Variables

The application expects that environment variables will be passed in to
configure each Chronopolis Node. For local development, using a `.env` file
works well. In a production context it is up to the deploying institution to
decide what works best for them for populating these variables.

Example environment variables:

> Note these match Spring external config relaxed binding rules
> See: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-relaxed-binding

```
CHRON_NODES_0_NAME=UCSD
CHRON_NODES_0_ACEENDPOINT=https://chron.ucsd.edu/
CHRON_NODES_0_ACEHOST=chron.ucsd.edu
CHRON_NODES_0_ACEPORT=443
CHRON_NODES_0_ACEUSERNAME=ace-user
CHRON_NODES_0_ACEPASSWORD=ace-password
CHRON_NODES_0_INGESTENDPOINT=https://chron-ingest.ucsd.edu/
CHRON_NODES_0_INGESTHOST=chron-ingest.ucsd.edu
CHRON_NODES_0_INGESTPORT=443
CHRON_NODES_0_INGESTUSERNAME=ingest-user
CHRON_NODES_0_INGESTPASSWORD=ingest-password
CHRON_NODES_0_ENVIRONMENT=production
CHRON_NODES_1_NAME=TDL
CHRON_NODES_1_ACEENDPOINT=https://tdl.something
...
...
...
```

