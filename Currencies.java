/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bofa;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wassi
 */
public class Currencies {
    final String acronym;
    final String name;
    double volume;
    double price;
    final int maxChange;

    public Currencies(String acronym, String name, double volume, double price, int maxChange) {
        this.acronym = acronym;
        this.name = name;
        this.volume = volume;
        this.price = price;
        this.maxChange = maxChange;
    }

    public String getAcronym() {
        return acronym;
    }


    public int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
}
    
    public String getName() {
        return name;
    }


    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxChange() {
        return maxChange;
    }
    public void checkPrice(){
        double change = (100+getRandomNumber(-getMaxChange(), getMaxChange()));
        price = getPrice() * (change/100);
        if(change > 100){
            System.out.println("Currency price is now at: ");
            System.out.print("\u001B[32m" + price + "\u001B[0m" + ". Price change of: +" + "\u001B[32m" + (-100+change) + "%" + "\u001B[0m");
        }
        else if(change < 100){
            System.out.println("Currency price is now at: ");
            System.out.print("\u001B[31m" + price + "\u001B[0m" + ". Price change of: -" + "\u001B[31m" + (100-change) + "%" + "\u001B[0m");
        }
        setPrice(price);
    }
    public void buyPrice(){
        double change = (100+getRandomNumber(0, getMaxChange()));
        price = getPrice() * (change/100);
        System.out.println("Currency price is now at: ");
        System.out.print("\u001B[32m" + price + "\u001B[0m" + ". Price change of: +" + "\u001B[32m" + (-100+change) + "%" + "\u001B[0m");
        setPrice(price);
    }
    public void sellPrice(){
        double change = (100-getRandomNumber(0, getMaxChange()));
        price = getPrice() * (change/100);
        System.out.println("Currency price is now at: ");
        System.out.print("\u001B[31m" + price + "\u001B[0m" + ". Price change of: -" + "\u001B[31m" + (100-change) + "%"  + "\u001B[0m");
        setPrice(price);
    }
    public String toString(){
        return getAcronym() + " " +getName() + " price: " + getPrice() +  " volume available: " + getVolume();
    }
}
