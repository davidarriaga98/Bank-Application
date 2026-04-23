FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/client-0.0.1-SNAPSHOT.jar client.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "client.jar"]