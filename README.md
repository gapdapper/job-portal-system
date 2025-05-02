# Job Portal System

A backend RESTful API built with **Spring Boot** that enables job seekers and companies to connect efficiently. The system provides job postings, application submissions, role-based access control, and secure authentication using JWT.

## 🚀 Features

- User authentication & registration with JWT
- Role-based authorization (Admin, Applicant, Company)
- Pagination and sorting for job listings
- Applicants can apply for jobs
- Admins can view all applications
- RESTful API design

## 🛠 Tech Stack

- **Backend:** Spring Boot, Spring Security, JWT, Hibernate, JPA
- **Database:** MySQL
- **Build Tool:** Maven
- **Other:** Lombok, MapStruct

## ⚙️ Configuration

Update your `application.yml` or `application.properties` file as shown below:

```yaml
#spring.application.name=Job Portal System

spring:
  application:
    name: job-portal-system
  profiles:
    active:
      - db
  datasource:
    url: jdbc:mysql://localhost:3306/jobportal?createDatabaseIfNotExist=true&autoReconnect=true&characterEncoding=UTF-8&allowMultiQueries=true&allowPublicKeyRetrieval=true&useSSL=false
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: password
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
    hibernate:
      ddl-auto: create

application:
  security:
    jwt:
      secret-key: <your-secure-jwt-secret>
      expiration: 86400000 # 1 day
      refresh-token:
        expiration: 604800000 # 7 days
```

> 🔐 Replace `<your-secure-jwt-secret>` with your actual secure key.

## 🐳 Docker Integration

This project includes automatic Docker support. When running the app using the Maven wrapper, the required services (like the database) will start automatically using `compose.yaml`.

### Docker Requirements

- Docker & Docker Compose installed and running
- `compose.yaml` file present in the root directory

### Compose File Example

```yaml
version: '3.8'
services:
  db:
    image: mysql:8.0
    container_name: job_portal_db
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root_password
      MYSQL_DATABASE: job_portal
      MYSQL_USER: your_db_user
      MYSQL_PASSWORD: your_db_password
    ports:
      - "3306:3306"
    volumes:
      - db_data:/var/lib/mysql

volumes:
  db_data:
```

> ✅ **Note:** No need to manually run `docker compose up`. It's triggered automatically when you run the app via Maven.

## 🏗 Build and Run

To build and run the application with Docker support:

```bash
./mvnw spring-boot:run
```

Access the API at:  
[http://localhost:8080/api/v1/](http://localhost:8080/api/v1/)

## 📖 API Endpoints

### 🔐 Authentication

| Method | Endpoint         | Description              |
|--------|------------------|--------------------------|
| POST   | `/auth/register` | Register a new user      |
| POST   | `/auth/login`    | Login and receive a JWT  |

---

### 💼 Job Posts

| Method | Endpoint                           | Description                                                        |
|--------|------------------------------------|--------------------------------------------------------------------|
| GET    | `/api/v1/jobs`                     | Get all job posts with optional pagination, sorting, and filtering |
| GET    | `/api/v1/jobs/{id}`                | Get a specific job post by ID                                      |
| GET    | `/api/v1/jobs/industry/{industry}` | Get job posts filtered by industry                                 |
| POST   | `/api/v1/jobs`                     | (Company only) Create a new job post                               |
| PUT    | `/api/v1/jobs/{id}`                | (Company only) Update a job post by ID                             |
| DELETE | `/api/v1/jobs/{id}`                | (Company only) Delete a job post by ID                             |

---

### 📝 Job Applications

| Method | Endpoint                                                 | Description                                                       |
|--------|----------------------------------------------------------|-------------------------------------------------------------------|
| POST   | `/api/v1/jobs/{id}/submit`                               | (Applicant only) Submit a job application                         |
| GET    | `/api/v1/jobs/applications/me`                           | (Applicant only) View applications submitted by current applicant |
| GET    | `/api/v1/jobs/{id}/applications`                         | (Company only) View applications for a specific job post          |
| PUT    | `/api/v1/jobs/{job-id}/applications/{app-id}/shortlist` | (Company only) Shortlist an application                           |
| PUT    | `/api/v1/jobs/{job-id}/applications/{app-id}/reject`    | (Company only) Reject an application                              |
