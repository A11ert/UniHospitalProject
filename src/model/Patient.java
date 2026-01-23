package model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Payable {
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
    public void setAge(int age){
        if(age<0){
            throw new IllegalArgumentException("Age can't be negative");
        }
        this.age=age;
    }

    public int getBalance(){return balance;}
    public void setBalance(int balance){
        if(balance<0){
            throw new IllegalArgumentException("Balance can't be negative");
        }
        this.balance=balance;
    }

    //method to charge for procedure or smth like that
    public void charge(int cost){
        if(cost<0){
            throw new IllegalArgumentException("Cost of charge can't be negative");
        }else if(balance-cost<0){
            throw new IllegalArgumentException("Not enough money in balance");
        }
        balance-=cost;
    }

    // method to add balance
    @Override
    public void addBalance(int number){
        if(number<=0){
            throw new IllegalArgumentException("Number should be positive");
        }
        balance+=number;
    }


    @Override
    public String getRole() { return "exceptions.Patient"; }

    @Override
    public void work() {
        System.out.println("Patient " + name + " is receiving treatment.");
    }

    @Override
    public String toString() {
        return "exceptions.Patient : " + name
                + ", id : "+id
                + ", age : "+ age
                + ", balance : "+balance+".";
    }
}
