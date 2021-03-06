FROM gradle:4.10-jdk11-slim as build_stage
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build

FROM openjdk:11-slim
WORKDIR /app
ENV PROFILE=prod
COPY --from=build_stage /home/gradle/project/build/libs/weewar-map-viewer.jar .

EXPOSE 8080

CMD java -jar -server -Xmx250m -Xss256k -XX:+UseCompressedOops -XX:CICompilerCount=2 \
 -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC \
 -Dfile.encoding=UTF-8 -Dserver.port=$PORT  \
 -Dspring.profiles.active=$PROFILE weewar-map-viewer.jar