# Car Rental System (Java)

This project is a console-based **Car Rental System** developed using **Java** and **Object-Oriented Programming (OOP)** principles.

The system allows users to view available cars, rent and return vehicles, manage payments, and administrate the car inventory.

---

## 🚗 Features

- List available and all cars
- Rent and return cars
- Support for multiple car types:
  - Gas cars
  - Electric cars
  - Luxury cars
- Multiple payment methods:
  - Cash
  - Card
  - Bank transfer
  - Mobile payment
- Add and remove cars from inventory
- Filter cars by brand, fuel type, and availability
- Automatic rental fee calculation using polymorphism

---

## 🧱 OOP Concepts Used

- **Encapsulation**: Class fields are private and accessed via methods
- **Inheritance**: Different car types extend the base `Car` class
- **Polymorphism**: Rental fee calculation differs per car type
- **Abstraction**: Abstract `Car` class and `Rentable` interface
- **Enums**: Used for fuel types, payment methods, and rental status

---

## 🛠 Technologies

- Java
- Eclipse IDE
- Git & GitHub

---

## ▶️ How to Run

1. Clone the repository
2. Open the project in Eclipse
3. Run `Main.java`
4. Use the console menu to interact with the system

---

## 📌 Notes

- Initial data is seeded with 50 cars
- IDs are validated to be unique
- The project is developed for an Object-Oriented Programming course
