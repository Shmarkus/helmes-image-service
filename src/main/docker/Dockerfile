FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    APP_SLEEP=0

# add directly the war
ADD *.jar /app.jar

VOLUME /tmp
EXPOSE 80
CMD echo "The application will start in ${APP_SLEEP}s..." && \
    sleep ${APP_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app.jar