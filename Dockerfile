# Stage 1: Build with Maven
FROM maven:3.9.5-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy everything
COPY . .

# Build the app (skip tests for build stage)
RUN mvn clean package -DskipTests

# Stage 2: Final image to run
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the jar from builder stage
COPY --from=builder /app/target/*.jar app.jar


# Expose application port
EXPOSE 9090

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
