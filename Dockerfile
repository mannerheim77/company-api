FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/demo-1.0.0.jar
ADD ${JAR_FILE} bellamy-company.jar
ENTRYPOINT ["java","-jar","/bellamy-company.jar"]