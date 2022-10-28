# Creating first container to compile Java code and create jar file
FROM maven:3-openjdk-11 AS build
WORKDIR /app

# Copying pom.xml file to container
COPY pom.xml .

# Downloading dependencies
RUN mvn -f ./pom.xml clean package

# Copying source code to container
COPY src src
COPY resources resources

# Compiling and creating jar file
RUN mvn -f ./pom.xml package

# Creating second container to run jar file
FROM openjdk:11
ENV BUILD "tswd-java-examples-1.0-SNAPSHOT.jar"
WORKDIR /app

# Copying jar file from first container to second container
COPY --from=build /app/target/${BUILD} app.jar

# Running jar file
ENTRYPOINT ["java","-cp","app.jar"]