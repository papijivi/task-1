--> Task Management REST API (Spring Boot + MongoDB)

This project is a Spring Boot application that provides a REST API for managing "tasks" representing shell commands. Each task can have multiple executions, which are also tracked and stored.

--> Features

- Create, view, and delete tasks
- Execute shell commands and record their output
- Search tasks by name
- MongoDB integration for persistence



--> Technologies Used

- Java 23
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven
- Postman (for testing)

---

--> Endpoint

- Delete(http://localhost:8080/tasks/t1)
-  PUT (http://localhost:8080/tasks)
-  GET( http://localhost:8080/tasks/search?name=Print)
