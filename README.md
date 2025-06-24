# Trip Reservation Application

This project is a reservation system developed in Java. Through the graphical user interface, users can make bus and flight reservations, as well as view and edit their existing bookings.
The system supports both passenger and administrator roles. Administrators can add or remove trips, vehicles, and users, and manage existing data efficiently.

# Features

-User registration and login system
-Bus/flight search functionality
-Seat selection and reservation
-Trip and vehicle management via admin panel
-Modern Java Swing user interface

# Technologies Used

- Java  
- Java Swing  
- Object-Oriented Programming (OOP)  
- Design Patterns (Factory, Singleton, Builder, Prototype)


# Project Structure

├── src/
│   ├── main/
│   ├── gui/
│   ├── reservation/
    |  ├── repository/
    |  ├── service/
    |  ├── model/
    |  ├── vehicle/

│   ├── trip/
    |  ├── repository/
    |  ├── service/
    |  ├── model/
    |  ├── vehicle/

│   ├── vehicle/
    |  ├── repository/
    |  ├── service/
    |  ├── model/
    |  ├── vehicle/
│   └── user/
    |  ├── Repository/
    |  ├── Service/
    |  ├── model/
    |  ├── vehicle/


# Architecture Overview

The project follows a layered architecture with clear separation of concerns:

- Model Layer : Contains data classes that represent core entities such as `User`, `Trip`, `Vehicle`, and `Reservation`.
- Repository : Responsible for storing and retrieving data for each model. Each entity has its own repository (e.g., `TripRepository`, `UserRepository`).
- Service : Handles the application logic and validates data before interacting with the repositories. Services ensure rules and constraints are enforced consistently across the system.
