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
public class Key implements Serializable{
    
    private int idroom = 0;
    private String name = null;
    private String desc = null;
    
    public Key(int idroom,String name,String desc){
        this.idroom = idroom;
        this.name = name;
        this.desc = desc;
    }

    public int getOpenDoor(){
        return this.idroom;
    }
    
    public String getDescription(){
        return desc;
    }
    
    public String getName(){
        return this.name;
    }
    /*
     Writetofile<Key> write = new Writetofile<>("key.dat");
        Key key = new Key(504,"chiave grigia","una grigia chiave, dall'aspetto sembra la chiave di una botola."); 
         write.write_file(key);
         key = new Key(203,"chiave nera","una grossa chiave nera con una testa a forma di croce.");
         write.write_file(key);
         key = new Key(500,"chiave bianca","una piccola e raffinata chiave.");
         write.write_file(key);
         key = new Key(702,"chiave vecchia","una vecchia chiave usarata e arruginita.");
         write.write_file(key);
         
    */
}
