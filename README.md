# Course Management System

A simple Java console application for managing courses and students.

## Features

### Admin Features
- Login with admin credentials
- Add/Delete students
- Add/Delete courses
- View all courses

### Student Features
- Register new account
- Login with email and password
- View available courses
- Enroll in courses
- View enrolled courses
- Drop courses

## Default Admin Credentials
- Email: admin@example.com
- Password: admin123

## Project Structure

### Models
- `User.java`: User data model with properties like userId, username, email, phone, password, userType, and enrolled courses
- `CourseModel.java`: Course data model with courseId, courseName, and courseDuration
- `LoginModel.java`: Login credentials model with email and password

### Features
- `UserManagement.java`: Handles student addition and deletion (admin functions)
- `CourseManagement.java`: Manages course creation and deletion (admin functions)
- `EnrollmentManagement.java`: Handles course enrollment, user authentication, and registration

### Utilities
- `Utils.java`: Contains helper methods for:
  - Email validation
  - Phone number validation
  - Password validation
  - Input handling
  - ID generation

### Main Application
- `Main.java`: Entry point with menu system and program flow

## How to Run
1. Compile all Java files:
```bash
javac *.java
```

2. Run the program:
```bash
java Main
```

## Data Storage
- Data is stored in memory using ArrayLists
- Data will be reset when the program is restarted