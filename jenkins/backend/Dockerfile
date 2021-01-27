FROM openjdk:8-jdk-alpine
VOLUME /volumes
ADD /var/jenkins_home/workspace/s04p12d103/backend/target/basePJT-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]
