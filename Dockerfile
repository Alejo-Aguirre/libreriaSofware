# Use the official Maven image from Docker Hub
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory
WORKDIR /tmp

# Copy the parent POM and all modules
COPY pom.xml .

# Copy each module to the Docker image
COPY persistencia/ ./persistencia/
COPY negocio/ ./negocio/
COPY web/ ./web/

# Ensure Maven writes to the /tmp directory
ENV MAVEN_CONFIG /tmp/.m2

# Build each module
RUN mvn -f pom.xml clean install

# Build the final Docker image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built artifacts from the build stage
COPY --from=build /tmp/web/target/web-*.jar ./app.jar

# Run the web application
CMD ["java", "-jar", "app.jar"]
