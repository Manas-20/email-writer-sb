# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml ./
COPY src ./src

# Build the application inside the container
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Expose the application port (change it if necessary)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/email-writer-sb-0.0.1-SNAPSHOT.jar"]
