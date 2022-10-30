FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-centos
ARG JAR_FILE=api/target/document-*.jar
COPY ${JAR_FILE} document-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/document-service.jar"]
