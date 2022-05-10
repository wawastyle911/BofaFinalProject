/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bofa;

/**v
 *
 * @author wassi
 */
public class SavingAccount extends Account{
    final int maxChange;
    static int counter = 0;
    static final String type = "s";
    public SavingAccount(double balance, int maxChange) {
        super(balance);
        if(Account.getCounter() >= counter){
            counter = Account.getCounter();
            counter ++;
            this.id = counter;}
        else{
            counter++;
            this.id = id++;
        }
        this.maxChange = maxChange;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        SavingAccount.counter = counter;
    }
    
    public static String getType() {
        return type;
    }

    
    
    public int getMaxChange() {
        return maxChange;
    }
    public String toString(){
        return "Savings account " + getId();
    }
}
