# RentRead API

This API provides endpoints for managing books, users, and rental operations for a book rental service.

## Tech Stack

- **Java**: Core programming language
- **Spring Boot**: Framework for building the application
- **Spring Security**: For authentication and authorization
- **Gradle**: Build automation tool
- **MySQL**: Database management system
- **Hibernate**: ORM (Object-Relational Mapping) framework

## Setup

To set up and run the RentRead API locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/vaishaliggohil/RentRead.git
   ```
2. Navigate to the project directory:
   ```bash
   cd RentRead
   ```
3. Build the project using Gradle:
   ```bash
   gradle clean build
   ```
4. Run
   ```java
   ./gradlew bootRun
   ```

### API Endpoints

| Method | Endpoint                  | Description                          | Access Level |
|--------|---------------------------|--------------------------------------|--------------|
| POST   | `/unauth/register/user`   | Register a new user                  | Public       |
| POST   | `/unauth/register/admin`  | Register a new admin                 | Public       |
| POST   | `/manage/book`            | Add a book into the books repository | Admin        |
| GET    | `/book/available`         | Get all available books              | User/Admin   |
| GET    | `/books`                  | Get all books                        | Admin Only   |
| PUT    | `/rent/borrow/{bookId}`   | Rent a book                          | User Only    |
| POST   | `/books/{bookId}/return`  | Return a rented book                 | User Only    |
| DELETE | `/rent/return/1`          | Delete a book                        | Admin Only   |

## Error Handling

The API handles common errors and returns appropriate HTTP status codes:
- **404** - Resource not found (e.g., book or user not found)
- **400** - Bad request (e.g., invalid input)
- **401** - Unauthorized (e.g., accessing private endpoints without login)
- **403** - Forbidden (e.g., insufficient permissions)

### Authentication

- The API uses **Basic Authentication**.
- After registering, you can log in by providing the username (email) and password in the request header using Postman or any API client.
- Once logged in, use the generated session to make authorized API calls without resending credentials for some time.

### Configuration

- The application uses **H2 in-memory database** for persistence. No additional setup is required.
- **Spring Security** is used for securing the API with Basic Authentication.

### Unit Testing

Unit tests are included and cover key functionalities like user registration, book management, and rental transactions.

### Postman Collection

https://api.postman.com/collections/4142051-b3692df3-c5df-4f03-a7e3-e9f891588c4a?access_key=PMAT-01JB2QNCNF099XVDBSR3E4JY4B
