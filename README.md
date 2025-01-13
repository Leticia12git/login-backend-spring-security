# Login Project with Spring Security

This project is a basic implementation of a user login system using **Spring Security**. It focuses on securing user authentication and authorization processes and includes comprehensive unit tests to ensure the robustness of the application.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features
- User registration and login functionality
- Role-based access control (ADMIN and USER roles)
- Password encryption with BCrypt
- JWT-based authentication for session management
- Unit tests for service and repository layers

## Technologies Used
- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **JPA/Hibernate**
- **MYSqL** (for development and testing)
- **JUnit 5** and **Mockito** (for unit testing)


## Setup
To run this project locally, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/login-project.git
   cd login-project


2. Configure the application properties: Edit the application.properties file to set up the database and security configurations as needed.


3. Build the project:
   ./mvnw clean install

4. Run the application:
./mvnw spring-boot:run

5. Running the Application
Once the application is up and running, you can access the API at http://localhost:8080.

Testing

This project includes unit tests to ensure the proper functioning of the login and user management features. The tests cover various scenarios including:

- Successful user login
- Login failure due to incorrect credentials
- User registration with and without errors
- Authorization based on user roles
- To run the tests, execute the following command:

To run the tests, execute the following command:
./mvnw test

Example Test Cases
Successful Login Test

Verifies that a user with correct credentials can log in successfully.
Login Failure Test

Ensures that an exception is thrown when attempting to log in with incorrect credentials.
User Registration Test

Validates that a new user can be registered and saved in the database.
Authorization Test

Checks that only users with the appropriate role can access specific resources.
Project Structure
The project follows a typical Spring Boot structure:



src
├── main
│   ├── java
│   │   └── com.example.loginproject
│   │       ├── controller
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       └── security
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
└── test
└── java
└── com.example.loginproject
├── service
└── repository
Contributing
Contributions are welcome! Please fork this repository and submit a pull request with your changes.

License
This project is licensed under the MIT License. See the LICENSE file for more information.
