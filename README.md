
# Employee Management System

## About the Project

Employee Management System is a full-stack web application developed using React, Spring Boot, and PostgreSQL. The application provides a centralised platform for managing employees, departments, attendance, and leave requests.

The system implements JWT-based authentication and role-based authorisation with two roles: ADMIN and EMPLOYEE. Administrators can manage employees, departments, attendance, and leave requests, while employees can access their own attendance and leave information.

The project follows a layered architecture using Controller, Service, Repository, DTO, and Mapper components to maintain clean, modular, and maintainable code.

The React frontend communicates with the Spring Boot backend through RESTful APIs using Axios.

## Key Features

### Authentication and Security

- JWT-based authentication
- Secure password storage using BCrypt
- Role-based authorisation
- ADMIN and EMPLOYEE roles
- Stateless authentication using Spring Security
- Protected REST APIs
- Automatic JWT validation for authenticated requests
- Automatic redirection to login when the JWT expires

### Employee Management

- Add employee
- View employees
- View employee details
- Update employee details
- Delete employee
- Assign employees to departments
- Search employees by name
- Search employees by email
- Sort employees by name, salary, and joining date
- Filter employees by:
  - Department
  - Status
  - Gender
  - Salary range
  - Joining date
- Pagination

### Department Management

- Add department
- View departments
- Update department details
- Delete department
- Assign employees to departments

### Leave Management

- Apply for leave
- View leave records
- Employees can view their own leaves
- Administrators can view employee leave records
- Approve leave requests
- Reject leave requests
- Role-based leave management

### Attendance Management

- Employee check-in
- Employee check-out
- View attendance records
- Employees can view their own attendance
- Administrators can view all attendance records
- Attendance by employee
- Attendance by date
- Delete attendance records for authorised users

### Dashboard

The dashboard provides different information based on the logged-in user's role.

#### Admin Dashboard

- Total employees
- Total departments
- Active employees
- Inactive employees
- Employee management
- Department management
- Leave management

#### Employee Dashboard

- Employee email
- Employee role
- My Leaves
- My Attendance

## Technologies Used

### Frontend

- React.js
- React Router
- Axios
- Bootstrap
- JavaScript
- JWT Decode

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Jakarta Validation
- Maven
- JWT

### Database

- PostgreSQL

## Architecture

The application follows a layered architecture:

```text
React Frontend
      |
    Axios
      |
   REST APIs
      |
 Controller Layer
      |
 Service Layer
      |
 DTO / Mapper
      |
 Repository Layer
      |
 PostgreSQL
