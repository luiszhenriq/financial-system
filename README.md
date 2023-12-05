# Financial System

This API was made with the aim of helping the user with financial control



## Prerequisites:
- Java 17:
Ensure that you have JDK 17 installed on your machine. You can verify this by running the command java -version in the terminal.

- PostgreSQL:
Install PostgreSQL and create a database for your project.

- Database Configuration:
Update the database settings in the application.properties or application.yml file in your Spring project.


### Steps to Run the Project:
- Clone the Repository:
1. git clone https://github.com/luiszhenriq/financial-system.git


Database Configuration:
Open the file src/main/resources/application.properties and update the database settings, including URL, username, and password.

- Compile the Project:
./mvnw clean install

- Run the Project:
./mvnw spring-boot:run

Spring Boot will start the application on the default port (usually 8080).

Access the Application:
Open your web browser and navigate to http://localhost:8080 to verify that the application is running.

## Using JWT for Authentication:
Authentication with JWT:
The project requires authentication via JWT. Ensure you include a valid token in authenticated requests
### Authentication Controller


# API ENDPOINTS:

|Rest |Function |
|------ | ------- |
|POST  | Login |
|POST   | Register |


### Financial Controller

|Rest |Function |
|------ | ------- |
|POST  | Create Expense  |
|GET   | Consult Expense by ID |
|GET   | Consult All Expenses |
|GET   | Consult Expense by Name|
|PUT   | Update Expense |
|DELETE  | Delete Expense by ID|
