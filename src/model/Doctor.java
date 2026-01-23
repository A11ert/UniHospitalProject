package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private int maxAppointments;
    private List<Appointment> appointments;

    public Doctor() {
        this.appointments = new ArrayList<>();
    }

    public Doctor(int id, String name, int maxAppointments){
        this.appointments = new ArrayList<>();
        setId(id);
        setName(name);
        setMaxAppointments(maxAppointments);
    }

    public int getMaxAppointments(){
        return maxAppointments;
    }
    public void setMaxAppointments(int number){
        if(number<0){
            throw new IllegalArgumentException("max appointments number can't be lower than zero");
        }
        maxAppointments=number;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    //logic for booking
    public void booking(Appointment appointment){
        LocalDateTime newStart = appointment.getStartTime();
        LocalDateTime newEnd=appointment.endTime();
        for (Appointment ap : appointments) {
            if(newStart.isBefore(ap.endTime()) && ap.getStartTime().isBefore(newEnd)){
                throw new IllegalArgumentException("Another appointment exists at this time");
            }
        }
        appointments.add(appointment);
    }

    public int getAppointmentCount() {
        return appointments.size();
    }

    public void printAppointments() {
        System.out.println("ID  | exceptions.Doctor    | exceptions.Patient   | Start              | Status");
        System.out.println("----+-----------+-----------+--------------------+--------");
        for (Appointment a : appointments) {
            System.out.printf("%-3d | %-9s | %-9s | %-19s | %s%n",
                    a.getId(),
                    a.getDoctor().getName(),
                    a.getPatient().getName(),
                    a.getStartTime(),
                    a.getStatus());
        }
    }

    @Override
    public String getRole() { return "exceptions.Doctor"; }

    @Override
    public void work() {
        System.out.println("Doctor " + name + " is treating patients.");
    }

    @Override
    public String toString() {
        return "exceptions.Doctor " + name + ", id "+id+".";
    }

}
