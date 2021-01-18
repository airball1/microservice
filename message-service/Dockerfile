FROM 192.168.1.94:8080/micro-service/openjdk:8-jre
MAINTAINER xxx xxx@imooc.com

COPY target/message-service-1.0-SNAPSHOT.jar /message-service.jar

ENTRYPOINT ["java", "-jar", "/message-service.jar"]