
### Сборка приложения
1. Перейти в директорию проекта
2. Сборка проета : ./gradlew clean build
3. - удаление таблиц: ./gradlew dropSql
   - создание таблиц: ./gradlew createSql
4. Запуск сервера: ./gradlew tomcatRun
   (http://localhost:8080)
5. Остановка приложения:
  - [Ctrl] + [C] 
  - Y
  - ./gradlew --stop

### Технологии
- Java 11
- Gradle
- H2 
- Apache Wicket
- Apache Tomcat









