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
public class LongTrans extends Transac{

    public LongTrans(Currencies c, double volume, String type) {
        super(c, volume, type);
    }

    
    @Override
    public void buy(Currencies c, double vol) {
        c.setVolume(c.getVolume()-vol);
        System.out.println("Bought volume of: " + vol + " at price: " + c.getPrice());
        c.buyPrice();
    }

    @Override
    public void sell(Currencies c, double vol) {
        c.setVolume(c.getVolume() + vol);
        System.out.println("Sold volume of: " + vol + " at price: " + c.getPrice());
        c.sellPrice();
    }
    
}
