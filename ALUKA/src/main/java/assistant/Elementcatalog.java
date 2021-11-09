/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistant;

/**
 *
 * @author miche
 */
public class Elementcatalog {
    
    private String elem = null;
    private int price = 0;
    
    public Elementcatalog(String elem,int price){
        this.elem = elem;
        this.price = price;
    }
    
    public String getElem(){
        return this.elem;
    }
    
    public int getPrice(){
        return this.price;
    }
}
