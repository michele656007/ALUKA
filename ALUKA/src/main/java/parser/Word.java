/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Vector;

/**
 *
 * @author miche
 */
public class Word {
    
    private String word = null;
    private Vector<String> typology = null;
    private Vector<String> alias = null;
    
    public Word(String word,String typology){
        this.word = word;
        this.typology = new Vector<>();
        this.typology.add(typology);
        alias = new Vector<String>();
    }
    
    public void addSecontTypology(String secondt){
        this.typology.add(secondt);
    }
    
    public void addAlias(String alias){
        this.alias.add(alias);
    }
    
    public boolean checkAlias(String alias){
        
        for(int i=0;i<this.alias.size();i++){
            if(this.alias.get(i).equals(alias))
                return true;
        }
        
        return false;
    }
    
    
    public boolean equals(String word){
        String temp = word.trim();
        return this.word.equals(temp);
    }
    
    public String getTypology(){
        return this.typology.get(0);
    }
    
    public String getword(){
        return this.word;
    }
    
    public String getSecondTypology(){
        if(this.typology.size()==2)
            return this.typology.get(1);
        else
            return "noone";
    }
    public Vector<String> getAlias(){
        return this.alias;
    }
    
}
