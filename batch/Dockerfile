FROM openjdk:8
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} batch.jar
EXPOSE 8082
ENTRYPOINT java -jar -Dspring.profiles.active=dev /batch.jar
