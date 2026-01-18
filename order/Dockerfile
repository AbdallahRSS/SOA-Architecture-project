FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy already built jar
COPY target/order-0.0.1-SNAPSHOT.jar .

ENV SERVER_PORT=8081
EXPOSE 8081

CMD ["java", "-jar", "order-0.0.1-SNAPSHOT.jar"]
