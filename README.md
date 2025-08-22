📌 3. JournalApp – README  

# 📔 Journal App Backend

## 📖 Overview
A backend application for maintaining **personal journals**.  
Users can securely **register, log in, and manage journal entries**.  

## 🛠 Tech Stack
- **Language:** Java 17  
- **Framework:** Spring Boot 3  
- **Database:** MySQL  
- **Authentication:** JWT (Spring Security)  
- **Documentation:** Swagger  

## ✨ Features
- 🔐 User Authentication (Register/Login with JWT)  
- 📝 Create, Update, Delete journal entries  
- 📅 Fetch all journal entries for a user  

## 📂 Project Structure

---
journal-app/
┣ src/main/java/com/journalapp/
┃ ┣ config/ # JWT Security config
┃ ┣ controller/ # REST endpoints
┃ ┣ entity/ # Models (User, JournalEntry)
┃ ┣ repository/ # JPA Repositories
┃ ┣ service/ # Business logic
┃ ┗ JournalAppApplication.java
┣ src/main/resources/
┃ ┣ application.properties
┣ pom.xml
┗ README.md

---

##  Authentication Flow

1. Register a new user at `/api/auth/register`
2. Login at `/api/auth/login` to receive a **JWT token**
3. Use the token in API requests:


---

## 🔑 API Endpoints (Sample)
### Auth
- `POST /api/auth/register` → Register  
- `POST /api/auth/login` → Login  

### Journal
- `POST /api/journals` → Create journal entry  
- `GET /api/journals` → Get all entries  
- `PUT /api/journals/{id}` → Update entry  
- `DELETE /api/journals/{id}` → Delete entry  

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
Architecture Overview: JournalApp
┌──────────────┐        ┌──────────────┐         ┌──────────────┐        ┌──────────────┐
│  Clients     │  <--  │  Controllers │  <--   │  Services    │  <--   │  Repositories│
│ (Web / Postman)│     │ (@RestController) │     │ (@Service)  │         │ (@Repository)│
└──────────────┘        └──────────────┘         └──────────────┘        └──────────────┘
                                                                     ↓
                                                              ┌──────────────┐
                                                              │   MongoDB    │
                                                              │ (NoSQL Store)│
                                                              └──────────────┘

## ⚙️ Setup & Installation
1. Clone repo:  
   ```bash
   git clone https://github.com/Tejas-Amzare/JournalApp.git
   cd JournalApp

2. Configure `application.properties`:  
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/journalapp
   jwt.secret=yourSecretKey


3.Run with Maven:

mvn spring-boot:run

📌 Notes
Make sure MongoDB is running before you start the application.

📸 Screenshots
Swagger UI := (Postman API tests)
<img width="1730" height="853" alt="1" src="https://github.com/user-attachments/assets/29da3181-8ae9-4f2a-9f16-7f4c181e6c79" />
<img width="1713" height="588" alt="2" src="https://github.com/user-attachments/assets/b08fe267-9769-48cc-bff1-312d9b65b94c" />
<img width="1729" height="389" alt="3" src="https://github.com/user-attachments/assets/4d62690c-f38e-4ad2-a948-2a8ccac19e64" />





🙋‍♂️ Author
Tejas Gajanan Amzare
📧 tejasamzare@gmail.com
🔗 LinkedIn -https://www.linkedin.com/in/tejas-amzare/

