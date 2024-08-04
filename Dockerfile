FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/floodrisk-1.0.0-SNAPSHOT.jar /floodrisk.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/floodrisk.jar"]
