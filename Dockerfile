FROM openjdk:17-jdk-slim
WORKDIR /expense-tracker
CMD ["./gradlew", "clean", "bootJar"]
COPY ./build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
