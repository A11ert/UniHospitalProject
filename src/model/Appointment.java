package model;

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
    public void setId(int id){
        if(id<=0){
            throw new IllegalArgumentException("id should be positive");
        }
        this.id=id;
    }

    public Patient getPatient(){return patient;}
    public void setPatient(Patient patient){
        if(patient==null){
            throw new IllegalArgumentException("exceptions.Patient should be put");
        }
        this.patient=patient;
    }

    public Doctor getDoctor() { return doctor;}
    public void setDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("exceptions.Doctor should be put");
        }
        this.doctor = doctor;
    }

    public LocalDateTime getStartTime(){return startTime;}
    public void setStartTime(LocalDateTime startTime){
        if(startTime==null){
            throw new IllegalArgumentException("Start time of appointment should be set");
        }
        this.startTime=startTime;
    }

    public int getDurationMinutes(){return durationMinutes;}
    public void setDurationMinutes(int durationMinutes){
        if(durationMinutes<=0){
            throw new IllegalArgumentException("Duration of an appointment should be positive");
        }
        this.durationMinutes=durationMinutes;
    }

    public AppointmentStatus getStatus(){return status;}
    public void setStatus(AppointmentStatus status){
        if(status==null){
            throw new IllegalArgumentException("Status is null");
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
        return "exceptions.Appointment id : "+id+"\n"
                + "exceptions.Patient : " + patient.getName() + "\n"
                + "exceptions.Doctor : " + doctor.getName() + "\n"
                + "Start time : " + startTime + "\n"
                + "Duration time : " + durationMinutes + "\n"
                + "Status : " + status + "\n";
    }

}
