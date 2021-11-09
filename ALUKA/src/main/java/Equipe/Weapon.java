package Equipe;

import enumeration.Hand;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author miche
 */
public class Weapon implements Serializable{
        
    private String name = null;
    private String damage = null;
    private String description = null;
    private int lvweapon = 0;
    private Hand hand;
    private int price = 0;
    
    public Weapon(String name, String damage, String desc, Hand hand,int price,int lv){
        this.name = name;
        this.damage = damage;
        this.description = desc;
        this.hand = hand;
        this.price = price;
        this.lvweapon = lv;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDamage(){
        return damage;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public int getLV(){
        return this.lvweapon;
    }
    
    public Hand getHand(){
        return hand;
    }
    public int getPrice(){
        return this.price;
    }
    
    public Boolean weaponLVup() {

        int temp;
        
        if (this.lvweapon < 5) {
            if (this.lvweapon< 4) {
                lvweapon++;
                temp = Integer.parseInt(this.damage.substring(2));
                temp++;
                damage = damage.replace(this.damage.substring(2), String.valueOf(temp).toString());
                price = price +10;
            } else {
                if (this.lvweapon == 4) {
                    lvweapon++;
                    temp = Character.getNumericValue(this.damage.charAt(0));
                    System.out.println(temp);
                    temp++;
                    damage = damage.replace(this.damage.charAt(0),Character.forDigit(temp, 10) );
                    price = price + 50;
                }
            }
            return true;
        }
        return false;
    }
}
/*
desc = "Una semplice spada corta ad una mano. Causa 1 d4 di danni.";
        write.write_file(new Weapon("shortsword","1d4",desc,Hand.RIGHT,50,1));
        
        desc = "Una palla di ferro chiodata attaccata con una catena ad una mazza di legno ,ad una mano. Il buongiorno si vede dal mattino. Causa 1 d6 di danni.";
        write.write_file(new Weapon("morningstar","1d6",desc,Hand.RIGHT,100,1));
        
        desc = "Una lunga lama di acciaio e una corta impugnatura ad una mano. Causa  1 d8 di danno.";
        write.write_file(new Weapon("longsword","1d8",desc,Hand.RIGHT,200,1));
        
        desc = "Un martello da guerra a due mani che causa 1 d8 di danno";
        write.write_file(new Weapon("hammer","1d8",desc,Hand.TWOHANDS,200,1));
        
        desc = "Quest'arma puo' tagliare in due un persone, decapitare un cavallo con un colpo e far tremare ogni tuo aversario.Spadone a due mani.Causa 1 d10 di danno.";
        write.write_file(new Weapon("broadsword","1d10",desc,Hand.TWOHANDS,400,1));
*/