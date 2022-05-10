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
public class Client {
    String name;
    String passWord;
    ArrayList<Account> accounts = new ArrayList<>();
    public Client(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public String toString(){
        return getName();
    }
    
}
