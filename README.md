# AI-Powered Student Council Voting System

A comprehensive Java Desktop Application for managing student council elections. Built with JavaFX and Maven, this application provides an intuitive user interface for students to cast their votes, candidates to register, and administrators to oversee the election process with AI-based fraud detection.

## Features

- **Robust User Authentication**: Secure login and registration for both Admins and Students.
- **Student Board**: A dedicated dashboard for students to view candidates and securely cast their votes.
- **Admin Panel**: Manage the election, candidates, and oversee operations.
- **Results Dashboard**: Real-time view of election results with graphical charts.
- **AI Fraud Detection**: Built-in mechanisms to detect and log suspicious voting patterns automatically.

## Tech Stack

- **Java Version**: 25
- **UI Framework**: JavaFX (v25) for high-performance responsive desktop UI
- **Build System**: Maven
- **Database**: H2 Database (Embedded)
- **Testing**: JUnit 5

## Prerequisites

- JDK 25 installed on your machine.
- Maven installed and added to your system PATH.

## Getting Started

1. **Clone the Repository**
   ```bash
   git clone https://github.com/SamyakJain29/Student-Council-voting-system.git
   cd Student-Council-voting-system
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn javafx:run
   ```

## License

This project is open-source and available under the terms of the MIT License.
