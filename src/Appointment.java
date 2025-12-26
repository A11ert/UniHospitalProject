import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime startTime;
    private int durationMinutes;
    private AppointmentStatus status;

    public Appointment() {
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Appointment(String id, Patient patient, Doctor doctor, LocalDateTime startTime, int durationMinutes) {
        setId(id);
        setPatient(patient);
        setDoctor(doctor);
        setStartTime(startTime);
        setDurationMinutes(durationMinutes);
        this.status = AppointmentStatus.SCHEDULED;
    }

    public String getId(){return id;}
    public void setId(String id){
        if(id==null || id.isBlank()){
            throw new IllegalArgumentException("Id can't be null or empty.");
        }
        this.id=id;
    }

    public Patient getPatient(){return patient;}
    public void setPatient(Patient patient){
        if(patient==null){
            throw new IllegalArgumentException("Patient can't be null or empty.");
        }
        this.patient=patient;
    }

    public Doctor getDoctor() { return doctor;}
    public void setDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor can't be null or empty.");
        }
        this.doctor = doctor;
    }

    public LocalDateTime getStartTime(){return startTime;}
    public void setStartTime(LocalDateTime startTime){
        if(startTime==null){
            throw new IllegalArgumentException("Time can't be null.");
        }
        this.startTime=startTime;
    }

    public int getDurationMinutes(){return durationMinutes;}
    public void setDurationMinutes(int durationMinutes){
        if(durationMinutes<=0){
            throw new IllegalArgumentException("Durating time can't be <= 0");
        }
        this.durationMinutes=durationMinutes;
    }

    public AppointmentStatus getStatus(){return status;}
    public void setStatus(AppointmentStatus status){
        if(status==null){
            throw new IllegalArgumentException("Status can't be null.");
        }
        this.status=status;
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
