# Use Maven image to build the project
FROM maven:3.9-eclipse-temurin-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Use JRE image to run the application
FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
