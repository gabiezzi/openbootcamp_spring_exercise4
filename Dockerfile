FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file to the container
COPY target/laptop-api.jar /app/laptop-api.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "laptop-api.jar"]