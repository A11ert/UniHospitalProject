# Hospital Management System (Java, Console)

A simple **console-based Hospital Management System** built for a university project.  
The program allows you to manage **patients**, **doctors**, and **appointments**, including booking and cancelling appointments and handling patient balances.

---

## Features

- **Add Patient**
  - Stores: `id`, `name`, `age`, `balance`
  - Validates input (positive ID, non-empty name, age > 0, balance ≥ 0)
  - Prevents duplicate patient IDs

- **Add Doctor**
  - Stores: `id`, `name`, `maxAppointmentsPerDay`
  - Validates input and prevents duplicate doctor IDs

- **Book Appointment**
  - Links an appointment to an existing **patient** and **doctor**
  - Parses appointment start time in format: `YYYY-MM-DD HH:MM`
  - Duration is in minutes and must be ≥ 1
  - Prevents overlapping appointments for the same doctor
  - Prevents duplicate appointment IDs

- **Cancel Appointment**
  - Marks an appointment as cancelled

- **View Doctor Appointments**
  - Prints all appointments for a specific doctor in a table format

- **Patient Balance Management**
  - Add money to a patient's balance
  - Charge a patient (only if balance is enough)

---

## Project Structure (Classes)

- `Main`
  - Starts the application and reads menu choices in a loop.

- `TerminalInfo`
  - Console UI + main logic.
  - Stores all lists:
    - `List<Patient> allpatients`
    - `List<Doctor> alldoctor`
    - `List<Appointment> allappointment`
  - Contains methods for each menu action.

- `person`
  - Base class for `Patient` and `Doctor`.
  - Fields: `id`, `name`
  - Includes validation setters.

- `Patient extends person`
  - Fields: `age`, `balance`, `allergies`
  - Methods:
    - `addBalance(int)`
    - `charge(int)`
    - `addAllergy(String)` (duplicate-safe)

- `Doctor extends person`
  - Fields: `maxAppointments`, `appointments`
  - Booking logic checks schedule overlaps and stores appointments.
  - `printAppointments()` prints formatted output.

- `Appointment`
  - Fields: `id`, `patient`, `doctor`, `startTime`, `durationMinutes`, `status`
  - Methods:
    - `endTime()`
    - `cancel()`
    - `toString()`

---

## How to Run

### Option 1: IntelliJ IDEA
1. Open the project.
2. Run `Main.java`.

### Option 2: Command Line
From the folder containing your `.java` files:

```bash
javac *.java
java Main
