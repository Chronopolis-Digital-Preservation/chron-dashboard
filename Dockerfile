ARG MAVEN_VERSION=3
ARG TOMCAT_VERSION=9
ARG JAVA_VERSION=8

FROM maven:${MAVEN_VERSION}-jdk-${JAVA_VERSION} as builder
RUN mkdir /dashboard
WORKDIR /dashboard
ADD pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn package

FROM tomcat:${TOMCAT_VERSION}-jdk${JAVA_VERSION} as production
VOLUME /tmp
COPY --from=builder /dashboard/target/dashboard-*.war /usr/local/tomcat/webapps/dashboard.war
