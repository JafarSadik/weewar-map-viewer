# Weewar Map Viewer

An archive of over 12,000 weewar maps with an infinite scroll and server-side image renderer.

The project uses `Kotlin 1.9`, `Groovy 3`, `Gradle 8.2`, `Spring Boot 2.7` and is compiled with `Java 17 JDK Temurin-17.0.7+7 (build 17.0.7+7)`.

Build and run with Gradle Wrapper and JDK17:
```
./gradlew build
java -jar -Dspring.profiles.active=dev build/libs/weewar-map-viewer.jar
```

Or just pull & run the most recent image from Docker Hub:
```
docker run --pull always -p8080:8080 --rm dzafarsadik/weewar-map-viewer:latest
```