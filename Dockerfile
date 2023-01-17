FROM maven:3.8.2-jdk-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/SpringPost-0.0.1-SNAPSHOT.jar springPost.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","springPost.jar"]
