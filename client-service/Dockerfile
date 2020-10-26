FROM openjdk:11
RUN mkdir /opt/app
COPY target/client-service-1.0-SNAPSHOT.jar /opt/app
EXPOSE 8082
CMD ["java", "-jar", "/opt/app/client-service-1.0-SNAPSHOT.jar"]