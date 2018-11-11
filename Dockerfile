FROM openjdk:8-jdk-alpine

WORKDIR /app
COPY build/libs/weewar-map-viewer.jar .

EXPOSE 8080

CMD java -jar -Xms256m -Xmx256m -Dspring.profiles.active=prod weewar-map-viewer.jar
