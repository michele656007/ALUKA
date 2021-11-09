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
// effetti tipo : hp1d8 ripristina salute
//                sa1d8 ripristina sanità mentale
//classe che definisce le pozioni
public class Potion implements Serializable {

    private String name = null;
    private String effect = null;
    private int price = 0;
    private String desc = null;

    public Potion(String name, String effect, int price, String desc) {
        this.effect = effect;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public String getName() {
        return this.name;
    }

    public String getEffect() {
        return effect;
    }

    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return this.desc;
    }

}
/*
        desc = "Una piccola pozione di cura che ti cura per 1d8.";
        write.write_file(new Potion("cura piccola","hp1d8",20,desc));
        desc = "Una media pozione di cura che ti cura per 2d10";
        write.write_file(new Potion("cura media","hp2d10",50,desc));
        desc = "Una pozione di cura grande che cura per 3d12";
        write.write_file(new Potion("cura grande","hp3d12",100,desc));  
        
        desc = "Una piccola pillolina bianca che calma la tua mente per 1d8";
        write.write_file(new Potion("traquillante","sa1d8",5,desc)); 
        desc = "Una piccola pillolina bianca che calma la tua mente per 2d8";
        write.write_file(new Potion("calmante","sa2d8",15,desc)); 
*/
