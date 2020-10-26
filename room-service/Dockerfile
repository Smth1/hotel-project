FROM openjdk:11
RUN mkdir /opt/app
COPY target/room-service-1.0-SNAPSHOT.jar /opt/app
EXPOSE 8085
CMD ["java", "-jar", "/opt/app/room-service-1.0-SNAPSHOT.jar"]