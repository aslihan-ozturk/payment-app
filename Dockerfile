FROM openjdk:11
MAINTAINER AslihanYuksel
COPY target/paymentApp-0.0.1-SNAPSHOT.jar paymentApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/paymentApp-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

