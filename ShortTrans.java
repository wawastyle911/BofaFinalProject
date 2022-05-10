/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bofa;

/**
 *
 * @author wassi
 */
public class ShortTrans extends Transac{

    public ShortTrans(Currencies c, double volume, String type) {
        super(c, volume, type);
    }

    
    @Override
    public void buy(Currencies c, double vol) {
        System.out.println("Borrowed a volume of: " + vol + " and sold at price: " + c.getPrice() );
        c.sellPrice();
    }
    @Override
    public void sell(Currencies c, double vol){
        System.out.println("Bought a volume of: " + vol + " at price: " + c.getPrice() +" and gave it back to the loaner. ");
        c.buyPrice();
    }
    
}
