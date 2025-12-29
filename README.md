# Hospital Management System (OOP / Encapsulation) — Java

A small **university OOP project** that models a simple hospital scheduling system.  
Focus: **Encapsulation**, constructors, getters/setters, and basic business logic.

---

## Features

### Patient
- Stores patient info: **id, name, age, balance**
- Manages **allergies** list
- Logic:
  - `charge(cost)` — subtracts money if enough balance
  - `addAllergy(allergy)` — adds allergy (no duplicates)
  - `getAllergies()` — returns a copy (protects encapsulation)

### Doctor
- Stores doctor info: **id, name, maxAppointments**
- Holds a private list of `Appointment`
- Logic:
  - `booking(appointment)` — books only if time is free (checks overlap)
  - `printAppointments()` — prints all booked appointments
  - `getAppointments()` — returns a copy of list

### Appointment
- Stores appointment info: **id, patient, doctor, startTime, durationMinutes, status**
- Logic:
  - `endTime()` — calculates end time from start + duration
  - `cancel()` — sets status to `CANCELLED`
  - `toString()` — prints appointment info in multiple lines

### AppointmentStatus (Enum)
- `SCHEDULED`, `CANCELLED`
- can be changed to `COMPLETE`

---

## Project Structure

src:

-Patient.java

-Doctor.java

-Appointment.java

-AppointmentStatus.java

-Main.java

## Working process
### Balance and Charge method
<img width="1009" height="423" alt="image" src="https://github.com/user-attachments/assets/07af4b7a-701b-4c98-a1de-f89dbf5d5626" />
15000 balance to -><img width="275" height="115" alt="image" src="https://github.com/user-attachments/assets/b4fa9cbf-0ff5-4e3f-939a-6666eae30c7e" />


### Appointment
<img width="1288" height="451" alt="image" src="https://github.com/user-attachments/assets/07e5e308-16a8-44d8-9b65-d92de4f62e02" />

TerminalInfo
<img width="662" height="175" alt="image" src="https://github.com/user-attachments/assets/430382a4-abe7-409d-9d20-4fbe9fbf80db" />




