# ---------- Build Stage ----------
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /workspace

# Copy Maven files first for dependency caching
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build application
RUN ./mvnw clean package -DskipTests

# ---------- Runtime Stage ----------
FROM bellsoft/liberica-runtime-container:jre-21-slim-musl

WORKDIR /app

# Create non-root user
RUN addgroup -S appuser && adduser -S -G appuser appuser

# Copy jar from build stage
COPY --from=build /workspace/target/*.jar /app/auth-service.jar

# Expose Auth Service port
EXPOSE 8081

# Switch user
USER appuser

# Run application
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "/app/auth-service.jar"]