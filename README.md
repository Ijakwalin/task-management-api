**Task Management API**
This repository provides a Task Management API for managing tasks with CRUD operations. Follow the steps below to set up, run, and test the project.

**Prerequisites**
Ensure you have the following installed:
Java 11+
Maven 3.6+
Git

**Setup**
Clone the repository:
git clone https://github.com/Ijakwalin/task-management-api.git
cd task-management-api

**Build the project**
mvn clean install

**Run the application**
mvn spring-boot:run

**Testing**
Ensure test classes are in src/test/java:

Move any misplaced test files from src/main/java to src/test/java (e.g., TaskServiceTest and TaskControllerTest).
mkdir -p src/test/java/com/example/taskmanagement
mv src/main/java/com/example/taskmanagement/TaskServiceTest.java src/test/java/com/example/taskmanagement/
mv src/main/java/com/example/taskmanagement/TaskControllerTest.java src/test/java/com/example/taskmanagement/

**Run tests**
mvn test

**API Documentation**
Once the application is running, use tools like Postman or cURL to interact with the endpoints.
Default server: http://localhost:8080

GET	/tasks	Get all tasks
POST	/tasks	Create a new task
GET	/tasks/{id}	Get task by ID
PUT	/tasks/{id}	Update task by ID
DELETE	/tasks/{id}	Delete task by ID

**Cleanup**
To stop the application:
CTRL + C

To clean build artifacts:
mvn clean
