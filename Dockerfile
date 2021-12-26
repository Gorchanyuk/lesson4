# syntax=docker/dockerfile:1
FROM openjdk:11

COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

RUN ./mvnw install

EXPOSE 8080

CMD ["java", "-jar", "target/lesson4-0.0.1-SNAPSHOT.jar"]