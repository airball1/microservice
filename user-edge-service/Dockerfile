FROM 192.168.1.94:8080/micro-service/openjdk:8-jre
MAINTAINER xxx xxx@imooc.com

COPY target/user-edge-service-1.0-SNAPSHOT.jar /user-edge-service.jar

ENTRYPOINT ["java", "-jar", "/user-edge-service.jar"]