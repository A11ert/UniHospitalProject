package model;

public interface Payable {
    void addBalance(int amount);
    void charge(int amount);
    int getBalance();
}