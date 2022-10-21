FROM openjdk:11
WORKDIR /app
ENV BUILD "tswd-java-examples-1.0-SNAPSHOT.jar"
ADD target/${BUILD} app.jar
ENTRYPOINT ["java","-cp","app.jar"]
