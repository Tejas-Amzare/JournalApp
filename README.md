# 📓 JournalApp

**JournalApp** is a personal journal management system built using **Spring Boot** and **MongoDB**. It provides a secure REST API for user authentication and CRUD operations on journal entries. This project is ideal for learning backend development using Java and Spring Boot with JWT authentication.

---

##  Key Features

- **User Registration & Login**
- **JWT-based Authentication**
- **Create / Read / Update / Delete Journal Entries**
- **Spring Boot + MongoDB Integration**
- **Clean Layered Architecture**
- **RESTful API Design**

---

##  Tech Stack

- **Java 17**
- **Spring Boot 3**
- **MongoDB**
- **Spring Security + JWT**
- **Lombok**
- **Maven**

---

##  Project Structure

JournalApp/
├── config/ # Spring Security Configuration
├── controller/ # REST API Controllers
├── dto/ # Data Transfer Objects
├── exception/ # Custom Exceptions and Handlers
├── model/ # Domain Models (User, JournalEntry)
├── repository/ # MongoDB Repositories
├── service/ # Business Logic Layer
└── JournalAppApplication.java


---

##  Authentication Flow

1. Register a new user at `/api/auth/register`
2. Login at `/api/auth/login` to receive a **JWT token**
3. Use the token in API requests:


---

##  API Endpoints

| Method | Endpoint              | Description                    | Auth Required |
|--------|------------------------|--------------------------------|----------------|
| POST   | `/api/auth/register`   | Register a new user            | ❌             |
| POST   | `/api/auth/login`      | Login and get JWT token        | ❌             |
| GET    | `/api/journals`        | Get all journal entries        | ✅             |
| POST   | `/api/journals`        | Create a new journal entry     | ✅             |
| PUT    | `/api/journals/{id}`   | Update journal entry by ID     | ✅             |
| DELETE | `/api/journals/{id}`   | Delete journal entry by ID     | ✅             |

---

## ⚙ Getting Started

### Prerequisites

- Java 17+
- Maven
- MongoDB (local or cloud)

### Setup

```bash
git clone https://github.com/Tejas-Amzare/JournalApp.git
cd JournalApp
mvn clean install
mvn spring-boot:run
Visit: http://localhost:8080

📌 Notes
Make sure MongoDB is running before you start the application.

You can use Postman or Swagger UI to test endpoints.

For secure routes, include the Authorization: Bearer <token> header

🙋‍♂️ Author
Tejas Gajanan Amzare
📧 tejasamzare@gmail.com
🔗 LinkedIn
🔗 GitHub.
