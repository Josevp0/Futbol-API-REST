FROM openjdk:17
COPY target/intentoNumero1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8108
ENTRYPOINT [ "java","-jar", "app.jar" ]