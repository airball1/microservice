FROM 192.168.1.94:8080/micro-service/openjdk:8-jre
MAINTAINER xxx xxx@imooc.com

COPY target/course-dubbo-service-1.0-SNAPSHOT.jar /course-service.jar

ENTRYPOINT ["java", "-jar", "/course-service.jar"]