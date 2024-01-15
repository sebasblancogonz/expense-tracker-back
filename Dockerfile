FROM openjdk:17
EXPOSE 8080
RUN mkdir -p /app/
COPY build/libs/WorkoutTracker-1.0-SNAPSHOT.jar /app/WorkoutTracker-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/WorkoutTracker-1.0-SNAPSHOT.jar "]
