FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y maven

RUN java -version  # Verify Java version inside the container

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/email-writer-sb-0.0.1-SNAPSHOT.jar"]
