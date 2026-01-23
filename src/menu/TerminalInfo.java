package menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import model.*;

public class TerminalInfo {
    private final Scanner scanner = new Scanner(System.in);

    private final List<Person> people = new ArrayList<>();
    private final List<Appointment> allappointment = new ArrayList<>();


    public void showMenu() {
        System.out.println("====================================");
        System.out.println("        HOSPITAL MANAGEMENT");
        System.out.println("====================================");
        System.out.println("1) Add exceptions.Patient");
        System.out.println("2) Add exceptions.Doctor");
        System.out.println("3) Book exceptions.Appointment");
        System.out.println("4) Cancel exceptions.Appointment");
        System.out.println("5) View exceptions.Doctor Appointments");
        System.out.println("6) Add money to exceptions.Patient");
        System.out.println("7) Charge exceptions.Patient");
        System.out.println("8) View all people");
        System.out.println("9) View all Appointments");
        System.out.println("10) View work of every people");
        System.out.println("0) Exit");
        System.out.println("====================================");
    }

    public void run() {
        boolean on = true;
        while (on) {
            showMenu();
            System.out.println("What you want to choose");
            try{
                int x=scanner.nextInt();

                if(x==1)AddPatient();
                else if(x==2)AddDoctor();
                else if(x==3)BookAppointment();
                else if(x==4)CancelAppointment();
                else if(x==5)ViewAppointmentsForDoctor();
                else if(x==6)AddMoneyToBalance();
                else if(x==7)ChargePatient();
                else if(x==8)ViewAllPeople();
                else if(x==9)ViewAllAppointments();
                else if(x==10)ViewWork();
                else if(x==0)on=false;
                else{
                    System.out.println("Please choose between 0-10");
                }
            }catch(InputMismatchException e){
                System.out.println("Please write correct data");
                scanner.nextLine();
            }catch(Exception e){
                System.out.println("smth went wrong" + e);
                scanner.nextLine();
            }
        }
    }

    // functions
    public void AddPatient() {
        try{
            System.out.println("Write the information about patient.");

            System.out.println("ID:");
            int id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Name:");
            String name=scanner.nextLine();
            System.out.println("Age:");
            int age=scanner.nextInt();
            System.out.println("Balance");
            int balance= scanner.nextInt();

            Patient p = new Patient(id,name,age,balance);

            people.add(p);
            System.out.println("exceptions.Patient added.");

        }catch(InputMismatchException e){
            System.out.println("Invalid data");
            scanner.nextLine();
        }catch(IllegalArgumentException e){
            System.out.println("Validation problem: " + e);
        }
    }

    public void AddDoctor() {
        try{
            System.out.println("Write the information about doctor.");

            System.out.println("ID:");
            int id=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Name:");
            String name=scanner.nextLine();
            System.out.println("Maximum appointments: ");
            int maxAppointments=scanner.nextInt();

            Doctor d = new Doctor(id,name,maxAppointments);
            people.add(d);
            System.out.println("exceptions.Doctor added.");
            System.out.println();
        }catch(InputMismatchException e){
            System.out.println("Invalid data");
            scanner.nextLine();
        }catch(IllegalArgumentException e){
            System.out.println("Validation problem: " + e);
        }
    }

    public void BookAppointment() {
        System.out.println("Write information about appointment.");
        try {
            System.out.println("ID of an appointment: ");
            int id = scanner.nextInt();
            System.out.println("Id of the patient");
            int id_p = scanner.nextInt();
            System.out.println("Id of the doctor");
            int id_d = scanner.nextInt();
            System.out.println("Time of an appointment. Write exactly like YYYY-MM-DD HH:MM .");
            scanner.nextLine();
            String input = scanner.nextLine();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(input, format);
            System.out.println("Duration in minutes : ");
            int duration = scanner.nextInt();

            boolean exists = false;
            Doctor d = new Doctor();
            for (Person person : people) {
                if (person instanceof Doctor && person.getId() == id_d) {
                    d = (Doctor) person;
                    exists=true;
                }
            }
            if(!exists){
                throw new IllegalArgumentException("exceptions.Doctor with such ID doesn't exists");
            }
            exists=false;
            Patient p = new Patient();
            for (Person person : people) {
                if (person instanceof Patient && person.getId() == id_p) {
                    p = (Patient) person;
                    exists=true;
                }
            }
            if(!exists){
                throw new IllegalArgumentException("exceptions.Patient with such ID doesn't exists");
            }

            Appointment appointment = new Appointment(id, p, d, startTime, duration);
            allappointment.add(appointment);
            d.booking(appointment);

            System.out.println("Appointment booked.");
            System.out.println();
        }catch (InputMismatchException e){
            System.out.println("Wrond data type" + e);
        }catch(IllegalArgumentException e){
            System.out.println("Validation problem: "+ e);
        }
    }

    public void CancelAppointment() {
        try{
            System.out.println("Id of an appointment: ");
            int id = scanner.nextInt();
            Appointment ap=null;
            boolean exists=false;
            for(Appointment appointment:allappointment){
                if(appointment.getId()==id){
                    ap=appointment;
                    exists=true;
                }
            }
            if(!exists){
                throw new IllegalArgumentException("exceptions.Appointment with such id doesn't exists");
            }

            ap.cancel();
            System.out.println("exceptions.Appointment with ID: " + id + " was canceled.");
            System.out.println();
        }catch(InputMismatchException e){
            System.out.println("Wrong data type");
        }catch(IllegalArgumentException e){
            System.out.println("Validation problem: " + e);
        }

    }

    public void ViewAppointmentsForDoctor() {
        try{
            System.out.println("ID of the doctor: ");
            int id=scanner.nextInt();
            boolean exists = false;
            Doctor d = new Doctor();
            for (Person person : people) {
                if (person instanceof Doctor && person.getId() == id) {
                    d = (Doctor) person;
                    exists=true;
                }
            }
            if(!exists){
                throw new IllegalArgumentException("exceptions.Doctor with such ID doesn't exists");
            }

            d.printAppointments();
            System.out.println();
        }catch (InputMismatchException e){
            System.out.println("Wrong data type");
            scanner.nextLine();
        }catch (IllegalArgumentException e){
            System.out.println("Validation mistake: "+ e);
        }

    }

    public void AddMoneyToBalance() {
        try {
            System.out.println("ID of the patient: ");
            int id = scanner.nextInt();

            Patient p = null;
            for (Person person : people) {
                if (person instanceof Patient && person.getId() == id) {
                    p = (Patient) person;
                    break;
                }
            }

            if (p == null) {
                throw new IllegalArgumentException("There is no patient with such ID.");
            }

            System.out.println("How much do you want to add: ");
            int x = scanner.nextInt();
            scanner.nextLine();

            p.addBalance(x);

            System.out.println("Money were added to patient with id: " + id + ".");
            System.out.println();

        } catch (InputMismatchException e) {
            System.out.println("Wrong data type");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Validation problem: " + e.getMessage());
        }
    }

    public void ChargePatient() {
        try {
            System.out.println("ID of the patient: ");
            int id = scanner.nextInt();
            Patient p = null;
            for (Person person : people) {
                if (person instanceof Patient && person.getId() == id) {
                    p = (Patient) person;
                    break;
                }
            }
            if (p == null) {
                throw new IllegalArgumentException("There is no patient with such ID.");
            }

            System.out.println("How much do you want to charge: ");
            int x = scanner.nextInt();
            scanner.nextLine();

            p.charge(x);
            System.out.println("exceptions.Patient was charged.");
            System.out.println();

        } catch (InputMismatchException e) {
            System.out.println("Wrong data type");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Validation problem: " + e.getMessage());
        }
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

        System.out.println();
    }

    public void ViewAllAppointments() {
        System.out.println("Total number of appointments: " + allappointment.size());
        System.out.println();

        for (Appointment a : allappointment) {
            System.out.println("ID : " + a.getId()
                    + " | Status : " + a.getStatus()
                    + " | exceptions.Doctor : " + a.getDoctor().getName()
                    + " | exceptions.Patient : " + a.getPatient().getName()
                    + " | Time : " + a.getStartTime()
                    + " | Duration : " + a.getDurationMinutes());
        }

        System.out.println();
    }

    public void ViewWork(){
        if(people.size()==0){
            System.out.println("There no people in system");
            return;
        }
        for(Person p:people){
            p.work();
        }
        System.out.println();

    }

}
