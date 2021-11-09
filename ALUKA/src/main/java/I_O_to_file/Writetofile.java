/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I_O_to_file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miche
 */
public class Writetofile<T> {
    
    File file = null;
    FileOutputStream outfile;
    ObjectOutputStream outStream;
    
    public Writetofile (String name_file){
        try{
            file = new File(name_file);
            if(file.exists()){
                System.out.println("il file "+file+" esiste");
                //true serve per scrivere in append
                outfile = new FileOutputStream(file,true);
                outStream = new ObjectOutputStream(outfile);
            }
            else if(file.createNewFile()){
                System.out.println("Il file é stato creato");
                outfile = new FileOutputStream(file);
                outStream = new ObjectOutputStream(outfile);
            }
            else
                System.out.println("Il file non può essere creato");     
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void writefile(T object){
        try {
            this.outStream.writeObject(object);
            this.outStream.flush();
            // this.outStream.close();
        } catch (IOException ex) {
            //Logger.getLogger(Writetofile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closefile() {
        try {
            this.outStream.close();
            this.outfile.close();
        } catch (IOException ex) {
            Logger.getLogger(Writetofile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
