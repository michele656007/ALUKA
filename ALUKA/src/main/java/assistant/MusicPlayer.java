/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistant;

import javax.sound.sampled.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author miche
 */
public class MusicPlayer extends Thread{

    private AudioInputStream as1;
    private Clip clip = null;
    private Line line1 = null;

    public MusicPlayer() {
        
    }
    
    public void playintro(){
        this.start("C:\\Users\\miche\\Documents\\NetBeansProjects\\ALUKA\\src\\music\\intro.wav");
    }
    public void palyboss(){
        this.start("C:\\Users\\miche\\Documents\\NetBeansProjects\\ALUKA\\src\\music\\boss.wav");
    }
    
    public void stopmusic(){
        this.clip.stop();
    }
    
    public void playmusic(){
        this.clip.start();
    }
    
    private void start(String path){
        run(path);
    }
    
    private void run (String path){
        try {
            as1 = AudioSystem.getAudioInputStream(new File(path));
            AudioFormat af = as1.getFormat();
            clip = AudioSystem.getClip();
            DataLine.Info info = new DataLine.Info(Clip.class, af);
            line1 = AudioSystem.getLine(info);
             if ( ! line1.isOpen() )
               {
                clip.open(as1);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
               }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
