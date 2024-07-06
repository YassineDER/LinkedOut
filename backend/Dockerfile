FROM openjdk:19-jdk-alpine3.16
LABEL authors="Yassine DERGAOUI"
EXPOSE 8080
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
