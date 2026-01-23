import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class TerminalInfo {
    private final Scanner scanner = new Scanner(System.in);

    private final List<Person> people = new ArrayList<>();
    private final List<Appointment> allappointment = new ArrayList<>();

    //check for right input
    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            String line = readLine(prompt);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (Example: 1).");
            }
        }
    }

    private int readPositiveInt(String prompt) {
        while (true) {
            int x = readInt(prompt);
            if (x > 0) return x;
            System.out.println("Must be positive.");
        }
    }

    private int readNonNegativeInt(String prompt) {
        while (true) {
            int x = readInt(prompt);
            if (x >= 0) return x;
            System.out.println("Must be 0 or greater.");
        }
    }

    private String readNonBlank(String prompt) {
        while (true) {
            String s = readLine(prompt);
            if (!s.isBlank()) return s;
            System.out.println("Value cannot be empty.");
        }
    }

    private LocalDateTime readDateTime(String prompt){
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while(true){
            String in=readLine(prompt);
            try{
                return LocalDateTime.parse(in,format);
            }catch (Exception e){
                System.out.println("Wrong time format. Use YYYY-MM-DD HH:MM (example: 2026-01-06 10:30).");
            }
        }
    }

    //options
    private int readMenuOption() {
        while (true) {
            int x = readInt("Choose option: ");
            if (x >= 0 && x <= 9) return x;
            System.out.println("Wrong number, try again.");
        }
    }

    // check for existing or uniqueness
    public boolean isUniquePatient(int id) {
        for (Person p : people) {
            if (p instanceof Patient && p.getId() == id) return false;
        }
        return true;
    }

    public boolean isUniqueDoctor(int id) {
        for (Person p : people) {
            if (p instanceof Doctor && p.getId() == id) return false;
        }
        return true;
    }

    public boolean isUniqueAppointment(int id) {
        for (Appointment a : allappointment) if (a.getId() == id) return false;
        return true;
    }

    public Patient isPatientExists(int id) {
        for (Person p : people) {
            if (p instanceof Patient && p.getId() == id) return (Patient) p;
        }
        return null;
    }

    public Doctor isDoctorExists(int id) {
        for (Person p : people) {
            if (p instanceof Doctor && p.getId() == id) return (Doctor) p;
        }
        return null;
    }

    public Appointment isAppointmentExists(int id) {
        for (Appointment a : allappointment) if (a.getId() == id) return a;
        return null;
    }

    private void next() {
        System.out.println();
    }

    // menu
    public void showMenu() {
        System.out.println("====================================");
        System.out.println("        HOSPITAL MANAGEMENT");
        System.out.println("====================================");
        System.out.println("1) Add Patient");
        System.out.println("2) Add Doctor");
        System.out.println("3) Book Appointment");
        System.out.println("4) Cancel Appointment");
        System.out.println("5) View Doctor Appointments");
        System.out.println("6) Add money to Patient");
        System.out.println("7) Charge Patient");
        System.out.println("8) View all people");
        System.out.println("9) View all Appointments");
        System.out.println("0) Exit");
        System.out.println("====================================");
    }

    //base
    public void run() {
        boolean on = true;
        while (on) {
            showMenu();
            int x = readMenuOption();
            if(x==1)InformationAboutPatient();
            else if(x==2)InformationAboutDoctor();
            else if(x==3)BookAppointment();
            else if(x==4)CancelAppointment();
            else if(x==5)ViewAppointmentsForDoctor();
            else if(x==6)AddMoneyToBalance();
            else if(x==7)ChargePatient();
            else if(x==8)ViewAllPeople();
            else if(x==9)ViewAllAppointments();
            else if(x==0)on=false;
        }
    }

    // functions
    public void InformationAboutPatient() {
        System.out.println("Write the information about patient.");

        int id = readPositiveInt("ID: ");
        String name = readNonBlank("Name: ");
        int age = readPositiveInt("Age: ");
        int balance = readNonNegativeInt("Balance: ");

        Patient p = new Patient();

        if (!p.setId(id)) { System.out.println("ID is incorrect.\n"); return; }
        if (!isUniquePatient(id)) { System.out.println("Patient with such ID already exists.\n"); return; }
        if (!p.setName(name)) { System.out.println("Name is incorrect.\n"); return; }
        if (!p.setAge(age)) { System.out.println("Age must be > 0.\n"); return; }
        if (!p.setBalance(balance)) { System.out.println("Balance can't be negative.\n"); return; }

        people.add(p);
        System.out.println("Patient created.");
        next();
    }

    public void InformationAboutDoctor() {
        System.out.println("Write the information about doctor.");

        int id = readPositiveInt("ID: ");
        String name = readNonBlank("Name: ");
        int max = readNonNegativeInt("Maximum appointments per day (0 = no limit): ");

        Doctor d = new Doctor();

        if (!d.setId(id)) { System.out.println("ID is incorrect.\n"); return; }
        if (!isUniqueDoctor(id)) { System.out.println("Doctor with such ID already exists.\n"); return; }
        if (!d.setName(name)) { System.out.println("Name is incorrect.\n"); return; }
        if (!d.setMaxAppointments(max)) { System.out.println("Max appointments can't be negative.\n"); return; }


        people.add(d);
        System.out.println("Doctor created.");
        next();
    }

    public void BookAppointment() {
        System.out.println("Write information about appointment.");

        int id = readPositiveInt("ID of the appointment: ");
        if (!isUniqueAppointment(id)) {
            System.out.println("Appointment with this ID already exists.\n");
            return;
        }

        int id_p = readPositiveInt("ID of the patient: ");
        int id_d = readPositiveInt("ID of the doctor: ");
        LocalDateTime startTime = readDateTime("Start (YYYY-MM-DD HH:MM): ");
        int duration = readPositiveInt("Duration (minutes): ");

        Patient p = isPatientExists(id_p);
        if (p == null) {
            System.out.println("Patient with this ID doesn't exist!\n");
            return;
        }

        Doctor d = isDoctorExists(id_d);
        if (d == null) {
            System.out.println("Doctor with this ID doesn't exist!\n");
            return;
        }

        Appointment appointment = new Appointment(id, p, d, startTime, duration);
        if (!d.booking(appointment)) {
            next();
            System.out.println("Another appointment already exists at this time or id's doesn't match.");
            return;
        }

        allappointment.add(appointment);
        System.out.println("Appointment booked.");
        next();
    }

    public void CancelAppointment() {
        int id = readPositiveInt("Write ID of the appointment: ");
        Appointment appointment = isAppointmentExists(id);

        if (appointment == null) {
            System.out.println("There is no appointment with such ID!\n");
            return;
        }
        appointment.cancel();
        System.out.println("Appointment with ID: " + id + " was canceled.");
        next();
    }

    public void ViewAppointmentsForDoctor() {
        int id = readPositiveInt("ID of the doctor: ");
        Doctor d = isDoctorExists(id);

        if (d == null) {
            System.out.println("There is no doctor with such ID.\n");
            return;
        }

        d.printAppointments();
        next();
    }

    public void AddMoneyToBalance() {
        int id = readPositiveInt("ID of the patient: ");
        Patient p = isPatientExists(id);

        if (p == null) {
            System.out.println("There is no patient with such ID.\n");
            return;
        }

        int x = readPositiveInt("How much do you want to add: ");
        if (!p.addBalance(x)) {
            System.out.println("Amount must be > 0.\n");
            return;
        }

        System.out.println("Money were added to patient with id: " + id + ".");
        next();
    }

    public void ChargePatient() {
        int id = readPositiveInt("ID of the patient: ");
        Patient p = isPatientExists(id);

        if (p == null) {
            System.out.println("There is no patient with such ID.\n");
            return;
        }

        int x = readPositiveInt("How much do you want to charge: ");
        if (!p.charge(x)) {
            System.out.println("Not enough balance (or invalid charge).");
            next();
            return;
        }

        System.out.println("Patient with id: " + id + " was charged.");
        next();
    }

    public void ViewAllPeople() {
        int doctors = 0;
        int patients = 0;

        for (Person p : people) {
            if (p instanceof Doctor) doctors++;
            else if (p instanceof Patient) patients++;
        }

        System.out.println("Number of doctors: " + doctors);
        for (Person p : people) {
            if (p instanceof Doctor) System.out.println(p);
        }

        System.out.println();
        System.out.println("Number of patients: " + patients);
        for (Person p : people) {
            if (p instanceof Patient) System.out.println(p);
        }

        next();
    }
    public void ViewAllAppointments(){
        int count=0;
        for(Person d:people){
            if(d instanceof Doctor){
                count+=((Doctor) d).getAppointmentCount();
            }
        }
        System.out.println("Total number of appointments : "+count+"\n");
        for(Person d:people){
            if(d instanceof Doctor){
                System.out.println("Doctor : "+ d.getName() + " |  Number of appointments : "+ ((Doctor) d).getAppointmentCount() + "\n");
                for(Appointment a:allappointment){
                    if(a.getDoctor().getId()==d.getId()){
                        System.out.println("ID : "+a.getId() + " |  Status : "+ a.getStatus()+ " |  Time : "+ a.getStartTime() + " |  Duration : "+a.getDurationMinutes()+"\n");
                    }
                }
            }
        }
        next();
    }
}
