# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add Maintainer Info
LABEL maintainer="harsh.and@outlook.com(opens in new tab)"
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 8181 available to the world outside this container
EXPOSE 8181
# The application's jar file
ARG JAR_FILE=build/libs/*.jar
# Add the application's jar to the container
COPY ${JAR_FILE} app.jar
# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]