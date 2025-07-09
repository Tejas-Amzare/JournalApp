#  Journal-App

**all execution in given below**


A safe restful backand application to manage user journal entries. Manufactured with spring boot and mongodb, is characterized by user authentication with JWT.

,

##  features
- User Registration and Login
- JWT-based certification
- Crud operation for journal entries
- Mongodb integration
- Modular Code Structure (Controller - Service - Repository)
- API Documentation with Swagger (optional)
- Postman ready for test

,

##  tech stack
- Java 17+
- Spring boot
- Spring data Mongodab
- Spring Protection + JWT
- Maven
- Mongodab Atlas
- Postman

,

## 🗂 project structure
Persnal.journalapp
├ ├ ├ controller
│ │ │ authcontroller.java
│ │ │ Jernalantroller. Jawa
├ model
│ │ user.java
│ │ │ Journalist.
├ ├ repository
│ │ │ userrepository.java
,
├ service
,
│ │ │ Journalist.
├ security
│ │ │ jwtauthfilter.java
│ │ │ jwtil.java
│ │ │ │ Securityconfig.java
└ Journalappapplication.

'

!! Application properties
spring.application.name = journalapp
spring.data.mongodb.uri = mongodb+srv: // <RE_USERNAME>:
spring.data.mongodb.database = journaldb
spring.data.mongodb.auto- India- construction = truth

**Authentication observation (jWT)**
**Register user**
➤ Endpoint: Post/API/Oth/Register
➤ Peelod:

**Jason**

  "User Name": "Raja 1010",
  "Email": "Raja@example.com",
  "Password": "Password 123"
,
**Login user**
➤ Endpoint: Post/API/Oth/Login
➤ Peelod:

Jason

,
  "User Name": "Raja 1010",
  "Password": "Password 123"
,
**➤ Response:**

Jason

,
  "Token": "Eyjhbgcioijiuzi1niisinr5cci ..."
,
Use JWT tokens for all protected closing points
➤ Add it to your request header:


Authority: Bearer <His_Tokeen>
Like access preserved API:

**Get /API /Entries - See all Journal Entries

Post /API /Entries - Create a new entry

Put/API/Entries/{ID} - Update an entry

Remove/API/Entries/{ID} - Remove an entry**
