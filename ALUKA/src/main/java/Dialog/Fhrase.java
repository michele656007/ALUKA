/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialog;

import enumeration.Arguments;
import enumeration.Pngname;

/**
 *
 * @author miche
 */
public class Fhrase {
    private String dialog = null;
    private Arguments argument = null;
    
    public Fhrase(String dialog,Arguments argument){
        this.argument = argument;
        this.dialog = dialog;
    }
    public Arguments getArgument(){
        return this.argument;
    }
    public String getDialog(){
        return this.dialog;
    }
    
}
