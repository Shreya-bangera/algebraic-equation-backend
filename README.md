📘 Algebraic Equation Backend

Spring Boot REST API – Store & Solve Algebraic Expressions

🚀 Project Overview

1. This project is a Spring Boot REST API that allows users to:

2. Store algebraic equations

3. Convert infix expressions to postfix

4. Construct an expression tree

5. Evaluate equations using variable substitution

6. Retrieve stored equations

7. The application uses in-memory storage and follows proper REST architecture.



🧠 Features Implemented

✅ Infix → Postfix conversion
✅ Expression Tree construction
✅ Multi-variable support (x, y, z, etc.)
✅ Power operator (^) support
✅ In-memory storage using ConcurrentHashMap
✅ Proper REST endpoints
✅ JSON request & response
✅ Error handling with correct HTTP status codes

🏗️ Project Structure

src/main/java/com/example/algebraicevaluator
│
├── controller
│     └── HelloController.java
│
├── service
│     └── ExpressionService.java
│
├── model
│     ├── Equations.java
│     └── ExpressionNode.java
│
└── AlgebraicEvaluatorApplication.java



🔢 Supported Operators

+ Addition

- Subtraction

* Multiplication

/ Division

^ Power

Supports expressions like:

x + y
x^2 + y^2 - 4
x*y + z^3 - 7
a^2 + b^2 + c^2

🌐 API Endpoints

1️⃣ Store Equation

Stores equation and builds expression tree.

Endpoint
POST /api/equations/store

Request Body
{
  "equation": "x^2 + y^2 - 4"
}

Response
{
  "message": "Equation stored successfully",
  "equationId": 1
}


2️⃣ Get All Stored Equations
Endpoint
GET /api/equations

Response
{
  "equations": [
    {
      "equationId": 1,
      "equation": "x^2 + y^2 - 4"
    }
  ]
}


3️⃣ Evaluate Equation

Evaluates stored equation with variable values.

Endpoint
POST /api/equations/{id}/evaluate

Example
POST /api/equations/1/evaluate

Request Body
{
  "variables": {
    "x": 3,
    "y": 4
  }
}

Response
{
  "equationId": 1,
  "equation": "x^2 + y^2 - 4",
  "variables": {
    "x": 3,
    "y": 4
  },
  "result": 21
}

🛠️ Technologies Used

Java 17

Spring Boot 3

Maven

REST API


Postman (for testing)

▶️ How to Run
1️⃣ Clone Repository
git clone https://github.com/Shreya-bangera/algebraic-equation-backend.git

2️⃣ Navigate to Project
cd algebraic-evaluator

3️⃣ Run Application

Windows:

mvnw.cmd spring-boot:run


Mac/Linux:

./mvnw spring-boot:run


Application will start at:

http://localhost:8080

🧪 Testing

Use Postman to test:

1. Store equation

2. Retrieve equations

3. Evaluate equation

All endpoints accept and return JSON.

⚠️ Error Handling

The API handles:

1. Missing equation input → 400 Bad Request

2. Invalid equation ID → 404 Not Found

3. Missing variables → 400 Bad Request

Division by zero → 400 Bad Request
