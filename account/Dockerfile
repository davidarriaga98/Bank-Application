# Compilacion
FROM gradle:9.4.1-jdk17 AS builder
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN gradle bootJar -x test

#Ejecucion
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/account-0.0.1-SNAPSHOT.jar account.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "account.jar"]