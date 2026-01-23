import java.util.ArrayList;
import java.util.List;

public class Patient extends Person{
    private int age;
    private int balance;
    private List<String> allergies;

    public Patient() {

    }

    public Patient(int id, String name, int age, int balance){
        this.allergies = new ArrayList<>();
        setId(id);
        setName(name);
        setAge(age);
        setBalance(balance);
    }


    public int getAge(){return age;}
    public boolean setAge(int age){
        if(age<=0){
            return false;
        }
        this.age=age;
        return true;
    }

    public int getBalance(){return balance;}
    public boolean setBalance(int balance){
        if(balance<0){
            return false;
        }
        this.balance=balance;
        return true;
    }

    //method to charge for procedure or smth like that
    public boolean charge(int cost){
        if(cost<0){
            return false;
        }else if(balance-cost<0){
            return false;
        }
        balance-=cost;
        return true;
    }

    // method to add balance
    public boolean addBalance(int number){
        if(number<=0){
            return false;
        }
        balance+=number;
        return true;
    }


    @Override
    public String getRole() { return "Patient"; }

    @Override
    public String toString() {
        return "Patient : " + name
                + ", id : "+id
                + ", age : "+ age
                + ", balance : "+balance+".";
    }
}
