FROM openjdk:11
RUN mkdir /opt/app
COPY target/admin-service-1.0-SNAPSHOT.jar /opt/app
EXPOSE 8081
CMD ["java", "-jar", "/opt/app/admin-service-1.0-SNAPSHOT.jar"]