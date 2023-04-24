FROM openjdk:19

COPY target/details-0.0.1-SNAPSHOT.jar details-docker.jar

ENTRYPOINT ["java","-jar","/details-docker.jar"]


