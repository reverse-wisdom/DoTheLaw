FROM openjdk:8-jdk-alpine
VOLUME /volumes
WORKDIR /var
RUN ls -al
ADD ./target/basePJT-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]
