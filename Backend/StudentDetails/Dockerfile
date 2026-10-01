FROM eclipse-temurin:17-jre
# Uses Java 17 runtime.
WORKDIR /app
# Creates/uses /app inside the container.
COPY target/*.jar app.jar
# Copies your Spring Boot JAR into the container.
EXPOSE 8085
# Documents that the application uses port 8080.
ENTRYPOINT ["java", "-jar", "app.jar"]
# Starts your Spring Boot application when the container starts.