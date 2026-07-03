# AI-Powered CV Analyzer Platform

This project is an enterprise backend application developed with Java and Spring Boot that simulates a modern Applicant Tracking System (ATS).

The main goal is to automate the recruitment process by allowing recruiters to upload CVs, analyse them using Artificial Intelligence, compare candidates against job requirements and rank them according to their compatibility.

The project is being developed incrementally, following software engineering best practices and focusing on clean architecture, scalability and maintainability.

---

## Current Features

### Candidate Management

- Candidate CRUD
- PostgreSQL persistence
- Spring Data JPA
- Hibernate

### PDF Processing

- Upload CVs in PDF format
- Automatic file storage
- PDF text extraction using Apache PDFBox

### AI Resume Analysis

<img width="684" height="814" alt="image" src="https://github.com/user-attachments/assets/e05baab4-5210-4276-b4f1-a6f65e2d9785" />

The extracted CV text is sent to Google Gemini, which identifies:

- Technical Skills
- Soft Skills
- Seniority
- Strengths
- Weaknesses

The analysis is stored in the database and can later be reused without calling the AI again.

### Matching Engine

<img width="272" height="309" alt="image" src="https://github.com/user-attachments/assets/82b64522-65a3-4375-99f4-2c8754fc5518" />

The application compares candidates against job offers using a custom scoring algorithm implemented entirely in Java.

Current weights:

| Category | Weight |
|----------|--------|
| Technical Skills | 70% |
| Soft Skills | 20% |
| Seniority | 10% |

For each candidate the platform calculates:

- Technical matching
- Soft skills matching
- Seniority matching
- Overall compatibility score
- Missing skills
- Candidate ranking

### Authentication

The API is secured using:

- Spring Security
- JWT Authentication
- Stateless Authentication
- BCrypt Password Encryption
- Role Based Authorization

Current roles:

- ADMIN
- RECRUITER

---

## Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Main programming language |
| Spring Boot 3 | Backend framework |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Persistence layer |
| Hibernate | ORM |
| PostgreSQL | Database |
| Google Gemini API | AI CV analysis |
| Apache PDFBox | PDF text extraction |
| Swagger / OpenAPI | API documentation |
| Maven | Dependency management |

---

## Project Structure

```
src/main/java/com/nunes/ai_cv_analyzer

config/
controller/
dto/
entity/
exception/
integration/
    └── gemini/
mapper/
repository/
security/
service/
util/
```

The project follows a layered architecture:

<img width="800" height="900" alt="image" src="https://github.com/user-attachments/assets/47855410-4d13-4433-abe3-62ab44bfed96" />

External AI communication is isolated inside the `integration/gemini` package.

---

## Security Flow

<img width="1030" height="607" alt="image" src="https://github.com/user-attachments/assets/17856fcc-f746-401a-b71c-83efd4a1c7df" />

---

## API Documentation

<img width="1204" height="1027" alt="image" src="https://github.com/user-attachments/assets/31342704-6197-414b-afd7-2431edf905e4" />

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

Authentication is performed using a Bearer Token obtained from the `/auth/login` endpoint.

---

## Running the Project

Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/ai-cv-analyzer.git
```

Create an `application.properties` file based on the provided `application-example.properties`.

Run PostgreSQL.

Start the application:

```bash
mvn spring-boot:run
```

---

## Project Status

### Completed

- ✅ Candidate CRUD
- ✅ DTO Pattern
- ✅ Validation
- ✅ Global Exception Handling
- ✅ PDF Upload
- ✅ PDF Text Extraction
- ✅ Google Gemini Integration
- ✅ AI Resume Analysis
- ✅ Matching Engine
- ✅ Spring Security
- ✅ JWT Authentication

### Planned

- AWS S3 Integration
- Docker & Docker Compose
- GitHub Actions
- Flyway
- Unit Testing
- Integration Testing
- Logging
- Clean Architecture improvements
- Performance optimizations

---

## Screenshots

### Architecture

> *(Coming soon)*

### Swagger

> *(Coming soon)*

### Matching Example

> *(Coming soon)*

---

## Author

**Sérgio Nunes**

This project is part of my backend portfolio and is being developed as a way to deepen my knowledge of enterprise Java development, software architecture and AI integration.
