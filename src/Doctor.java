import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String id;
    private String name;
    private int maxAppointments;
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String id, String name, int maxAppointments){
        setId(id);
        setName(name);
        setMaxAppointments(maxAppointments);
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        if(id==null || id.isBlank()){
            throw new IllegalArgumentException("Patient id can't be empty.");
        }
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Patient name can't be empty.");
        }
        this.name=name;
    }

    public int getMaxAppointments(){
        return maxAppointments;
    }
    public void setMaxAppointments(int number){
        if(number<0){
            throw new IllegalArgumentException("max appointment can't be < 0.");
        }
        maxAppointments=number;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    //logic
    public int countAppointmentsOn(LocalDate date) {
        int count = 0;
        for (Appointment a : appointments) {
            if (a.getStartTime().toLocalDate().equals(date) && a.getStatus() != AppointmentStatus.CANCELLED) {
                count++;
            }
        }
        return count;
    }

    public boolean isAvailable(LocalDateTime start, int durationMinutes) {
        Appointment temp = new Appointment("TEMP", null, this, start, durationMinutes);
        for(int i=0; i<appointments.size(); i++){
            Appointment ap=appointments.get(i);
            if(ap.getStatus()!=AppointmentStatus.CANCELLED && ap.overlaps(temp)){
                return false;
            }
        }
        return true;
    }

    public boolean bookAppointment(Appointment appointment) {
        LocalDate date = appointment.getStartTime().toLocalDate();

        if (countAppointmentsOn(date) >= maxAppointments) return false;
        if (!isAvailable(appointment.getStartTime(), appointment.getDurationMinutes())) return false;

        appointments.add(appointment);
        return true;
    }
}
