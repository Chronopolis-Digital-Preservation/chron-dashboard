# Requirements
* Java 8
* Tomcat 8+

# Deployment
It is pretty easy to deploy the chronopolis dashboard.

Drop the dashboard.war file into your Java Web Server's application directory.


The dashboard does require one external file to be referenced via Java Virtual Machine Options.

Adding the following parameter to the startup script of your java web container should do the trick:
```
-Dspring.config.location="file:<full_path_to_file>/secrets.yml"
```

It is recommended that the secrets.yml file be stored in a non-volatile location.

We give this recommendation because during upgrades the secrets.yml file will not be deleted and upgrades will go smoother.

We have a directory called chron-dashboard-home where we store our file.

Example secrets.yml file:

```
nodes:
  - name: 'UCSD'
    aceendpoint: 'https://chron.ucsd.edu/'
    acehost: 'chron.ucsd.edu'
    aceport: '443'
    aceusername: '<ACE username with read rights>'
    acepassword: '<password>'
    ingestendpoint: 'https://chron-ingest.ucsd.edu:8443/'
    ingesthost: 'chron-ingest.ucsd.edu'
    ingestport: '8443'
    ingestusername: '<Ingest username with read rights>'
    ingestpassword: '<password>'
    environment: 'production'
  - name: 'NCAR'
    aceendpoint: 'https://chronopolis.ucar.edu/'
    acehost: 'chronopolis.ucar.edu'
    aceport: '443'
    aceusername: '<ACE username with read rights>'
    acepassword: '<password>'
    environment: 'production'
  - name: 'TEST-UMIACS'
    aceendpoint: 'https://chronopolis00.umiacs.umd.edu/'
    acehost: 'chronopolis00.umiacs.umd.edu'
    aceport: 443
    aceusername: '<ACE username with read rights>'
    acepassword: '<password>'
    environment: 'test'
  - name: 'DPN'
    aceendpoint: 'https://chrondpn.ucsd.edu/'
    acehost: 'chrondpn.ucsd.edu'
    aceport: 443
    aceusername: '<ACE username with read rights>'
    acepassword: '<password>'
    environment: 'dpn'
```
You will need to know people that know people to get the appropriate username and passwords to each individual ace/ingest system.

Example application.properties file:
```
dashboard.version=@dashboard.version@

# Pretty-print JSON responses
spring.jackson.serialization.indent_output=true
```
