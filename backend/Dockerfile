FROM maven:3.9.6-eclipse-temurin-17-alpine
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Dspring.profiles.active=prod
COPY target/*.jar /app/target/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/target/app.jar", "--spring.profiles.active=prod", "--server.port=8080"]
