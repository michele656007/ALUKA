/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomsystem;

import Equipe.Item;
import enemy.Enemy;
import enumeration.Roomevent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author miche
 */
//classe che rappresenta l'idea di stanza e li luogo aperto o chiuso che porta ad altri luoghi
public class Room {

    private int id_room = 0;
    private Vector<Enemy> enemies = null;
    private Vector<Item> chest = null;
    private ArrayList<Item> ontheground = null;
    private Roomevent event = null;
    private int blockedroom = 0;
    private String chestname = null;
    private String pngname = "noone";

    public Room(int id_room, Roomevent event, Vector<Enemy> enemys, int blockedroom, String pngname) {
        this.id_room = id_room;
        chest = new Vector<>();
        enemies = new Vector<>();
        this.ontheground = new ArrayList<>();
        this.event = event;
        for (int j = 0; j < enemys.size(); j++) {
            this.enemies.add(enemys.get(j));
        }
        this.blockedroom = blockedroom;
        if (pngname != null) {
            this.pngname = pngname;
        }
    }
    @Override
    public Object clone(){
        Room rm = new Room(this.id_room,this.event,this.enemies,this.blockedroom,this.pngname);
        
        rm.setChestname(this.chestname);
        if(!this.chest.isEmpty()){
            for(int i=0;i<chest.size();i++){
                rm.addChest(chest.get(i));
            }
        }
        if(!this.ontheground.isEmpty()){
            Iterator ite = this.ontheground.iterator();
            while(ite.hasNext()){
                Item item = (Item) ite.next();
                System.out.println(item.getNameItem());
                rm.addWeaponontheGround(item);
            }
        }
        return rm;
    }
    //metodo per settare il nome del png che popola la stanza
    public String getPngname() {
        return this.pngname;
    }

    //m per ottenere l'id della stanza
    public int getIdroom() {
        return this.id_room;
    }

    //m per ottenere il vettore contenente gli eventi della stanza
    public Roomevent getEvent() {
        return this.event;
    }

    //m per ottenere una possibile porta o percorso bloccato
    public int getBlockedDoor() {
        return this.blockedroom;
    }

    //m per settare il nome della chest
    public void setChestname(String name) {
        this.chestname = name;
    }

    //m per ottenere il nome della chest
    public String getChestname() {
        return this.chestname;
    }

    //metodo per settare il vettore contenente gli eventi
    public void setEventVector(Roomevent event) {
        this.event = event;
    }

    //m per settare una porta o percorso bloccata
    public void setBlockedDoor(int blockeddoor) {
        this.blockedroom = blockeddoor;
    }

    //m per ottenere il vettore contenente i nemici presenti nella stanza 
    public Vector<Enemy> getEnemies() {
        return this.enemies;
    }

    //m per lasciare oggetti nella cest
    public void addChest(Item item) {
        this.chest.add(item);
    }

    //m per ottenere l'elenco del contenuto della chest
    public Vector<String> getChest() {
        Vector<String> chest = new Vector<>();
        for (int i = 0; i < this.chest.size(); i++) {
            chest.add(this.chest.get(i).getNameItem());
        }
        return chest;
    }

    //m per ottenere l'elenco di cio' che e' per terra
    public Vector<String> getOntheground() {
        Vector<String> chest = new Vector<>();
        Iterator weapite = this.ontheground.iterator();
        Item item = null;
        while (weapite.hasNext()) {
            item = (Item) weapite.next();
            chest.add(item.getNameItem());
        }
        return chest;
    }

    //m per prendere qualcosa dalla chest
    public Item takeItemfronChest(String element) {
        
        Item item = null;
        if (!chest.isEmpty()) { 
            for(int i =0;i<this.chest.size();i++){
                if(this.chest.get(i).getNameItem().equals(element)){
                    item = this.chest.get(i);
                    this.chest.remove(i);
                }
            }
            return item;
        }
        return null;
    }

    //m per svotare il vettore contente i nemici
    public void removeAllEnemies() {
        this.enemies = new Vector<>();
    }

    //metodo per modificare un evento 
    public void modifyEvent(Roomevent event) {
        this.event = event;
    }

    //metodo per lasciare qualcosa per terra
    public void addWeaponontheGround(Item weapon_s) {
        this.ontheground.add(weapon_s);
    }

    //m per prendere qualcosa da terra
    public Item takeitemfromtheground(String weapon_s) {
        Item weapon = null;
        Iterator weapite = this.ontheground.iterator();
        while (weapite.hasNext()) {
            weapon = (Item) weapite.next();
            if (weapon.getNameItem().equals(weapon_s)) {
                this.ontheground.remove(weapon);
                return weapon;
            }
        }
        return null;
    }
    
    public Vector<Item> getChestItem(){
        return this.chest;
    }
    public Vector<Item> getGroundItem(){
        
        Vector<Item> tempItem = new Vector<>();
        Iterator ite = this.ontheground.iterator();
        
        while(ite.hasNext()){
            
            tempItem.add((Item)ite.next());
        }
        return tempItem;
    }
    
    public void opendoor() {
        this.blockedroom = 0;
    }
    
    public void removeAllinChest(){
        this.chest.removeAllElements();
    }
    
    public Boolean IsinChest(String name){
        for(int i=0;i<this.chest.size();i++){
            if(chest.get(i).getNameItem().equals(name)){
                return true;
            }
        }
        return false;
    }
}
