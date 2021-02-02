FROM openjdk:8-jdk-alpine
ADD ./target/basePJT-0.0.1-SNAPSHOT.jar app.jar
VOLUME /test/data
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]
