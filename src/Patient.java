import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String id;
    private String name;
    private int age;
    private int balance;
    private List<String> allergies;

    public Patient() {
        this.allergies = new ArrayList<>();
    }

    public Patient(String id, String name, int age, int balance){
        this.allergies = new ArrayList<>();
        setId(id);
        setName(name);
        setAge(age);
        setBalance(balance);
    }

    public String getId(){return id;}
    public void setId(String id){
        if(id==null || id.isBlank()){
            throw new IllegalArgumentException("Patient id can't be empty.");
        }
        this.id=id;
    }

    public String getName() {return name;}
    public void setName(String name){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Patient name can't be empty.");
        }
        this.name=name;
    }

    public int getAge(){return age;}
    public void setAge(int age){
        if(age<=0){
            throw new IllegalArgumentException("Patient age must be > 0.");
        }
        this.age=age;
    }

    public int getBalance(){return balance;}
    public void setBalance(int balance){
        if(balance<0){
            throw new IllegalArgumentException("Patient balance must be cant be negative..");
        }
        this.balance=balance;
    }

    //method to charge for procedure or smth like that
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

    // method to add balance
    public void addBalance(int number){
        if(number<=0){
            throw new IllegalArgumentException("Number to add to balance can't be zero or less");
        }
        balance+=number;
    }

    //allergy
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
