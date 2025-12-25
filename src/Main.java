import javax.print.Doc;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // doctors and patient information
        Patient p1 = new Patient("071107554554", "Yernaz", 18, 15000);
        Patient p2 = new Patient("051123004", "Kairat", 25, 45000);
        Doctor d1 = new Doctor("42001", "Vlad", 15);

        //charge method
        p1.charge(5000);
        System.out.println(p1.getBalance());

        //two different appointments. Second appointment overlaps another
        Appointment ap1 = new Appointment("1", p1, d1, LocalDateTime.of(2025, 12, 26, 10, 0), 25);
        Appointment ap2 = new Appointment("2", p2, d1, LocalDateTime.of(2025,12, 26, 10, 10), 10);

        //booking method for first appointment
        d1.booking(ap1);
        //Check if appointment is success`ful.
        d1.printAppointments();

        //trying to book second appointment
        d1.booking(ap2);

        //new appointment hour later after ap1.
        Appointment ap3 = new Appointment("3", p2, d1, LocalDateTime.of(2025,12, 26, 11, 10), 10);
        //trying to book new appointment
        d1.booking(ap3);

        //printing all appointment for doctor d1(Vlad)
        d1.printAppointments();
    }
}