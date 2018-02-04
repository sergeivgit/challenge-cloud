FROM openjdk:8-jdk-alpine
MAINTAINER sergio.oconcer@gmail.com
COPY target/cities-services-zuul-1.0.0.RELEASE.jar /opt/spring-cloud/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/spring-cloud/lib/cities-services-zuul-1.0.0.RELEASE.jar"]
VOLUME /var/lib/spring-cloud/config-repo
