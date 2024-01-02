# Step 1: Use base image for building
FROM openjdk:17-jdk-slim as build

# Set working directory
WORKDIR /workspace/app

# Copy dependencies
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

# Build the application
RUN ./gradlew build -x test

# Step 2: Use base image for running
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Set environment variable
ENV SPRING_PROFILES_ACTIVE=prod

# Copy the executable JAR file created in the build stage
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Execute the application when the container starts
ENTRYPOINT ["java","-jar","/app/app.jar"]
