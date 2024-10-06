# Use Maven with OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17-alpine as builder
WORKDIR /app

# Copy the project files and build in one step to avoid intermediate layers
COPY pom.xml src/ ./
RUN mvn clean package -DskipTests -Dspring.profiles.active=prod

# Use a minimal JRE image for the runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
