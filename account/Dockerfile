FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/account-0.0.1-SNAPSHOT.jar account.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "account.jar"]