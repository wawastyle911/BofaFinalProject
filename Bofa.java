/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bofa;

import static bofa.Transac.ALC;
import static bofa.Transac.BTC;
import static bofa.Transac.BZR;
import static bofa.Transac.CND;
import static bofa.Transac.CRO;
import static bofa.Transac.LEO;
import static bofa.Transac.LNA;
import static bofa.Transac.LTC;
import static bofa.Transac.MAR;
import static bofa.Transac.MNK;
import static bofa.Transac.MON;
import static bofa.Transac.NMD;
import static bofa.Transac.PND;
import static bofa.Transac.POL;
import static bofa.Transac.SAK;
import static bofa.Transac.SFM;
import static bofa.Transac.SID;
import static bofa.Transac.WAS;
import static bofa.Transac.ZBI;
import static bofa.Transac.cryptos;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author wassi
 */
public class Bofa {
    
    /**
     * @param args the command line arguments
     */
    public static ArrayList<Client> clients = new ArrayList<>();

    public static ArrayList<Client> getClients() {
        return clients;
    }
    
    public static void main(String[] args) {
        cryptos.add(BTC);
        cryptos.add(BZR);
        cryptos.add(MON);
        cryptos.add(POL);
        cryptos.add(MNK);
        cryptos.add(LEO);
        cryptos.add(ZBI);
        cryptos.add(SAK);
        cryptos.add(LTC);
        cryptos.add(LNA);
        cryptos.add(MAR);
        cryptos.add(SFM);
        cryptos.add(WAS);
        cryptos.add(PND);
        cryptos.add(SID);
        cryptos.add(CND);
        cryptos.add(ALC);
        cryptos.add(NMD);
        cryptos.add(CRO);
        Scanner sc = new Scanner(System.in);
        int input = 0;
        int input1 = 0;
        int input2 = 0;
    do{
        System.out.println(
          "[1] Register new Client \n"
        + "[2] Login \n" +
          "[0] leave"    );
        input = sc.nextInt();
        switch(input){
            case 1:
                clients.add(UserInputManager.retrieveClientInfo());
                break;
            case 2:
                Client c = UserInputManager.login();
                if(c != null){
                System.out.println("Welcome " +c);
                do{
                    System.out.println(
                    "[1] new account \n"+
                    "[2] sort crypto \n" +
                    "[3] select account \n" +
                    "[4] save all transactions to file \n" +
                    "[5] view file \n" +
                    "[0] leave"    );
                    input1 = sc.nextInt();
                    switch(input1){
                        case 1:
                            UserInputManager.newAccount();
                            break;
                        case 2:
                            UserInputManager.sort();
                            break;
                        case 3:
                            Account a = UserInputManager.selectAccount();
                            if(a!=null){
                                System.out.println("Now working on account: " + a);
                                do{
                                    System.out.println(
                                    "[1] view fiat balance \n"+
                                    "[2] view owned crypto \n" +
                                    "[3] view owed crypto \n" +
                                    "[4] add to fiat balance \n" +
                                    "[5] buy crypto \n" +
                                    "[6] sell crypto \n" +
                                    "[7] short crypto \n" +
                                    "[8] pay back owed crypto \n" +
                                    "[9] check crypto price \n" +
                                    "[0] leave"    );
                                    input2 = sc.nextInt();
                                    switch(input2){
                                        case 1:
                                            System.out.println(a.getBalance());
                                            break;
                                        case 2:
                                            System.out.println(a.getTransacs());
                                            break;
                                        case 3:
                                            System.out.println(a.getShorts());
                                            break;
                                        case 4:
                                            UserInputManager.deposit(a);
                                            break;
                                        case 5:
                                            UserInputManager.buy(a);
                                            break;
                                        case 6:
                                            UserInputManager.sell(a);
                                            break;
                                        case 7:
                                            UserInputManager.ShortS(a);
                                            break;
                                        case 8:
                                            UserInputManager.ShortB(a);
                                            break;
                                        case 9: 
                                            UserInputManager.checkP();
                                            break;
                                            
                                    }
                                }while(input2 != 0);
                            }
                            break;
                        case 4:
                            UserInputManager.writeFile(Account.getPubTrans(), "Bofa.txt");
                            break;
                        case 5:
                            UserInputManager.file("Bofa.txt");
                            break;
                    }
                }while(input1 != 0);
                }
                break;
            
        }
    }while(input!=0);
        
    }

    
 
}
