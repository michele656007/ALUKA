/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package I_O_to_file;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 *
 * @author miche
 */
public class Readtofile<T> {

    File file = null;
    FileInputStream infile;
    ObjectInputStream inStream;
    T object = null;

    public Readtofile(String name_file) {
        try {
            file = new File(name_file);
            if (file.exists()) {
                
                infile = new FileInputStream(file);
                inStream = new ObjectInputStream(infile);
            } else {
                System.out.println("il file non esiste\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T readtofile(){
        object = null;
        try {
            object = (T) inStream.readObject();
        } catch (EOFException e) {
            //e.printStackTrace();
            return null;
        }catch(IOException io){
            //io.printStackTrace();
        }catch(ClassNotFoundException cl){
            //cl.printStackTrace();
        }
        
        return object;
        
    }

    public void closefile() {
        try {
            this.inStream.close();
        } catch (IOException ex) {
            //Logger.getLogger(Readtofile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.infile.close();
        } catch (IOException ex1) {
            //Logger.getLogger(Readtofile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
