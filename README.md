# 📔 JournalApp Backend: Secure Personal Journaling Platform

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/Tejas-Amzare/JournalApp)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)
[![Java Version](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-blueviolet)](https://spring.io/projects/spring-boot)

---

## 📖 Overview
**JournalApp** is a backend application for maintaining **secure personal journals**.  
Users can **register, log in, and manage journal entries** using REST APIs secured with JWT authentication.  

It’s a **showcase of modern backend development practices** and design patterns in Spring Boot 3.

---

## 📌 Table of Contents
- [Tech Stack](#-tech-stack)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [Authentication Flow](#-authentication-flow)
- [API Endpoints](#-api-endpoints)
- [Architecture](#-architecture)
- [Setup & Installation](#-setup--installation)
- [Usage](#-usage)
- [Visuals](#-visuals)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

---

## 🛠 Tech Stack
- **Language:** Java 17  
- **Framework:** Spring Boot 3  
- **Database:** MongoDB  
- **Authentication:** JWT (Spring Security)  
- **Documentation:** Swagger / OpenAPI  

---

## ✨ Features
- 🔐 Secure User Authentication (Register/Login with JWT)  
- 📝 Create, Update, Delete journal entries  
- 📅 Fetch all journal entries for a user  
- ⚡ Health-check endpoint for monitoring  

---

## 📂 Project Structure
journal-app/
┣ src/main/java/com/journalapp/
┃ ┣ config/ # JWT Security config
┃ ┣ controller/ # REST endpoints
┃ ┣ entity/ # Models (User, JournalEntry)
┃ ┣ repository/ # JPA / Mongo Repositories
┃ ┣ service/ # Business logic
┃ ┗ JournalAppApplication.java
┣ src/main/resources/
┃ ┗ application.properties
┣ pom.xml
┗ README.md



---

## 🔑 Authentication Flow
1. Register a new user: `POST /api/auth/register`  
2. Login to receive a JWT token: `POST /api/auth/login`  
3. Use the JWT token in `Authorization: Bearer <TOKEN>` header for all protected endpoints.

---

## 💻 API Endpoints

### Auth
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST   | /api/auth/register | Register new user | ❌ |
| POST   | /api/auth/login    | Login & get JWT token | ❌ |

### Journal
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET    | /api/journals | Get all journal entries | ✅ |
| POST   | /api/journals | Create new journal entry | ✅ |
| PUT    | /api/journals/{id} | Update journal entry by ID | ✅ |
| DELETE | /api/journals/{id} | Delete journal entry by ID | ✅ |

### User
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET    | /user/getAll | Get all users | ✅ |
| POST   | /user/post   | Create a new user | ✅ |
| PUT    | /user/{username} | Update user details | ✅ |
| DELETE | /user/{id}   | Delete a user | ✅ |

### Health Check
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /healthCheck | Server health status |

---

## 🏗 Architecture Overview

┌──────────────┐
│ Clients │ <-- Web / Postman
└──────┬───────┘
│
┌──────▼────────┐
│ Controllers │ (@RestController)
└──────┬────────┘
│
┌──────▼────────┐
│ Services │ (@Service)
└──────┬────────┘
│
┌──────▼────────┐
│ Repositories │ (@Repository)
└──────┬────────┘
│
┌──────▼────────┐
│ MongoDB │ (NoSQL Store)
└───────────────┘


---

## ⚙️ Setup & Installation
### Prerequisites
- Java 17
- Maven 3+
- MongoDB installed and running

### Steps
1. Clone the repository:

git clone https://github.com/Tejas-Amzare/JournalApp.git
cd JournalApp
Configure application.properties:


spring.data.mongodb.uri=mongodb://localhost:27017/journalapp
jwt.secret=yourSecretKey
Run the application:


mvn spring-boot:run
💻 Usage Examples
Register a User

POST /api/auth/register
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "Password123"
}

Login

POST /api/auth/login
Content-Type: application/json

{
  "username": "johndoe",
  "password": "Password123"
}
Create a Journal Entry (Authorized)

POST /api/journals
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "title": "My First Entry",
  "content": "Today I learned Spring Boot!"
}
📸 Visuals
Swagger UI

<img width="1730" height="853" alt="1" src="https://github.com/user-attachments/assets/fbfc768b-cfc2-4373-803f-066580d8a6e9" />


Sample Postman Requests

<img width="1713" height="588" alt="2" src="https://github.com/user-attachments/assets/7a2a8778-31c3-4c29-b4ca-4a405de71ac0" />

<img width="1729" height="389" alt="3" src="https://github.com/user-attachments/assets/ba0ad942-64ee-4f85-881f-62047b3af034" />

🤝 Contributing
Contributions are welcome! Please:

Fork the repository

Create a feature branch (git checkout -b feature-name)

Commit your changes (git commit -m "Add feature")

Push to your branch (git push origin feature-name)

Open a Pull Request

📝 License
This project is licensed under the MIT License - see LICENSE file for details.

📫 Contact
Tejas Gajanan Amzare


📧 Email: tejasamzare@gmail.com


🔗 LinkedIn: https://www.linkedin.com/in/tejas-amzare
