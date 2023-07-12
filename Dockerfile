FROM eclipse-temurin:19-alpine
LABEL version="1.0.0" description="Microservicio inventario"
ENV TZ America/Bogota
EXPOSE 8080
COPY target/app-inventory-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar app.jar" ]
HEALTHCHECK CMD curl --fail http://localhost:8080 || exit 1