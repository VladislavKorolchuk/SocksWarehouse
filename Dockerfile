# Distribution Open Java Development Kit (OpenJDK)
FROM amazoncorretto:11
# Set current working directory
WORKDIR /opt/sock
# Copies files and folders to the container
COPY target/*.jar /opt/sock/*.jar
# Call during container execution
ENTRYPOINT java - jar /opt/sock/*.jar
