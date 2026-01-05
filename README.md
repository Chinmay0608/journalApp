📓 Personal Journal App

A full-stack application for managing personal journal entries, featuring a robust Spring Boot REST API backend and a native Android (Kotlin) frontend.

🚀 Overview

This project demonstrates modern backend engineering practices including DTO patterns, Input Validation, and NoSQL database integration. It is paired with a native Android client to demonstrate full-stack capabilities within the Java/JVM ecosystem.

🛠️ Tech Stack

Backend (Server)

Language: Java 17+

Framework: Spring Boot 3.x

Database: MongoDB (NoSQL)

API: RESTful Web Services

Validation: Spring Boot Starter Validation

Documentation: Swagger UI / OpenAPI

Frontend (Mobile)

Platform: Android (Native)

Language: Kotlin

Networking: Retrofit 2 & GSON

IDE: Android Studio

⚙️ Features

CRUD Operations: Create, Read, Update, and Delete journal entries.

Data Validation: Backend ensures data integrity (e.g., non-empty titles) using @Valid and Bean Validation.

Architecture: Separation of concerns using Controller-Service-Repository pattern.

Mobile Integration: Android client communicates securely with the backend via REST.

🏃‍♂️ Getting Started

Prerequisites

Java Development Kit (JDK) 17 or higher

MongoDB installed and running locally on port 27017

Android Studio (for the mobile app)

1. Setup the Backend

Clone the repository.

Navigate to the backend directory.

Configure your MongoDB connection in src/main/resources/application.properties:

spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
spring.data.mongodb.auto-index-creation=true


Run the application:

./mvnw spring-boot:run


Access Swagger UI to test endpoints: http://localhost:8080/swagger-ui.html

2. Setup the Android App

Open the JournalAppClient folder in Android Studio.

Allow Gradle to sync dependencies.

Important: If using the Android Emulator, the backend URL is set to http://10.0.2.2:8080 (this maps to your computer's localhost).

Run the app on an Emulator (e.g., Pixel 4 API 30).


📂 Project Structure

├── src/main/java/com/example/journalApp
│   ├── controller  # API Layer (Requests/Responses)
│   ├── service     # Business Logic
│   ├── repository  # Database Interaction (MongoRepository)
│   ├── entity      # Database Models
│   └── dto         # Data Transfer Objects
└── JournalAppClient (Android)
    ├── app/src/main/java
    │   ├── api     # Retrofit Interface
    │   ├── model   # Kotlin Data Classes
    │   └── ui      # Activities & Layouts


🔮 Future Roadmap

[ ] Implement User Authentication (Spring Security + JWT).

[ ] Add Search and Filtering for entries.

[ ] Polish Android UI with RecyclerView and Material Design.

[ ] Write Unit Tests (JUnit & Mockito).

Chinmay Maheshwari
