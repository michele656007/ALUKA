/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipe;

import java.io.Serializable;

/**
 *
 * @author miche
 */
public class Armor implements Serializable {
    private String name = null;
    private String description = null;
    private int CA = 0;
    private int price = 0;
    
    public Armor(String name,String descr,int CA,int price){
        this.name = name;
        this.description = descr;
        this.CA = CA;
        this.price = price;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public int getCA(){
        return CA;
    }
    
    public int getPrice(){
        return this.price;
    }
}
/*
desc = "Una semplice armatura di pelle con rifiniture in ferro per farla un po' di piu'. Aumenta la CA di +1.";
        write.write_file(new Armor("armatura di pelle",desc,1,50));
        
        desc = "Un'armatura fatta in ferro, molto resistente e pesante.Aumenta la CA di +3.";
        write.write_file(new Armor("armatura di ferro",desc,3,300));
        
        desc = "Questa e' l'armatura che tutti vorrebbero, realizzata in placche di acciaio e' resistente e leggera.Aumenta la CA di +5.";
        write.write_file(new Armor("armatura di acciaio",desc,5,500));
        
*/