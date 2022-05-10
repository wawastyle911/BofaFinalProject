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
public abstract class Transac {
    Currencies c;
    double volume;
    final String type;

    public Transac(Currencies c, double volume, String type) {
        this.c = c;
        this.volume = volume;
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    public Currencies getC() {
        return c;
    }

    public void setC(Currencies c) {
        this.c = c;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
    
    public static ArrayList<Currencies> cryptos = new ArrayList<>();
    static Currencies BTC = new Currencies("BTC", "bitcoin", 100.0 , 30000.0 , 10);
    static Currencies BZR = new Currencies("BZR" , "bazerman coin", 112.0 , 1000.0 , 15);
    static Currencies MON = new Currencies("MON", "mona coin" , 115.0 , 450.0 , 18);
    static Currencies POL = new Currencies("POL" , "polygon" , 117.0 , 300.0 , 22);
    static Currencies MNK = new Currencies("MNK", "moonknight coin" , 120.0 , 275.0 , 23);
    static Currencies LEO = new Currencies("LEO","leo coin" , 123.0 , 200.0 , 26);
    static Currencies ZBI = new Currencies("ZBI","zebi coin" , 128.0 , 225.0 , 27);
    static Currencies SAK = new Currencies("SAK","sakku coin" , 130.0 , 175.0 , 28);
    static Currencies LTC = new Currencies("LTC","litecoin" , 150.0 , 160.0 , 29);
    static Currencies LNA = new Currencies("LNA","luna" , 200.0 , 130.0 , 33);
    static Currencies MAR = new Currencies("MAR","marvel coin" , 222.0 , 110.0 , 36);
    static Currencies SFM = new Currencies("SFM","safemoon coin" , 250.0 , 103.0 , 42);
    static Currencies WAS = new Currencies("WAS","wassim coin" , 350.0 , 99.0 , 46);
    static Currencies PND = new Currencies("PND","shoaib coin" , 375.0 , 33.0 , 50);
    static Currencies SID = new Currencies("SID","sidemen coin" , 380.0 , 29.0 , 52);
    static Currencies CND = new Currencies("CND","candice coin" , 400.0 , 25.0 , 54);
    static Currencies ALC = new Currencies("ALC","alice coin" , 403.0 , 7.0 , 55);
    static Currencies NMD = new Currencies("NMD","nomad coin" , 433.0 , 5.0 , 59);
    static Currencies CRO = new Currencies("CRO","crypto.com coin" , 450.0 , 3.0 , 60);
    public abstract void buy(Currencies c, double vol);
    public abstract void sell(Currencies c, double vol);
    public static ArrayList<Currencies> sortByName(){
        cryptos.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return cryptos;
    }
    public static ArrayList<Currencies> sortByPrice(){
        cryptos.sort((o1, o2) -> (((Double)o1.getPrice()).compareTo((Double)o2.getPrice())));
        return cryptos;
    }
    public String toString(){
        return getC() + ", volume: " + getVolume() + ", " + getType() + "\n";
    }
}
