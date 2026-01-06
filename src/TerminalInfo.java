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
    public Appointment isAppointmentExists(int id){
        for (Appointment a : allappointment) if (a.getId() == id) return a;
        return null;
    }
    //start
    public void showMenu() {
        System.out.println("====================================");
        System.out.println("        HOSPITAL MANAGEMENT");
        System.out.println("====================================");
        System.out.println("1) Add Patient");
        System.out.println("2) Add Doctor");
        System.out.println("3) Book Appointment");
        System.out.println("4) View Cancel Appointment");
        System.out.println("5) View Doctor Appointments");
        System.out.println("6) Add money to Patient");
        System.out.println("7) Charge Patient");
        System.out.println("0) Exit");
        System.out.println("====================================");
        System.out.print("Choose option: ");
    }
    //1
    public void InformationAboutPatient(){
        System.out.println("Write the information about patient.");
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
            System.out.println("ID is incorrect try again.\n");
            return;
        }
        if(!isUniquePatient(id)){
            System.out.println("Patient with such ID already exists, try another one.\n");
            return;
        }
        if(!p.setName(name)){
            System.out.println("Name is incorrect try again.\n");
            return;
        }
        if(!p.setAge(age)){
            System.out.println("Age can't be negative, try again.\n");
            return;
        }
        if(!p.setBalance(balance)){
            System.out.println("Balance can't be negative, try again.\n");
            return;
        }
        allpatients.add(p);
        System.out.println("Patient created.\n");
    }
    //2
    public void informationAboutDoctor(){
        System.out.println("Write the information about doctor.");
        System.out.print("ID : ");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name : ");
        String name=scanner.nextLine();
        System.out.print("Maximum Appointment per day : ");
        int age= scanner.nextInt();
        Doctor d=new Doctor(1,"X",1);

        if(!d.setId(id)){
            System.out.println("ID is incorrect try again.\n");
            return;
        }
        if(!isUniqueDoctor(id)){
            System.out.println("Doctor with such ID already exists, try another one.\n");
            return;
        }
        if(!d.setName(name)){
            System.out.println("Name is incorrect try again.\n");
            return;
        }
        if(!d.setMaxAppointments(age)){
            System.out.println("Max appointments per day can't be negative.\n");
            return;
        }
        alldoctor.add(d);
        System.out.println("Doctor created.");
        System.out.println("");
    }
    //3
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
            System.out.println("ID must be positive.\n");
            return;
        }
        if(duration <= 0){
            System.out.println("Duration must be >= 1.\n");
            return;
        }
        if(!isUniqueAppointment(id)){
            System.out.println("Appointment with this ID already exists.\n");
            return;
        }

        Patient p = isPatientExists(id_p);
        if(p == null){
            System.out.println("Patient with this ID doesn't exist!\n");
            return;
        }

        Doctor d = isDoctorExists(id_d);
        if(d == null){
            System.out.println("Doctor with this ID doesn't exist!\n");
            return;
        }

        LocalDateTime startTime;
        try {
            startTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            System.out.println("Wrong date/time format. Use YYYY-MM-DD HH:MM\n");
            return;
        }

        Appointment appointment = new Appointment(id, p, d, startTime, duration);
        if(!d.booking(appointment)){
            return;
        }

        allappointment.add(appointment);
        System.out.println("Appointment booked.");
        System.out.println("");
    }
    //4
    public void CancelAppointment(){
        System.out.println("Write ID of the appointment : ");
        int id= scanner.nextInt();
        if(isAppointmentExists(id)==null){
            System.out.print("There is no appointment with such ID!\n");
            return;
        }
        Appointment appointment=isAppointmentExists(id);
        appointment.cancel();
        System.out.print("Appointment with ID : "+id
                + " was canceled.\n");
    }
    //5
    public void ViewAppointmentsForDoctor(){
        System.out.print("ID of the doctor : ");
        int id=scanner.nextInt();
        if(isDoctorExists(id)==null){
            System.out.print("There is no doctor with such ID\n");
            return;
        }
        Doctor d=isDoctorExists(id);
        d.printAppointments();
        System.out.println("");
    }
    //6
    public void AddMoneyToBalance(){
        System.out.print("ID of the patient : ");
        int id=scanner.nextInt();
        if(isPatientExists(id)==null){
            System.out.print("There is no patient with such ID\n");
            return;
        }
        Patient p=isPatientExists(id);
        System.out.print("How much do you want to add : ");
        int x=scanner.nextInt();
        if(!p.addBalance(x)){
            System.out.print("Amount can't be negative.");
            return;
        }
        p.addBalance(x);
        System.out.print("Money were added to patient with id : " + id
                + ".\n");
        System.out.println("");
    }
    //7
    public void ChargePatient(){
        System.out.print("ID of the patient : ");
        int id=scanner.nextInt();
        if(isPatientExists(id)==null){
            System.out.print("There is no patient with such ID\n");
            return;
        }
        Patient p=isPatientExists(id);
        System.out.print("How much do you want to charge : ");
        int x=scanner.nextInt();
        if(!p.charge(x)){
            System.out.print("Not enough balance or charge can't be negative.");
            return;
        }
        p.charge(x);
        System.out.print("Patient with id : "+id
                + " was charged.\n");
        System.out.println("");
    }


}
