# Використовуємо базовий образ з JDK
FROM openjdk:17-jdk-slim

# Вказуємо робочу директорію всередині контейнера
WORKDIR /app

# Копіюємо jar файл додатку в контейнер
COPY target/learning-programming-0.0.1-SNAPSHOT.jar app.jar

# Відкриваємо порт, на якому буде працювати ваш додаток (звичайно Spring Boot працює на порту 8080)
EXPOSE 3004

# Команда для запуску додатку при старті контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]
