# Використовуємо базовий образ для Maven
FROM maven:latest AS build

WORKDIR /app

# Копіюємо файли для збірки
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app

# Збираємо проект і створюємо .jar файл
RUN mvn clean
RUN mvn package -DskipTests

# Тепер використовуємо образ з JDK для запуску додатку
FROM openjdk:17-jdk-slim AS final

# Копіюємо зібраний .jar файл з попереднього кроку
COPY --from=build /app/target/learning-programming-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 3004

ENTRYPOINT ["java", "-jar", "app.jar"]
