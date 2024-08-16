FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/apisimples-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} apisimples-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/apisimples-0.0.1-SNAPSHOT.jar"]