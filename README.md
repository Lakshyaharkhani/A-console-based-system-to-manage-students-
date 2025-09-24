# Course Management System

A simple Java console application for managing courses and students.

## Features

### Admin Features
- Login with admin credentials
- Add/Delete students (students receive unique 10-digit IDs)
- Add/Delete courses
- View all courses
- Add/View/Delete assignments per course
- Search students by ID, name (partial), or email with detailed output

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
  - Course/assignment ID generation (int)
  - Unique 10-digit user ID generation (long)

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

## Usage Tips
- Use the Admin menu to add students and courses first.
- When adding a student, a unique 10-digit ID is assigned automatically.
- To search students, choose "Search Students" and enter one of:
  - A 10-digit ID (exact match)
  - A name or partial name (case-insensitive)
  - An email (exact match, case-insensitive)
- Search results show: ID, Name, Email, Phone, and enrolled courses (with names and IDs).

## Data Storage
- Data is stored in memory using ArrayLists
- Data will be reset when the program is restarted

## Troubleshooting
- If running the app in a non-interactive context, you may see: "No more input. Exiting." Run it in a console and provide input as prompted.