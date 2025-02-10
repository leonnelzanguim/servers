# stage 1: build
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

COPY . .

RUN mvn package -DskipTests


# stage 2: run
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]

