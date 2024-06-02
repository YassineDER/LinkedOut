FROM maven:3.9.0-eclipse-temurin-19-alpine AS build
LABEL authors="yassine"
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src src
RUN mvn -B package -DskipTests
