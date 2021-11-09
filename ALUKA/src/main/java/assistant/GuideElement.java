/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistant;

import java.io.Serializable;

/**
 *
 * @author miche
 */
public class GuideElement implements Serializable{
    
    private String guidename = null;
    private String desc = null;
    
    public GuideElement(String guidename,String desc){
        this.guidename = guidename;
        this.desc = desc;
    }
    
    public String getGuideName(){
        return this.guidename;
    }
    
    public String getDesc(){
        return this.desc;
    }
    
}
/*

        desc = "Muoversi: per muoversi c'e' una solo condizione: "
                + "intorno a te non ci devono essere nemici."
                + " Per muoversi effettivamente basta inserire "
                + "i comandi nord,sud,est,ovest.";
        guiel = new GuideElement("gameplay",desc);
        write.write_file(guiel);
        desc = "Combattimenti: per combattere con la sapda "
                + " basta usare il comando attacca e specificare"
                + " il bersaglio se ci sono piu' nemici. Per usare "
                + "magie o miracoli, basta utilizzare il loro nome"
                + "come comando e nel caso speficifare l'obiettivo.";
        guiel = new GuideElement("gameplay",desc); 
        write.write_file(guiel);
        guiel = new GuideElement("command","bag: permette di controllare"
                + " il contenuto della borsa."); 
        write.write_file(guiel);
        guiel = new GuideElement("command","levelup: comando da utilizzare"
                + " quando si sale di livello."); 
        write.write_file(guiel);
        guiel = new GuideElement("command","scheda pg: permette di vedere"
                + " le caratteristiche del personaggio."); 
        write.write_file(guiel);
        guiel = new GuideElement("command","esamina: comando da utilizzare"
                + " per leggere la descrizione di qualcosa, es esamina spada/magia/miracolo."); 
        write.write_file(guiel);
        guiel = new GuideElement("command","salva: salva la partita, se"
                + " la stanza non e' libera l'energia dei nemici vera' riprestinata." ); 
        write.write_file(guiel);        
        guiel = new GuideElement("command","esci: chiude il gioco senza salvare."); 
        write.write_file(guiel);
        guiel = new GuideElement("command","carica: permette di tornare al menu"
                + " principale per caricare una partita."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","prendi: permette di prendere un oggetto."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","lascia: permette di lasciare un oggetto"
                + " che hai in borsa."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","guarda: permette di guardare la stanza"
                + " e se accompagnato con un elemento della stanza permettono di"
                + " guardarci all'interno."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","leggi: permette di leggere un libro."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","compra: permette di comprare qualcosa."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","vendi:permette di vendere oggetti."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","attacca: permette di attaccare un nemico"
                + " specificato se ci sono piu' nemici, o attaccare un nemico"
                + " solo."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","parla: permette di parlare"
                + " con le persone."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","chiedi: permette di chiedere particolari"
                + " argomenti ai png."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","usa: permette di usare particolari"
                + " oggetti come pozioni e lanciare incantesimi e miracoli."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","equipaggia: permette di equipaggiare armi,scudi"
                + " e armature."); 
        write.write_file(guiel);
        guiel = new GuideElement("verb","migliora: permette da chi di dovere"
                + " di migliorare le armi."); 
        write.write_file(guiel);
        
        write.close_file();
*/