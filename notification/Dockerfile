# Dockerfile for Notification Service
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built jar file (change the name if needed)
COPY target/notification-0.0.1-SNAPSHOT.jar .

# Set environment variables
ENV SERVER_PORT=8083
EXPOSE 8083

# Run the application
CMD ["java", "-jar", "notification-0.0.1-SNAPSHOT.jar"]
