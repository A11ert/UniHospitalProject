# Hospital Management System (Java, OOP)

Console-based Hospital Management System built with OOP principles.  
You can manage **patients**, **doctors**, and **appointments** using a terminal menu.

---

## Features
- Add Patient
- Add Doctor
- Book Appointment (with date & time)
- Cancel Appointment
- View appointments for a doctor
- Add money to patient balance
- Charge patient balance
- View all people
- View all appointments

---

## Project Structure
- `menu/`
  - `TerminalInfo` (menu + user input, calls logic)
  - `Menu` (interface with `displayMenu()` and `run()`)

- `model/`
  - `Person` (abstract parent class)
  - `Patient`, `Doctor` (child classes)
  - `Appointment`, `AppointmentStatus` (appointment model)

- `exception/` (optional if used)
  - Custom exceptions (if you created any)

---

## OOP Concepts Used
- **Inheritance:** `Patient` and `Doctor` extend `Person`
- **Polymorphism:** List of `Person` can store both `Doctor` and `Patient`
- **Abstraction:** `Person` is abstract (common fields + required methods)
- **Encapsulation:** fields are private/protected and validated using setters
- **Interface:** `Menu` (and `Payable` if you use it)

---


