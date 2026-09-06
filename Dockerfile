FROM eclipse-temurin:17-jre

LABEL project="DevOps CI/CD Project"
LABEL maintainer="Bommena Surya Teja"

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]