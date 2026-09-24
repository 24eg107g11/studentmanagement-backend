# 🎓 Student Management System — Backend

A Spring Boot REST API backend for a full-stack Student Management System.

The backend provides user authentication, JWT-based security, role-based authorization, student CRUD operations, validation, exception handling, and MySQL database integration.

## 🚀 Features

* 🔐 User registration
* 🔑 User login
* 🛡️ JWT authentication
* 👤 USER and ADMIN roles
* 📋 View students
* ➕ Add students
* ✏️ Update students
* 🗑️ Delete students
* ✅ Request validation
* ⚠️ Global exception handling
* 🔒 Protected REST APIs
* 🔐 BCrypt password hashing
* 🗄️ MySQL database integration
* 🌐 CORS configuration
* 📡 RESTful API architecture

## 🛠️ Technologies

* Java 21
* Spring Boot
* Spring Web
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* JWT
* BCrypt
* Maven
* Jakarta Validation

## 📁 Project Structure

```text
studentmanagement/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/studentmanagement/
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   └── StudentController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   ├── ApiResponse.java
│   │   │       │   ├── LoginRequest.java
│   │   │       │   └── StudentRequest.java
│   │   │       │
│   │   │       ├── exception/
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── ResourceNotFoundException.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   ├── Student.java
│   │   │       │   └── User.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── StudentRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       │
│   │   │       ├── security/
│   │   │       │   ├── CorsConfig.java
│   │   │       │   ├── JwtAuthenticationFilter.java
│   │   │       │   ├── JwtService.java
│   │   │       │   └── SecurityConfig.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── AuthService.java
│   │   │       │   └── StudentService.java
│   │   │       │
│   │   │       └── StudentmanagementApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── .env
├── .env.example
├── .gitignore
├── mvnw
├── pom.xml
└── README.md
```

## ⚙️ Requirements

Install the following before running the backend:

* Java 21
* Maven or Maven Wrapper
* MySQL
* Git
* Visual Studio Code or another Java IDE

Check Java:

```bash
java -version
```

Check Maven Wrapper:

```bash
./mvnw -version
```

## 🗄️ Database Configuration

The application uses MySQL.

Create the database:

```sql
CREATE DATABASE student_db;
```

The backend connects to:

```text
localhost:3306/student_db
```

Database credentials are provided through environment variables.

The application does not store database passwords directly in the source code.

## 🔐 Environment Variables

Create a `.env` file in the backend project directory.

Example:

```env
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_secure_jwt_secret
JWT_EXPIRATION=3600000
```

The `.env` file must **never be committed to GitHub**.

A safe template is provided through:

```text
.env.example
```

## ⚠️ Loading Environment Variables

Spring Boot does not automatically load a `.env` file.

For local development, load the variables into your terminal before starting Spring Boot.

From the backend project directory:

```bash
set -a
source .env
set +a
```

Then start the application:

```bash
./mvnw spring-boot:run
```

Alternatively, environment variables can be exported manually:

```bash
export DB_USERNAME=your_mysql_username
export DB_PASSWORD=your_mysql_password
export JWT_SECRET=your_secure_jwt_secret
export JWT_EXPIRATION=3600000
```

## ▶️ Run the Backend

Open Terminal:

```bash
cd ~/Downloads/studentmanagement
```

Load your environment variables:

```bash
set -a
source .env
set +a
```

Start Spring Boot:

```bash
./mvnw spring-boot:run
```

The backend runs on:

```text
http://localhost:8080
```

## 🔐 Authentication

The application uses JWT authentication.

Authentication flow:

```text
User
  ↓
Register / Login
  ↓
AuthController
  ↓
AuthService
  ↓
BCrypt Password Verification
  ↓
JWT Token
  ↓
Frontend
  ↓
Authorization: Bearer <JWT_TOKEN>
  ↓
JwtAuthenticationFilter
  ↓
Spring Security
  ↓
Protected API
```

Passwords are stored using BCrypt hashing rather than plain text.

## 👥 Role-Based Authorization

The application supports two roles:

### USER

A normal user can:

```text
GET    /api/students          ✅
GET    /api/students/{id}     ✅

POST   /api/students          ❌
PUT    /api/students/{id}     ❌
DELETE /api/students/{id}     ❌
```

### ADMIN

An administrator can:

```text
GET    /api/students          ✅
GET    /api/students/{id}     ✅
POST   /api/students          ✅
PUT    /api/students/{id}     ✅
DELETE /api/students/{id}     ✅
```

Authorization is enforced by Spring Security on the backend.

## 🌐 API Endpoints

### Authentication

#### Register

```http
POST /api/auth/register
```

Example request:

```json
{
  "name": "John",
  "email": "john@example.com",
  "password": "password123"
}
```

#### Login

```http
POST /api/auth/login
```

Example request:

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

Successful login returns a JWT token.

## 🎓 Student APIs

### Get All Students

```http
GET /api/students
```

Requires:

```text
USER or ADMIN
```

### Get Student by ID

```http
GET /api/students/{id}
```

Requires:

```text
USER or ADMIN
```

### Add Student

```http
POST /api/students
```

Requires:

```text
ADMIN
```

Example:

```json
{
  "name": "Rahul",
  "email": "rahul@example.com",
  "department": "CSE",
  "age": 21
}
```

### Update Student

```http
PUT /api/students/{id}
```

Requires:

```text
ADMIN
```

Example:

```json
{
  "name": "Rahul Kumar",
  "email": "rahul@example.com",
  "department": "AIML",
  "age": 22
}
```

### Delete Student

```http
DELETE /api/students/{id}
```

Requires:

```text
ADMIN
```

## 🧪 Testing with cURL

### Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"name":"Test User","email":"user@example.com","password":"password123"}'
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"user@example.com","password":"password123"}'
```

Copy the JWT token returned by the login API.

### Get Students

```bash
curl http://localhost:8080/api/students \
-H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Add Student as Admin

```bash
curl -X POST http://localhost:8080/api/students \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_ADMIN_JWT_TOKEN" \
-d '{"name":"Rahul","email":"rahul@example.com","department":"CSE","age":21}'
```

## 🧪 Testing with Postman

The APIs can also be tested using Postman.

Base URL:

```text
http://localhost:8080
```

Authentication:

```text
Authorization
Type: Bearer Token
Token: YOUR_JWT_TOKEN
```

Test the following APIs:

```text
POST    /api/auth/register
POST    /api/auth/login

GET     /api/students
GET     /api/students/{id}
POST    /api/students
PUT     /api/students/{id}
DELETE  /api/students/{id}
```

## ⚠️ Validation

The backend validates incoming requests.

Student validation includes:

```text
Name        → Required
Email       → Required and valid email
Department  → Required
Age         → Required, between 1 and 120
```

Invalid requests return an appropriate HTTP error response.

## 🚨 Exception Handling

The backend uses a global exception handler:

```text
GlobalExceptionHandler
```

It handles:

* Validation errors
* Resource not found errors
* Other server exceptions

Example:

```json
{
  "message": "Student not found with id: 10"
}
```

## 🔒 Security

The project uses:

* JWT authentication
* BCrypt password hashing
* Spring Security
* Role-based authorization
* Protected student APIs
* Environment variables for secrets
* CORS configuration

Sensitive values such as:

```text
Database password
JWT secret
```

are not stored in Git.

## 🌐 CORS

The backend is configured to allow requests from the React development frontend.

Development frontend:

```text
http://localhost:5173
```

If Vite uses another port such as:

```text
http://localhost:5174
```

the backend CORS configuration supports that development port as well.

For production deployment, the CORS configuration should be changed to the actual deployed frontend URL.

## 🧩 Full-Stack Architecture

```text
                 ┌─────────────────────┐
                 │   React Frontend    │
                 │   localhost:5173    │
                 └──────────┬──────────┘
                            │
                       HTTP / REST
                            │
                            ▼
                 ┌─────────────────────┐
                 │   Spring Boot API   │
                 │   localhost:8080    │
                 └──────────┬──────────┘
                            │
                 ┌──────────┴──────────┐
                 │                     │
                 ▼                     ▼
        ┌─────────────────┐   ┌─────────────────┐
        │ Spring Security │   │   Spring Data   │
        │      + JWT      │   │      JPA        │
        └─────────────────┘   └────────┬────────┘
                                       │
                                       ▼
                              ┌─────────────────┐
                              │      MySQL      │
                              │   student_db    │
                              └─────────────────┘
```

## 🧪 Build the Project

To clean the project:

```bash
./mvnw clean
```

To build:

```bash
./mvnw clean package
```

To skip tests during packaging:

```bash
./mvnw clean package -DskipTests
```

## 📦 Maven

The project uses the Maven Wrapper.

You do not need to install Maven separately to run:

```bash
./mvnw spring-boot:run
```

or:

```bash
./mvnw clean package
```

## 🔒 Git Security

The following files should not be committed:

```text
.env
```

The project uses `.gitignore` to prevent sensitive and generated files from being committed.

Before pushing the project to GitHub, verify:

```bash
git status
```

You can also check whether `.env` is tracked:

```bash
git ls-files .env
```

This command should return nothing.

## 📌 Project Status

The backend currently includes:

* User registration
* User login
* JWT authentication
* BCrypt password hashing
* USER role
* ADMIN role
* Role-based authorization
* Student CRUD APIs
* Request validation
* Global exception handling
* MySQL integration
* CORS configuration
* Environment variable configuration
* REST API architecture

## 🔗 Frontend

The corresponding React frontend is maintained separately.

Frontend development server:

```text
http://localhost:5173
```

Backend development server:

```text
http://localhost:8080
```

## 🚀 Running the Complete Application

### Terminal 1 — MySQL

Make sure MySQL is running.

Verify:

```bash
mysqladmin -u root -p ping
```

### Terminal 2 — Backend

```bash
cd ~/Downloads/studentmanagement

set -a
source .env
set +a

./mvnw spring-boot:run
```

Backend:

```text
http://localhost:8080
```

### Terminal 3 — Frontend

```bash
cd ~/Downloads/studentmanagement-frontend
npm run dev
```

Frontend:

```text
http://localhost:5173
```

If Vite selects another port, use the URL displayed in Terminal.

## 👨‍💻 Developer

**Solthi Vyshnav**

B.Tech Student
Anurag University

## 📄 License

This project was created for educational and development purposes.

