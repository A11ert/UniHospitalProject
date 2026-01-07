import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person{
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
    public boolean setMaxAppointments(int number){
        if(number<0){
            return false;
        }
        maxAppointments=number;
        return true;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    //logic for booking
    public boolean booking(Appointment appointment){
        boolean f=false;
        if(!appointment.getDoctor().getName().equals(name)){
            System.out.println("This appointment is for another doctor");
            return false;
        }
        for (Appointment ap : appointments) {
            //skip canceled status;
            if (ap.getStatus() == AppointmentStatus.CANCELLED) continue;
            //if new appointment is after start of other appointment but before its end
            if (ap.getStartTime().isBefore(appointment.getStartTime()) && ap.endTime().isAfter(appointment.getStartTime())) {
                f = true;
                break;
            }
            // if new appointment if before start of other appointment but end after it
            if (ap.getStartTime().isAfter(appointment.getStartTime()) && appointment.endTime().isAfter(ap.getStartTime())) {
                f = true;
                break;
            }
        }
        if(f){
            System.out.println("It's impossible to book appointment for doctor "+appointment.getDoctor().getName() + " at : "+ appointment.getStartTime() +".");

            return false;
        }
        appointments.add(appointment);
        return true;
    }

    public int getAppointmentCount() {
        return appointments.size();
    }

    public void printAppointments() {
        System.out.println("ID  | Doctor    | Patient   | Start              | Status");
        System.out.println("----+----------+-----------+--------------------+--------");
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
    public String getRole() { return "Doctor"; }

    @Override
    public String toString() {
        return "Doctor " + name + ", id "+id+".";
    }

}
