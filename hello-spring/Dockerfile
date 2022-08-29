FROM openjdk:11
ARG PROJECT_NAME="hello-spring-0.0.1"
ARG JAR_FILE=./build/libs/${PROJECT_NAME}.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
