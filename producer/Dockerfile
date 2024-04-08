# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk:17-jre

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/order-producer.jar /app

# Run the JAR file
CMD ["java", "-jar", "order-producer.jar"]
