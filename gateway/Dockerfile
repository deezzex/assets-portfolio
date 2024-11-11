FROM eclipse-temurin:21.0.1_12-jdk-ubi9-minimal

WORKDIR /app

COPY target/*.jar gateway.jar

ENTRYPOINT ["java", "-jar", "gateway.jar"]