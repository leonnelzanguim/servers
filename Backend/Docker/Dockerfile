FROM maven:3.8.5-openjdk-17-slim

WORKDIR /tmp/app

COPY . .

RUN mvn package -DskipTests

RUN mkdir "/app"

RUN cp target/*.jar /app/server.jar

RUN rm -rf /tmp/app

EXPOSE 8090

CMD ["java", "-jar", "/app/server.jar"]

