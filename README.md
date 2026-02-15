📘 Algebraic Equation Backend

Spring Boot – Postfix Expression Tree Implementation

📌 Project Overview

This project is a RESTful Spring Boot application that:

Stores algebraic equations

Converts them into postfix notation

Builds an expression tree

Evaluates equations using variable substitution

Uses in-memory storage

Supports JSON request/response

Testable via Postman

The application follows the requirements specified in the backend assignment.

🧠 Core Features
1️⃣ Store Algebraic Equation

Converts infix expression to postfix

Builds an expression tree

Stores equation in memory

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

2️⃣ Retrieve Stored Equations

Returns all stored equations reconstructed from expression trees.

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

Evaluates a stored equation using variable substitution.

Endpoint

POST /api/equations/{equationId}/evaluate


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

🏗️ Architecture & Design

Project Structure:

controller/
service/
model/

🔹 Controller

Handles REST API endpoints.

🔹 Service

Tokenization

Infix → Postfix conversion

Expression Tree construction

Evaluation logic

🔹 Model

Equation model

Expression Tree Node

🔢 Supported Operators

+

-

*

/

^ (power)

Supports:

Multiple variables (x, y, z, etc.)

Multi-digit numbers

Expressions like:

3x + 2y - z

x^2 + y^2 - 4

x*y + z^3 - 7

🛠️ Technologies Used

Java 17

Spring Boot 3

Maven

REST APIs

Postman for testing

In-memory storage (ConcurrentHashMap)





▶️ How to Run the Project
1️⃣ Clone Repository
git clone https://github.com/Shreya-bangera/algebraic-equation-backend.git

2️⃣ Navigate to Project
cd algebraic-evaluator

3️⃣ Run Application

Windows:

mvnw.cmd spring-boot:run
OR
./mvnw spring-boot:run


Application runs at:

http://localhost:8080

🧪 Testing via Postman

Test the following endpoints:

Store equation

Retrieve equations

Evaluate equation

All APIs are JSON based.



⚠️ Error Handling

The application handles:

Missing equation input

Invalid equation syntax

Missing variable values

Division by zero

Invalid equation ID



Returns proper HTTP status codes:

400 – Bad Request

404 – Not Found

500 – Internal Error
