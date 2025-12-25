import javax.print.Doc;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // doctors and patient information
        Patient p1 = new Patient("071107554554", "Yernaz", 18, 15000);
        Doctor d1 = new Doctor("42001", "Vlad", 15);

        //two different appointments. Second appointment overlaps another
        Appointment ap1 = new Appointment("1", p1, d1, LocalDateTime.of(2025, 12, 26, 10, 0), 25);

        //booking method for first appointment and Check if appointment is successful.
        d1.booking(ap1);
        d1.printAppointments();

        //canceling appointment and checking if method worked.
        d1.cancelAppointment("1");
        d1.printAppointments();
    }
}