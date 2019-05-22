FROM openjdk:12-jdk-alpine
RUN apk update && apk add bash
VOLUME /tmp
COPY target/*.jar /app/app.jar
COPY ./entrypoint.sh /app/entrypoint.sh
ENTRYPOINT ["/app/entrypoint.sh"]