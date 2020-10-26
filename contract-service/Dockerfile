FROM openjdk:11
RUN mkdir /opt/app
COPY target/contract-service-1.0-SNAPSHOT.jar /opt/app
EXPOSE 8083
CMD ["java", "-jar", "/opt/app/contract-service-1.0-SNAPSHOT.jar"]