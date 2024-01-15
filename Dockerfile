FROM openjdk:17-jdk-slim
EXPOSE 8080
RUN mkdir -p /app/
COPY ./build/libs/WorkoutTracker-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
