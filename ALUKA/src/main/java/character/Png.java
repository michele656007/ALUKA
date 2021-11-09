/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author miche
 */
public class Png {
    private String name = null;
    private int idroom = 0;
    private Vector<String> namefile = null;
    
    public Png(String name,int idroom,Vector<String> namefile){
        this.name = name;
        this.idroom = idroom;
        this.namefile = namefile;
    }
}
