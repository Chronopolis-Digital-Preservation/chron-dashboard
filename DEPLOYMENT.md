# Requirements
* Java 8
* Tomcat 8+

# Deployment
It is pretty easy to deploy the chronopolis dashboard.

You will need to deploy the built Docker image from the Gitlab repository.

This can be found at: `registry.gitlab.com/chronopolis/chron-dashboard/dashboard`

The application expects that environment variables will be passed in to
configure each Chronopolis Node. Example environment variables:

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
CHRON_NODES_1_NAME=DPN
CHRON_NODES_1_ACEENDPOINT=https://dpn.something
...
...
...
```
