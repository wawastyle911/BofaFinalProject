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
public interface IAccount {
    public void deposit(int amount);
    public void viewOwed();
    public void viewOwned();
    
    
}
