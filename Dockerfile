FROM eclipse-temurin:17.0.8_7-jdk-focal as build_stage
COPY . /project
WORKDIR /project
RUN ./gradlew build

FROM eclipse-temurin:17-jre-focal
WORKDIR /app
ENV PROFILE=prod
COPY --from=build_stage /project/build/libs/weewar-map-viewer.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${PROFILE}",\
            "-server", "-Xmx250m", "-Dfile.encoding=UTF-8",\
            "weewar-map-viewer.jar"]
