/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipe;

import enumeration.Typeitem;

/**
 *
 * @author miche
 */
public class Item{
    
    private Armor armor = null;
    private Weapon weapon = null;
    private Shield shield = null;
    private Potion potion = null;
    private Key key = null;
    private Book book = null;
    private Typeitem type;
    
    public Item(Key key){
        this.key = key;
        type = Typeitem.key;
    }
    public Item(Armor armor) {
        this.armor = armor;
        type = Typeitem.armor;
    }
    public Item(Weapon weapon){
        this.weapon = weapon;
        type = Typeitem.weapon;
    }
    public Item(Shield shield){
        this.shield = shield;
        type = Typeitem.shield;
    }
    public Item(Potion potion){
        this.potion = potion;
        type = Typeitem.potion;
    }
    public Item(Book book){
        this.book = book;
        type = Typeitem.book;
    }
    public Typeitem getType(){
        return type;
    }
    
    public String getNameItem(){
        if(this.armor!=null)
            return armor.getName();
        if(this.potion!=null)
            return potion.getName();
        if(this.shield!=null)
            return shield.getName();
        if(this.weapon!=null)
            return weapon.getName();
        if(this.key!= null)
            return key.getName();
        if(this.book!=null)
            return book.getName();
        return null;
    }
    
    public Armor getArmor(){
        return this.armor;
    }
    
    public Weapon getWeapon(){
        return this.weapon;
    }
    
    public Shield getShield(){
        return this.shield;
    }
    public Potion getPotion(){
        return this.potion;
    }
    public Key getKey(){
        return this.key;
    }
    public Book getBook(){
        return this.book;
    }
    
    public int getLvItem(){
        if(this.shield!=null)
            return shield.getLV();
        if(this.weapon!=null)
            return weapon.getLV();
        
        return 0;
    }
}
