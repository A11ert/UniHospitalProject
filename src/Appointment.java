import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime startTime;
    private int durationMinutes;
    private AppointmentStatus status;

    public Appointment() {
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Appointment(int id, Patient patient, Doctor doctor, LocalDateTime startTime, int durationMinutes) {
        setId(id);
        setPatient(patient);
        setDoctor(doctor);
        setStartTime(startTime);
        setDurationMinutes(durationMinutes);
        this.status = AppointmentStatus.SCHEDULED;
    }

    public int getId(){return id;}
    public boolean setId(int id){
        if(id<=0){
            return false;
        }
        this.id=id;
        return true;
    }

    public Patient getPatient(){return patient;}
    public boolean setPatient(Patient patient){
        if(patient==null){
            return false;
        }
        this.patient=patient;
        return true;
    }

    public Doctor getDoctor() { return doctor;}
    public boolean setDoctor(Doctor doctor) {
        if (doctor == null) {
            return false;
        }
        this.doctor = doctor;
        return true;
    }

    public LocalDateTime getStartTime(){return startTime;}
    public boolean setStartTime(LocalDateTime startTime){
        if(startTime==null){
            return false;
        }
        this.startTime=startTime;
        return true;
    }

    public int getDurationMinutes(){return durationMinutes;}
    public boolean setDurationMinutes(int durationMinutes){
        if(durationMinutes<=0){
            return false;
        }
        this.durationMinutes=durationMinutes;
        return true;
    }

    public AppointmentStatus getStatus(){return status;}
    public boolean setStatus(AppointmentStatus status){
        if(status==null){
            return false;
        }
        this.status=status;
        return true;
    }

    public LocalDateTime endTime(){
        return (startTime.plusMinutes(durationMinutes));
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    @Override
    public String toString(){
        return "Appointment id : "+id+"\n"
                + "Patient : " + patient.getName() + "\n"
                + "Doctor : " + doctor.getName() + "\n"
                + "Start time : " + startTime + "\n"
                + "Duration time : " + durationMinutes + "\n"
                + "Status : " + status + "\n";
    }

}
