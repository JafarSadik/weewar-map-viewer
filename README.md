# Weewar Map Viewer

Weewar.com was a fantastic turn-based strategy game discontinued in 2015.
This archive comprises over 12,000 Weewar maps, featuring an infinite scroll and a server-side image renderer.
It could be argued that this project was a success, considering that many of these maps are currently being utilized
by [tinyattack.com](https://www.tinyattack.com/)

The project uses `Kotlin 1.9`, `Groovy 3`, `Gradle 8.2`, `Spring Boot 2.7` and is compiled
with `Java 17 JDK Temurin-17.0.7+7`.

Build and run with Gradle Wrapper and JDK17:

```
./gradlew build
java -jar -Dspring.profiles.active=dev build/libs/weewar-map-viewer.jar
```

Or just pull & run the most recent image from Docker Hub:

```
docker run --pull always -p8080:8080 --rm dzafarsadik/weewar-map-viewer:latest
```