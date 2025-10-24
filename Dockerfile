FROM maven:3.9.11-eclipse-temurin-21-alpine AS builder
WORKDIR /home/app

COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre
WORKDIR /home/app

COPY --from=builder /home/app/target/*.jar ssp-game-api.jar

ENV PROFILE dev

CMD ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "ssp-game-api.jar"]

