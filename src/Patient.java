import java.util.ArrayList;
import java.util.List;

public class Patient {

    private int id;
    private String name;
    private int age;
    private int balance;
    private List<String> allergies;

    public Patient() {
        this.allergies = new ArrayList<>();
    }

    public Patient(int id, String name, int age, int balance){
        this.allergies = new ArrayList<>();
        setId(id);
        setName(name);
        setAge(age);
        setBalance(balance);
    }

    public int getId(){return id;}
    public boolean setId(int id){
        if(id<=0){
            return false;
        }
        this.id=id;
        return true;
    }

    public String getName() {return name;}
    public boolean setName(String name){
        if(name==null || name.isBlank()){
            return false;
        }
        this.name=name;
        return true;
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

    public boolean charge(int cost){
        if(cost<0){
            throw new IllegalArgumentException("Charge can't be < 0.");
        }else if(balance-cost<0){
            System.out.println("Not enough balance.");
            return false;
        }
        balance-=cost;
        return true;
    }

    public List<String> getAllergies() {
        return new ArrayList<>(allergies);
    }
    public void addAllergy(String allergy) {
        if (allergy == null || allergy.isBlank()){
            throw new IllegalArgumentException("Allergy cannot be empty.");
        }
        if (!allergies.contains(allergy)){
            allergies.add(allergy);
        }
    }

    @Override
    public String toString() {
        return "Patient : " + name
                + ", id : "+id
                + ", age : "+ age
                + ", balance : "+balance+".";
    }
}
