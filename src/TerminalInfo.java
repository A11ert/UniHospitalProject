import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalInfo {
    Scanner scanner = new Scanner(System.in);
    List<Patient> allpatients = new ArrayList<>();

    public boolean checkid(int id){
        for(int i=0; i<allpatients.size(); i++){
            if(allpatients.get(i).getId()==id){
                return false;
            }
        }
        return true;
    }

    public void StartInfo(){
        System.out.println("=== HOSPITAL SYSTEM ===\n"
                +"1.Add Patient\n"
                +"2.Add Doctor\n"
                +"3.Book appointment\n"
                +"3.View all appointments for one doctor\n"
                +"4.Cancel appointment\n"
                +"5.Add allergy to patient\n");
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

        if(!p.setId(id) || !checkid(id)){
            if(!p.setId(id)){
                System.out.println("Id is incorrect try again.");
            }else{
                System.out.println("Id already exists, try again.");
            }
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
        if(p.setBalance(balance)){
            System.out.println("Balance can't be negative, try again.");
            return;
        }
        System.out.println("Patient created.");
    }

    public void informationAboutDoctor(){
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

        if(!p.setId(id) || !checkid(id)){
            if(!p.setId(id)){
                System.out.println("Id is incorrect try again.");
            }else{
                System.out.println("Id already exists, try again.");
            }
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
        if(p.setBalance(balance)){
            System.out.println("Balance can't be negative, try again.");
            return;
        }
        System.out.println("Patient created.");
    }

    public void BookAppointment(){
        System.out.println("Write the information about appointment:\n"
                +"ID for appointment\n"
                +"ID of patient\n"
                +"ID of doctor\n"
                +"Start time\n"
                +"Duration of appointment\n");
    }

}
