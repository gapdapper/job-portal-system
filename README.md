# Job Portal System

A full-stack web application built with **Spring Boot** that enables job seekers and companies to connect efficiently. The system provides job postings, application submissions, role-based access control, and secure authentication using JWT.

## 🚀 Features

- User authentication & registration with JWT
- Role-based authorization (Admin, Applicant, Company)
- Pagination, and sorting for job listings
- Applicants can apply for jobs
- Admins can view all applications
- Pagination and sorting for job listings
- RESTful API design

## 🛠 Tech Stack

- **Backend:** Spring Boot, Spring Security, JWT, Hibernate, JPA
- **Database:** MySQL / PostgreSQL (your choice)
- **Build Tool:** Maven / Gradle
- **Other:** Lombok, MapStruct, Swagger (optional)

## ⚙️ Configuration

Configure your database in `application.properties` or `application.yml`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```

Alternatively, use Docker Compose to set up the database:

Create a `docker-compose.yml` file:

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

Start the database:

```bash
docker-compose up -d
```

## 🏗 Build and Run

Build and run the application:

```bash
./mvnw spring-boot:run
```

Access the API at:  
[http://localhost:8080/api/v1/](http://localhost:8080/api/v1/)

## 📖 API Endpoints

| Method | Endpoint                  | Description                          |
|--------|---------------------------|--------------------------------------|
| POST   | `/auth/register`          | Register a new user                 |
| POST   | `/auth/login`             | Login and receive JWT               |
| GET    | `/jobs`                   | List all job posts                  |
| POST   | `/jobs`                   | (Company only) Create a job post    |
| POST   | `/jobs/{id}/submit`       | (Applicant only) Apply for a job    |
| GET    | `/jobs/{id}/applications` | (Admin only) View applications      |
