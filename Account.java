/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bofa;

import java.util.ArrayList;

/**
 *
 * @author wassi
 */
public class Account implements IAccount{
    double balance ;
    int id = 0;
    final int maxChange;
    static int counter = 0;
    static final String type = "a";
    public static ArrayList<Transac> pubTrans = new ArrayList<>();
    public ArrayList<Transac> transacs = new ArrayList<>();
    public ArrayList<Transac> shorts = new ArrayList<>();
    public Account(double balance){
        if(SavingAccount.getCounter() >= counter){
            counter = SavingAccount.getCounter();
            counter ++;
            this.id = counter;}
            else{
            counter++;
            this.id++;}
        this.balance = balance;
        this.maxChange = 100;

    }

    public int getMaxChange() {
        return maxChange;
    }
    
    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Account.counter = counter;
    }
    
    public static String getType() {
        return type;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public void deposit(int amount) {
        balance += amount;
            }


    @Override
    public void viewOwed() {
        System.out.println(shorts);
    }

    @Override
    public void viewOwned() {
        System.out.println(transacs);
    }

    public static ArrayList<Transac> getPubTrans() {
        return pubTrans;
    }

    public ArrayList<Transac> getTransacs() {
        return transacs;
    }

    public ArrayList<Transac> getShorts() {
        return shorts;
    }
    public String toString(){
        return "Account " + getId();
    }
}
