/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import enumeration.Roomevent;
import java.util.Vector;

/**
 *
 * @author miche
 */
public class Storytelling {
    private Vector<Roomevent> event = null;
    private String story = null;
    
    public Storytelling(String story,Vector<Roomevent> event){
        this.event = event;
        this.story = story;
    }
    
    public Vector<Roomevent> getRoomevent(){
        return this.event;
    }
    public String getStorytelling(){
        return story;
    }
}
