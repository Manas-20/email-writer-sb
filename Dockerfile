WORKDIR /app


RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/email-writer-sb-0.0.1-SNAPSHOT.jar"]
