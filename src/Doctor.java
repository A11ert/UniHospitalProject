import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String id;
    private String name;
    private int maxAppointments;
    private List<Appointment> appointments;

    public Doctor() {
        this.appointments = new ArrayList<>();
    }

    public Doctor(String id, String name, int maxAppointments){
        this.appointments = new ArrayList<>();
        setId(id);
        setName(name);
        setMaxAppointments(maxAppointments);
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        if(id==null || id.isBlank()){
            throw new IllegalArgumentException("Doctor id can't be empty.");
        }
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Doctor name can't be empty.");
        }
        this.name=name;
    }

    public int getMaxAppointments(){
        return maxAppointments;
    }
    public void setMaxAppointments(int number){
        if(number<0){
            throw new IllegalArgumentException("Max appointment can't be < 0.");
        }
        maxAppointments=number;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    //logic for booking
    public boolean booking(Appointment appointment){
        boolean f=false;
        if(appointment.getDoctor().getName()!=name){
            System.out.println("This appointment is for another doctor");
            return false;
        }
        for(int i=0; i<appointments.size(); i++){
            Appointment ap = appointments.get(i);
            //skip canceled status;
            if(ap.getStatus()==AppointmentStatus.CANCELLED)continue;
            //if new appointment is after start of other appointment but before its end
            if(ap.getStartTime().isBefore(appointment.getStartTime()) && ap.endTime().isAfter(appointment.getStartTime())){
                f=true;
                break;
            }
            // if new appointment if before start of other appointment but end after it
            if(ap.getStartTime().isAfter(appointment.getStartTime()) && appointment.endTime().isAfter(ap.getStartTime())){
                f=true;
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

    public void cancelAppointment(String Id){
        if(Id==null || Id.isBlank()){
            throw new IllegalArgumentException("Id cant be empty");
        }
        for(int i=0; i<appointments.size(); i++){
            Appointment ap=appointments.get(i);
            if(Id.equals(ap.getId())){
                this.appointments.get(i).cancel();
                return;
            }
        }
        System.out.println("There is no appointment with such Id");

    }

    public int getAppointmentCount() {
        return appointments.size();
    }

    public void printAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments.");
            return;
        }
        int count = 1;
        for (int i = 0; i < appointments.size(); i++) {
            if(appointments.get(i).getStatus().equals(AppointmentStatus.SCHEDULED)){
                System.out.println("Appointment: " + count);
                System.out.println("Id : " + appointments.get(i).getId() + ", At : " + appointments.get(i).getStartTime());
            }
        }
    }

    @Override
    public String toString() {
        return "Doctor " + name + ", id "+id+".";
    }

}
