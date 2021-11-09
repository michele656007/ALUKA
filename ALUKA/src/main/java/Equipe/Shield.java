/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipe;

import enumeration.Hand;
import java.io.Serializable;

/**
 *
 * @author miche
 */
public class Shield extends Weapon implements Serializable{
  
    public Shield(String name, String damage, String desc, Hand hand, int price, int lv) {
        super(name, damage, desc, hand, price, lv);
    }
    
    public String getDefence(){
        return super.getDamage();
    }
    
    @Override
    public int getPrice(){
        return super.getPrice();
    }
}
/*
String desc = null;
        Writetofile<Shield> write = new Writetofile<>("shield.dat");
        desc = "Tre assi di legno inchiodate tra di loro.Puoi trovare di meglio. Difende per 1 d4.";
        write.write_file(new Shield("scudo di legno","1d4",desc,Hand.LEFT,50,1));
        desc = "Uno scudo realizzato con ferro e legno per dargli stabilita e leggerezza. Difende per 1 d6.";
        write.write_file(new Shield("scudo di ferro","1d6",desc,Hand.LEFT,100,1));
        desc = "Scudo a torre in acciaio, nient'altro da dire. Difende per 1 d8.";
        write.write_file(new Shield("scudo di acciaio","1d8",desc,Hand.LEFT,200,1));
*/