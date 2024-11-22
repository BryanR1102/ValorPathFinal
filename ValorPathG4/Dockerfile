FROM amazoncorretto:21-alpine-jdk
#ARG JAR_FILE=target/ValorPathG4-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app_valorpath.jar
COPY "./target/ValorPathG4-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT["java" , "-jar" , "/app.jar"]
