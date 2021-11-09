/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import I_O_to_file.Writetofile;
import assistant.GuideElement;
import gui.Welcomehall;
import java.util.Random;

/**
 *
 * @author miche
 */
public class Start {

    public static void main(String args[]) {

        Welcomehall welcome = new Welcomehall();
       /* 
        Writetofile<GuideElement> write = new Writetofile<>("guide.dat");
        String desc = null;
        //GuideElement guiel;
        
        desc = "Muoversi: per muoversi c'e' una solo condizione: "
                + "intorno a te non ci devono essere nemici."
                + " Per muoversi effettivamente basta inserire "
                + "i comandi nord,sud,est,ovest.\n"
                + "Combattimenti: per combattere con la sapda "
                + " basta usare il comando attacca e specificare"
                + " il bersaglio se ci sono piu' nemici. Per usare "
                + "magie o miracoli, basta utilizzare il loro nome"
                + "come comando e nel caso speficifare l'obiettivo.";
        //guiel = new GuideElement("gameplay",desc); 
        write.writefile(new GuideElement("gameplay",desc));
        
        desc = "bag: permette di controllare il contenuto della borsa.\n"
                + "levelup: comando da utilizzare quando si sale di livello.\n"
                + "scheda pg: permette di vedere le caratteristiche del personaggio.\n"
                + "esamina: comando da utilizzare per leggere la descrizione di qualcosa, es esamina spada/magia/miracolo.\n"
                + "salva: salva la partita, se la stanza non e' libera l'energia dei nemici vera' riprestinata.\n"
                + "esci: chiude il gioco senza salvare.\n"
                + "carica: permette di tornare al menu.\n"
                + "";
        write.writefile(new GuideElement("command",desc));
       
        desc = "prendi: permette di prendere un oggetto.\n"
                + "lascia: permette di lasciare un oggetto\n"
                + "guarda: permette di guardare la stanza\n"
                + "leggi: permette di leggere un libro.\n"
                + "compra: permette di comprare qualcosa.\n"
                + "vendi:permette di vendere oggetti.\n"
                + "attacca: permette di attaccare un nemico specificato se ci sono piu' nemici, o attaccare un nemico solo.\n"
                + "parla: permette di parlare con le persone.\n"
                + "chiedi: permette di chiedere particolari argomenti ai png.\n"
                + "usa: permette di usare particolari oggetti come pozioni e lanciare incantesimi e miracoli.\n"
                + "equipaggia: permette di equipaggiare armi,scudi e armature.\n"
                + "migliora: permette da chi di dovere di migliorare le armi.\n";
         
        write.writefile(new GuideElement("verb",desc));
        
        write.closefile();
        */
    }

}//end main

