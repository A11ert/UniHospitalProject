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
        if(id==null){
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
        if (doctor == null) throw new IllegalArgumentException("Patient can't be null or empty.");
        this.doctor = doctor;
    }
}
