import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        boolean on=true;
        Scanner scanner = new Scanner(System.in);
        TerminalInfo terminal = new TerminalInfo();

        terminal.showMenu();
        while(on){

            int x=scanner.nextInt();

            if(x==1){
                terminal.InformationAboutPatient();
            }else if(x==2){
                terminal.informationAboutDoctor();
            }else if(x==3){
                terminal.BookAppointment();
            }else if(x==4){
                terminal.CancelAppointment();
            }else if(x==5){
                terminal.ViewAppointmentsForDoctor();
            }else if(x==6){
                terminal.AddMoneyToBalance();
            }else if(x==7){
                terminal.ChargePatient();
            }
            else if(x==0){
                on=false;
            }
            else{
                System.out.print("Wrong number try again");
            }
        }
        System.out.println("Thanks for using!");
    }
}