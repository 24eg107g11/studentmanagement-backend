FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy Maven wrapper and project files
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Application port
EXPOSE 8080

# Start the generated JAR
CMD ["sh", "-c", "java -jar target/*.jar"]