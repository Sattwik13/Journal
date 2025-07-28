# 📝 Journal App

A full-stack, secure, and scalable **Journal Management System** built with **Spring Boot**, offering user authentication, sentiment analysis, Redis caching, scheduled tasks, and RESTful APIs. Ideal for daily journaling with emotional tracking and weather context integration.

---

## 📌 Features

- 🛡️ **JWT Authentication** with Spring Security
- 💬 **Sentiment Analysis** for user entries
- 📊 **Weather API Integration**
- ⚡ **Redis Caching** for improved performance
- 📅 **Scheduled Tasks** using Spring Scheduler
- 📚 **RESTful APIs** for journal, user, and admin operations
- 🔍 **Swagger UI** for API documentation
- 📧 **Email Service** for notifications
- 📧 **Cron Expression** for Scheduling task
- 🧪 **Unit & Integration Tests**

---

## 🧠 Technologies Used
- Spring Boot

- Spring Security

- Spring MVC

- JWT

- Redis

- JPA/Hibernate

- MySQL/PostgreSQL

- Swagger/OpenAPI

- JUnit, Mockito

## 📁 Project Structure

```bash
src/
├── main/
│ ├── java/com/journal/journalApp/
│ │ ├── controller/ # REST controllers for different roles
│ │ ├── entity/     # JPA entities
│ │ ├── repository/ # Repositories and custom implementations
│ │ ├── Service/    # Business logic
│ │ ├── config/     # Spring & Redis configurations
│ │ ├── schedulers/ # Scheduled tasks (e.g., reminder emails)
│ │ ├── model/      # Data models (e.g., sentimentData)
│ │ ├── apiResponse/ # API response DTOs
│ │ ├── filter/     # JWT Filter
│ │ ├── utils/      # Utility classes (e.g., JWT helper)
│ └── resources/
│ ├── application.yml # Configuration
│ ├── logback.xml     # Logging config
├── test/             # JUnit and MockMVC tests
```

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven 3.8+
- Redis Server (for caching)
- MongoDB (configured in `application.yml`)

### ⚙️ Setup & Run

```bash
# Clone the repository
git clone https://github.com/Sattwik13/sattwik13-journal.git
cd sattwik13-journal

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```
### Edit `Application.properties` 
```bash
spring.data.mongodb.uri = mongodb+srv://<DB_NAME>:<Password>@cluster0.7pe66db.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
spring.data.mongodb.database=journaldb
spring.data.mongodb.auto-index-creation= true

spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

server.port= 8081
server.servlet.context-path= /journal 

weather.api.key= API_KEY

spring.mail.host=smtp.gmail.com 
spring.mail.port=PORT
spring.mail.username=Demo@gmail.com
spring.mail.password=PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

spring.redis.host= localhost
spring.redis.port= 6379 

# kafka setup -----------
spring.kafka.bootstrap-servers=pkc-12576z.us-west2.gcp.confluent.cloud:9092

spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

spring.kafka.consumer.group-id=weekly-sentiment-group
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=com.journal.journalApp.model 

spring.kafka.properties.sasl.mechanism=PLAIN
spring.kafka.properties.security.protocol=SASL_SSL
spring.kafka.properties.sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username='Username' password='password';

# Best practice for higher availability in Apache Kafka clients prior to 3.0
spring.kafka.properties.session.timeout.ms=45000
```

### 🔑 API Security
- JWT Token Authentication is enabled.

- Secure routes for `/user/**, /admin/**`

- Public routes include `/auth/**, /health`

### 🧪 Testing
Unit and integration tests are included under `src/test/java.`

Use the following to run tests: 
```bash
./mvnw test
```
### 📘 Documentation
Swagger UI available at:

```bash
http://localhost:8080/swagger-ui/index.html
```

## 🙋 Author
Sattwik Manna
