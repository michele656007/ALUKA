/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistant;

import Equipe.Armor;
import Equipe.Potion;
import Equipe.Shield;
import Equipe.Weapon;
import I_O_to_file.Readtofile;
import enchantment.Miracle;
import enchantment.Spel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author miche
 */
public class Merchant {
    
    private ArrayList<Elementcatalog> catalog = null;
    
    public Merchant(){
        this.catalog = new ArrayList<>();
    }
    
    public Boolean searchMerchant(int idroom){
        if(102 == idroom){
            priest();
            return true;
        }
        if(104 == idroom){
            this.gunsmith();
            return true;
        }
        if(105 == idroom){
            this.alchemist();
            return true;
        }
        if(502 == idroom){
            this.demon();
            return true;
        }
        return false;
    }
    
    public ArrayList<Elementcatalog> takeCatalog(){
        return this.catalog;
    }
    
    private void priest(){
        
        this.catalog = new ArrayList<>();
        
        Readtofile<Miracle> read = new Readtofile<>("miracle.dat");
        Miracle miracle = null;
        do{
            miracle = read.readtofile();
            if(miracle!= null){
                System.out.println(miracle.getSpelLv());
                this.catalog.add(new Elementcatalog(miracle.getName(),(miracle.getSpelLv()*100)));
            }
        }while(miracle!= null);
    }
    
    private void gunsmith(){
        
        this.catalog = new ArrayList<>();
        
        Readtofile<Armor> readar = new Readtofile<>("armor.dat");
        Armor armor = null;
        do{
            armor = readar.readtofile();
            if(armor!= null){
                this.catalog.add(new Elementcatalog(armor.getName(),armor.getPrice()));
            }
        }while(armor!= null);
        
        Readtofile<Shield> readsh = new Readtofile<>("shield.dat");
        Shield shield = null;
        do{
            shield = readsh.readtofile();
            if(shield!= null){
                this.catalog.add(new Elementcatalog(shield.getName(),shield.getPrice()));
            }
        }while(shield!= null);
        
        Readtofile<Weapon> readwe = new Readtofile<>("weapon.dat");
        Weapon weapon = null;
        do{
            weapon = readwe.readtofile();
            if(weapon!= null){
                this.catalog.add(new Elementcatalog(weapon.getName(),weapon.getPrice()));
            }
        }while(weapon!= null);
    }
    
    private void demon (){
        Readtofile<Spel> read = new Readtofile<>("spel.dat");
        
        this.catalog = new ArrayList<>();
        Spel spel = null;
        do{
            spel = read.readtofile();
            if(spel!= null){
                System.out.println(spel.getName()+spel.getSpelLv());
                this.catalog.add(new Elementcatalog(spel.getName(),(spel.getSpelLv()*2)));
            }
        }while(spel!= null);
    }
    
    private void alchemist(){
        Readtofile<Potion> read = new Readtofile<>("potion.dat");
        this.catalog = new ArrayList<>();
        Potion potion = null;
        do{
            potion = read.readtofile();
            if(potion!= null){
                this.catalog.add(new Elementcatalog(potion.getName(),potion.getPrice()));
            }
        }while(potion!= null);
    }
    
    public int searchinCatalog(String name){
        Iterator ite = this.catalog.iterator();
        Elementcatalog elemcat = null;
        while(ite.hasNext()){
            elemcat = (Elementcatalog)ite.next();
            if(elemcat.getElem().equals(name))
                return elemcat.getPrice();
        }
        return 0;
    }
}
