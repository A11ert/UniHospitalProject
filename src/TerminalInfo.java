import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalInfo {
    Scanner scanner = new Scanner(System.in);
    List<Patient> allpatients = new ArrayList<>();
    List<Doctor> alldoctor = new ArrayList<>();
    List<Appointment> allappointment = new ArrayList<>();

    public boolean isUniquePatient(int id){
        for (Patient p : allpatients) if (p.getId() == id) return false;
        return true;
    }
    public boolean isUniqueDoctor(int id){
        for (Doctor d : alldoctor) if (d.getId() == id) return false;
        return true;
    }
    public boolean isUniqueAppointment(int id){
        for (Appointment a : allappointment) if(a.getId()==id)return false;
        return true;
    }
    public Patient isPatientExists(int id){
        for (Patient p : allpatients) if (p.getId() == id) return p;
        return null;
    }
    public Doctor isDoctorExists(int id){
        for (Doctor d : alldoctor) if (d.getId() == id) return d;
        return null;
    }

    public void StartInfo(){
        System.out.println("=== HOSPITAL SYSTEM ===\n"
                +"1.Add Patient\n"
                +"2.Add Doctor\n"
                +"3.Book appointment\n"
                +"4.View all appointments for one doctor\n"
                +"5.Cancel appointment\n"
                +"6.Add allergy to patient\n");
    }

    public void InformationAboutPatient(){
        System.out.println("Write the information about him.");
        System.out.print("ID : ");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name : ");
        String name=scanner.nextLine();
        System.out.print("Age : ");
        int age= scanner.nextInt();
        System.out.print("Balance : ");
        int balance = scanner.nextInt();

        Patient p=new Patient(1,"X",1,1);

        if(!p.setId(id)){
            System.out.println("Id is incorrect try again.");
            return;
        }
        if(!isUniquePatient(id)){
            System.out.println("Id already exists, try again.");
            return;
        }
        if(!p.setName(name)){
            System.out.println("Name is incorrect try again.");
            return;
        }
        if(!p.setAge(age)){
            System.out.println("Age can't be negative, try again.");
            return;
        }
        if(!p.setBalance(balance)){
            System.out.println("Balance can't be negative, try again.");
            return;
        }
        allpatients.add(p);
        System.out.println("Patient created.");
    }

    public void informationAboutDoctor(){
        System.out.println("Write the information about him.");
        System.out.print("ID : ");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name : ");
        String name=scanner.nextLine();
        System.out.print("Maximum Appointment per day : ");
        int age= scanner.nextInt();
        Doctor d=new Doctor(1,"X",1);

        if(!d.setId(id)){
            System.out.println("Id is incorrect try again.");
            return;
        }
        if(!isUniqueDoctor(id)){
            System.out.println("Id already exists, try again.");
            return;
        }
        if(!d.setName(name)){
            System.out.println("Name is incorrect try again.");
            return;
        }
        if(!d.setMaxAppointments(age)){
            System.out.println("Max appointments per day can't be negative.");
            return;
        }
        alldoctor.add(d);
        System.out.println("Doctor created.");
    }

    public void BookAppointment(){
        System.out.println("Write information about appointment.");
        System.out.print("ID of the appointment : ");
        int id = scanner.nextInt();
        System.out.print("ID of the patient : ");
        int id_p = scanner.nextInt();
        System.out.print("ID of the doctor : ");
        int id_d = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Start (YYYY-MM-DD HH:MM): ");
        String s = scanner.nextLine();
        System.out.print("Duration of the appointment (minutes): ");
        int duration = scanner.nextInt();

        if(id <= 0){
            System.out.println("Id must be positive.");
            return;
        }
        if(duration <= 0){
            System.out.println("Duration must be >= 1.");
            return;
        }
        if(!isUniqueAppointment(id)){
            System.out.println("Appointment with this ID already exists.");
            return;
        }

        Patient p = isPatientExists(id_p);
        if(p == null){
            System.out.println("Patient with this ID doesn't exist!");
            return;
        }

        Doctor d = isDoctorExists(id_d);
        if(d == null){
            System.out.println("Doctor with this ID doesn't exist!");
            return;
        }

        LocalDateTime startTime;
        try {
            startTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            System.out.println("Wrong date/time format. Use YYYY-MM-DD HH:MM");
            return;
        }

        Appointment appointment = new Appointment(id, p, d, startTime, duration);
        if(!d.booking(appointment)){
            return;
        }

        allappointment.add(appointment);
        System.out.println("Appointment booked.");
    }


}
