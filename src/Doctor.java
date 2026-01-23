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
        LocalDateTime newStart = appointment.getStartTime();
        LocalDateTime newEnd=appointment.endTime();
        for (Appointment ap : appointments) {
            if(newStart.isBefore(ap.endTime()) && ap.getStartTime().isBefore(newEnd)){
                return false;
            }
            return true;
        }
        appointments.add(appointment);
        return true;
    }

    public int getAppointmentCount() {
        return appointments.size();
    }

    public void printAppointments() {
        System.out.println("ID  | Doctor    | Patient   | Start              | Status");
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
    public String getRole() { return "Doctor"; }

    @Override
    public String toString() {
        return "Doctor " + name + ", id "+id+".";
    }

}
