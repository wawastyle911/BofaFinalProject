/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bofa;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author wassi
 */
public class UserInputManager {
    static int i = 0;
    static int j = 0;
    public static Client retrieveClientInfo() {
          Scanner s = new Scanner(System.in);
          System.out.println("Please enter client name: ");
          String name = s.nextLine();
          System.out.println("enter password: ");
          String password = s.nextLine();
          Client c = new Client(name, password);
          return c;
    }
    public static Client login(){
        
        Scanner s = new Scanner(System.in);
          System.out.println("Please enter client name: ");
          String name = s.nextLine();
          System.out.println("enter password: ");
          String password = s.nextLine();

        for (i = 0; i < Bofa.getClients().size(); i++) {

            if(Bofa.getClients().get(i).getName().equals(name) && Bofa.getClients().get(i).getPassWord().equals(password)){

                return  Bofa.getClients().get(i);
            }
        }
            System.out.println("incorrect info");
            return null;

    }
    public static Account newAccount(){
        Scanner s = new Scanner(System.in);
        int input = 0;
        System.out.println(
            "[1] new saving account \n" +
            "[2] new regular account \n" +
            "[0] leave"    );
        input = s.nextInt();
        do{
            switch(input){
                case 1:
                    System.out.println("what is the maximum percent volatility you want to tolerate: ");
                    int maxChange = s.nextInt();
                    Account sa = new SavingAccount(30000, maxChange);
                    System.out.println("New savings account successfuly created. Base balance is 30000. id is " + sa.getId());
                    Bofa.getClients().get(i).getAccounts().add(sa);
                    return sa;
                case 2:
                    Account a = new Account(100000);
                    System.out.println("New account successfuly created. Base balance is 100000. id is " + a.getId());
                    Bofa.getClients().get(i).getAccounts().add(a);
                    return a;
                
            }
        }while(input != 0);
        return null;
    }
    public static void sort(){
        Scanner s = new Scanner(System.in);
        int input = 0;   
        do{ 
            System.out.println(
            "[1] sort currencies by name \n" +
            "[2] sort currencies by price \n" +
            "[0] leave"    );
            input = s.nextInt();
            switch(input){
                case 1:
                    System.out.println(Transac.sortByName());
                    break;
                case 2:
                    System.out.println(Transac.sortByPrice());
                    break;
                
            }
        }while(input != 0);

    }
    public static Account selectAccount(){
        Scanner s = new Scanner(System.in);
          System.out.println("Please enter account id: ");
          int id = s.nextInt();
        for (j = 0; j < Bofa.getClients().get(i).getAccounts().size(); j++) {

            if(Bofa.getClients().get(i).getAccounts().get(j).getId() == id){
                return Bofa.getClients().get(i).getAccounts().get(j);
            }
        }
            System.out.println("incorrect id");
            return null;

    }
    public static void deposit(Account a){
        Scanner s = new Scanner(System.in);
        System.out.println("enter amount you wish to deposit: ");
        int am = s.nextInt();
        a.setBalance(a.getBalance() + am);
        System.out.println("new balance is : " + a.getBalance());
    }
    public static Transac buy(Account a){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter crypto name: ");
        String cryp = s.nextLine();
        System.out.println("Enter volume desired");
        int vol = s.nextInt();
        for (int k = 0; k < Transac.cryptos.size(); k++) {
            if(cryp.equals( Transac.cryptos.get(k).getAcronym()) ||  cryp.equals( Transac.cryptos.get(k).getName())){
                Transac.cryptos.get(k).checkPrice();
                double cost = vol*Transac.cryptos.get(k).getPrice();
                System.out.println("Cost is at: " + cost);
                if(a.getBalance()> (cost) && a.getMaxChange() >= Transac.cryptos.get(k).getMaxChange()){
                    Transac t = new LongTrans(Transac.cryptos.get(k), vol, "Long buy");
                    double bal = (a.getBalance() - cost);
                    a.setBalance(bal);
                    t.buy(Transac.cryptos.get(k), vol);
                    a.getTransacs().add(t);
                    Account.getPubTrans().add(t);
                    return t;
                }
                else if(a.getMaxChange() < Transac.cryptos.get(k).getMaxChange()){
                    System.out.println("This currency is too risky for you");
                    return null;
                }
                else{
                    System.out.println("balance insufficient.");
                    return null;
                }
                
            }
        }
        System.out.println("Currency doesn't exist");
        return null;
    }
    public static Transac sell(Account a){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter crypto name: ");
        String cryp = s.nextLine();
        for (int k = 0; k < a.getTransacs().size(); k++) {
            if(a.getTransacs().get(k).getC().getAcronym().equals(cryp) || a.getTransacs().get(k).getC().getName().equals(cryp)){
                a.getTransacs().get(k).getC().checkPrice();
                double Return = a.getTransacs().get(k).getVolume()* a.getTransacs().get(k).getC().getPrice();
                System.out.println("return is at: " + Return);
                Transac t = new LongTrans( a.getTransacs().get(k).getC(), a.getTransacs().get(k).getVolume(), "Long sell");
                a.setBalance(a.getBalance()+Return);
                t.sell( a.getTransacs().get(k).getC(), a.getTransacs().get(k).getVolume());
                a.getTransacs().remove(a.getTransacs().get(k));
                Account.getPubTrans().add(t);
                return t;
            }
        }
        System.out.println("you don't own this crypto.");
        return null;
    }
    public static Transac ShortS(Account a){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter crypto name: ");
        String cryp = s.nextLine();
        System.out.println("Enter volume desired");
        int vol = s.nextInt();
        for (int k = 0; k < Transac.cryptos.size(); k++) {
            if(cryp.equals( Transac.cryptos.get(k).getAcronym()) ||  cryp.equals( Transac.cryptos.get(k).getName())){
                if(a.getMaxChange() >= Transac.cryptos.get(k).getMaxChange()){
                    double cost = vol*Transac.cryptos.get(k).getPrice();
                    System.out.println("Return is at: " + cost);
                    Transac t = new ShortTrans(Transac.cryptos.get(k), vol, "Short entry");
                    a.setBalance(a.getBalance() + Transac.cryptos.get(k).getPrice()*vol);
                    t.buy(Transac.cryptos.get(k), vol);
                    a.getShorts().add(t);
                    Account.pubTrans.add(t);
                    return t;
                }
                else if(a.getMaxChange() < Transac.cryptos.get(k).getMaxChange()){
                    System.out.println("This currency is too risky for you");
                    return null;
                }
            }
        }
        System.out.println("currency doesn't exist.");
        return null;
        
    }
     public static Transac ShortB(Account a){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter crypto name: ");
        String cryp = s.nextLine();
        for (int k = 0; k < a.getShorts().size(); k++) {
            if(a.getShorts().get(k).getC().getAcronym().equals(cryp) || a.getShorts().get(k).getC().getName().equals(cryp)){
                if(a.getBalance()> a.getShorts().get(k).getC().getPrice()*a.getShorts().get(k).getVolume()){
                    double Return = a.getShorts().get(k).getVolume()* a.getShorts().get(k).getC().getPrice();
                    System.out.println("Cost is at: " + Return);
                    Transac t = new ShortTrans(a.getShorts().get(k).getC(), a.getShorts().get(k).getVolume(), "Short exit");
                    a.setBalance(a.getBalance()-Return);
                    t.sell(a.getShorts().get(k).getC(), a.getShorts().get(k).getVolume());
                    a.getShorts().remove(a.getShorts().get(k));
                    Account.pubTrans.add(t);
                    return t;
                            
                }
                else{
                    System.out.println("insufficient funds");
                    return null;
                }
            }
        }
         System.out.println("you don't owe any of this currency.");
        return null;
     }
     
     public static void checkP(){
         Scanner s = new Scanner(System.in);
         System.out.println("what crypto do you want to check: ");
         String cryp = s.nextLine();
         for (int k = 0; k < Transac.cryptos.size(); k++) {
             if(cryp.equals( Transac.cryptos.get(k).getAcronym()) ||  cryp.equals( Transac.cryptos.get(k).getName())){
                 Transac.cryptos.get(k).checkPrice();
             }
         }
     }

    public static void writeFile(ArrayList<Transac> obj, String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(filename);



            for (int i = 0; i < obj.size(); i++) {
                String s = obj.get(i).toString();
                byte b[] = s.getBytes();
                fout.write(b);
            }
            fout.close();


            System.out.println("sucess...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void file(String filename) {
        File file = new File(filename);
        if (!Desktop.isDesktopSupported()) {
            System.out.println("not supported");
        }

        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
    
}
