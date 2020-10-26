FROM openjdk:11
RUN mkdir /opt/app
COPY target/housekeeping-service-1.0-SNAPSHOT.jar /opt/app
EXPOSE 8084
CMD ["java", "-jar", "/opt/app/housekeeping-service-1.0-SNAPSHOT.jar"]