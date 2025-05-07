FROM maven:latest AS build

WORKDIR /app

COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app

RUN mvn clean
RUN mvn package -DskipTests

COPY src/main/resources/static/images/ /static/images/

FROM openjdk:17-jdk-slim AS final

COPY --from=build /app/target/learning-programming-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 3004

ENTRYPOINT ["java", "-jar", "app.jar"]
