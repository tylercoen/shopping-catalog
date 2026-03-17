# 🛒 Shopping Catalog Web Application

A Java-based dynamic web application that simulates an online shopping experience with user authentication, product browsing, and cart management.

This project was built using Java Servlets, JSP-style HTML generation, and follows a layered architecture with unit and integration testing.

---

## 🚀 Features

### 🔐 User Authentication
- Register new users
- Login with session management
- Logout functionality
- Session validation and protection

### 📦 Product Catalog
- Static catalog page for browsing products
- Add items to cart from catalog

### 🛍️ Shopping Cart
- Add items to cart
- Increment item quantity
- View cart summary
- Display total price per item
- Persist cart data on server shutdown

### 💾 Persistence Layer
- User data loaded from database abstraction
- Cart data saved and restored via `DatabaseManager`

### 🧪 Testing
- Unit tests using **JUnit**
- Mocking servlet behavior with **Mockito**
- Coverage includes:
  - `UserManager`
  - `AccessServlet`
  - `CartManager`

---

## 🏗️ Tech Stack

- **Java**
- **Java Servlets (javax.servlet-api)**
- **Apache Tomcat**
- **Maven**
- **JUnit 4**
- **Mockito**

---

## 📁 Project Structure
# 🛒 Shopping Catalog Web Application

A Java-based dynamic web application that simulates an online shopping experience with user authentication, product browsing, and cart management.

This project was built using Java Servlets, JSP-style HTML generation, and follows a layered architecture with unit and integration testing.

---

## 🚀 Features

### 🔐 User Authentication
- Register new users
- Login with session management
- Logout functionality
- Session validation and protection

### 📦 Product Catalog
- Static catalog page for browsing products
- Add items to cart from catalog

### 🛍️ Shopping Cart
- Add items to cart
- Increment item quantity
- View cart summary
- Display total price per item
- Persist cart data on server shutdown

### 💾 Persistence Layer
- User data loaded from database abstraction
- Cart data saved and restored via `DatabaseManager`

### 🧪 Testing
- Unit tests using **JUnit**
- Mocking servlet behavior with **Mockito**
- Coverage includes:
  - `UserManager`
  - `AccessServlet`
  - `CartManager`

---

## 🏗️ Tech Stack

- **Java**
- **Java Servlets (javax.servlet-api)**
- **Apache Tomcat**
- **Maven**
- **JUnit 4**
- **Mockito**

---

## 📁 Project Structure
shopping-catalog/
│
├── src/main/java/
│ ├── model/
│ │ ├── User.java
│ │ ├── UserManager.java
│ │ ├── CartItem.java
│ │ ├── CartManager.java
│ │ ├── DatabaseManager.java
│ │ └── CartSummaryHtmlGenerator.java
│ │
│ └── servlet/
│ ├── AccessServlet.java
│ └── CartServlet.java
│
├── src/main/webapp/
│ ├── login.html
│ ├── catalog.html
│ ├── styles.css
│ └── images/
│
├── src/test/java/
│ ├── UserManagerTest.java
│ ├── AccessServletTest.java
│ └── CartManagerTest.java
│
└── pom.xml

## ⚙️ Setup & Installation

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/shopping-catalog.git
cd shopping-catalog

## ⚙️ Setup & Installation

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/shopping-catalog.git
cd shopping-catalog

/shopping-catalog

4. Run the Application

Start the server and navigate to:
http://localhost:8080/shopping-catalog/login.html

🧪 Running Tests
In Eclipse

Right-click test class → Run As → JUnit Test

Using Maven

mvn test
🔄 Application Flow

User registers via /login.html

Logs in through AccessServlet

Redirected to catalog.html

Adds items to cart via CartServlet

Views cart summary at /cart

Logs out (session invalidated)

⚠️ Key Implementation Details
Session Handling

Sessions are validated on every request

Invalid sessions are redirected to login

Cart Storage

Uses:
Map<String, Map<CartItem, Integer>>

Tracks cart items per user

Maintains quantity counts

HTML Rendering

Dynamic cart HTML generated via CartSummaryHtmlGenerator

Error Handling

Graceful handling of invalid input

HTTP error responses for failures

🧠 Lessons Learned

Building REST-like servlet controllers

Managing session state securely

Structuring Java web applications without frameworks

Writing unit tests for servlets using Mockito

Handling object equality (equals & hashCode) for map keys

🔮 Future Improvements

Replace HTML string building with JSP or Thymeleaf

Add database (PostgreSQL/MySQL)

Implement password hashing (e.g., BCrypt)

Add frontend framework (React)

Refactor into REST API (Spring Boot)
