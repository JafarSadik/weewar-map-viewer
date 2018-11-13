FROM openjdk:8-alpine

WORKDIR /app
COPY ./build/libs/weewar-map-viewer.jar .

EXPOSE 8080

CMD java -jar -server -Xms256m -Xmx256m -Xss512k -XX:CICompilerCount=2 \
 -Dfile.encoding=UTF-8 -Dserver.port=$PORT -Dspring.profiles.active=prod \
 weewar-map-viewer.jar
