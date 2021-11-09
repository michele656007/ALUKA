/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enchantment;

import java.io.Serializable;

/**
 *
 * @author miche
 */
/*
    La stringa effect puo' essere di due tipi: 
    1) Danno  :1d4%4m5 dove 1d4 indica il danno dell'incantesimo e %4 ogni quanti lv si aggiunge un dado all'incantesimo
        m5 numero massimi di dadi
        4d6%4r3
    2) Buffer :FOR4 indica la caratteristica da buffere e di quanto deve essere aumentata
*/
public class Spel implements Serializable{
    
    private String name = null;
    private String effect = null;
    private String description = null;
    private Boolean area = false;
    private int lv = 0;
    
    public Spel(String name,String effect,String desc,Boolean area,int lv){
        this.name = name;
        this.effect = effect;
        this.description = desc;
        this.area = area;
        this.lv = lv;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getEffect(){
        return effect;
    }
    public String getDescr(){
        return this.description;
    }
    
    public Boolean getArea(){
        return this.area;
    }
    
    public int getSpelLv(){
        return this.lv;
    }
}
/*
elenco incantesimi 

 Writetofile<Spel> write = new Writetofile<>("spel.dat");
        String desc = null;
         desc = "Questa blasfemia del sangue genera dalle punta delle tue dita dei cristalli color rosso sangue che scagli contro un tuo nemico. Causa 1 d4 di danni per dardo, ogni livello avrai un dardo in piu' max 5, ti causa 1 danno di sanita' mentale.";
        write.write_file(new Spel("dardo di sangue", "1d4%1m5", desc, false, 2));

        desc = "Questa blasfemia genera dai palmi delle tue mani due piccoli vortici di sangue e fuoco che brucia tutti i tuoi nemici. Causa un d5 di danno, ogni livello avrai un d5 in piu', max 5d5,ti causa 2 danni di sanita' mentale.";
        write.write_file(new Spel("mani brucianti", "1d5%1m5", desc, true, 3));

        desc = "Questo insulto alla vita genera un raggio di sangue rovente che fuori esce dalla punta del tuo dito, perforando e bruciando un tuo nemico, causando 4 d6 di danno per raggio, ogni 4 livelli ottieni un raggio in piu', ogni utilizzo ti causa 8 danni di sanita' mentale";
        write.write_file(new Spel("sangue rovente", "4d6%4r3", desc, true, 8));

        desc = "Questa blasfemia insegna come rume rubare la vita altri per rinvigore la propria non vita(aggiungi meta' del danno), causa 1 d6 di danno e ogni 2 livelli aggiunge un dado, max 5 e ti causa 10 danni di sanità mentale.";
        write.write_file(new Spel("tocco del vampiro", "1d6%2m5", desc, false, 8));

        desc = "Questo incantesimo del sangue genera una palla di fuoco nero e sangue, un piccolo sole malvagio pronto a bruciare tutto, causa 1 d6 di danni, ad ogni livello aggiungi un dado max 10, ti causa 6 danni di sanita' mentale.";
        write.write_file(new Spel("fuoco oscuro","1d6%2m10",desc,true,6));
        
        desc = " Secondo le antiche credenze questo incantesimo del sangue e' stato il primo rigurgito del signore dei demoni Orcus, genera un turbine di anime sofferenti e sangue che causa 4 d12 di danno, ogni 5 livelli ottieni 4 dadi in piu', ti causa 20 danni di sanità mentale.";
        write.write_file(new Spel("brimston","4d12%4r5",desc,true,20));
        
        write.close_file();
*/