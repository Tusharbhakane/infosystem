# Student Management System

A comprehensive student information management system built with Java, JavaFX, and MySQL. This application allows you to manage student records, including personal details, academic performance, and attendance.

## Features

- Student Management
  - Add new students
  - View student details
  - Update student information
  - Delete student records

- Course Management
  - Add new courses
  - View course details
  - Manage course enrollment

- Attendance Tracking
  - Record student attendance
  - Generate attendance reports

- Grade Management
  - Record student grades
  - Generate grade reports

## Prerequisites

- Java 11 or higher
- MySQL Server 8.0 or higher
- Maven 3.6 or higher

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd student-management-system
   ```

2. Create the MySQL database:
   - Open MySQL command line or MySQL Workbench
   - Run the SQL script located at `src/main/resources/database/schema.sql`

3. Configure database connection:
   - Open `src/main/java/com/student/management/util/DatabaseConnection.java`
   - Update the database URL, username, and password according to your MySQL setup

4. Build the project:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn javafx:run
   ```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── student/
│   │           └── management/
│   │               ├── controller/
│   │               ├── model/
│   │               └── util/
│   ├── resources/
│   │   ├── fxml/
│   │   └── database/
└── test/
    └── java/
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
