FROM eclipse-temurin:21-jdk-alpine AS build
RUN mkdir /opt/ms-iam
WORKDIR /opt/ms-iam/
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw ./
COPY mvnw.cmd ./
COPY pom.xml ./
RUN ./mvnw clean package
EXPOSE 8080
ENTRYPOINT ./mvnw spring-boot:run
