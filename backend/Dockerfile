# Use Gradle image to build the project
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test --no-daemon

# Use JRE image to run the application
FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
COPY --from=build /app/build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
