#Compilacion
FROM gradle:9.4.1-jdk17 AS builder
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN gradle bootJar -x test

#Ejecucion
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/client-0.0.1-SNAPSHOT.jar client.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "client.jar"]