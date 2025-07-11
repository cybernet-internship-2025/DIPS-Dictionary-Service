# 1. Use an official Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy all files and build the app
COPY . .
RUN mvn clean package -DskipTests

# 2. Use a lightweight JDK image to run the app
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy only the built jar from the first image
COPY --from=build /app/target/*.jar dictionary-service-1.0.0-SNAPSHOT.jar

# Set port (change if your app uses another)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "dictionary-service-1.0.0-SNAPSHOT.jar"]
