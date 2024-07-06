# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Dspring-boot.run.profiles=prod

# Stage 2: Run the application
FROM openjdk:19-jdk-alpine3.16
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
