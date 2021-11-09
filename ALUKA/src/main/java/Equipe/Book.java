/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Equipe;

import java.io.Serializable;

/**
 *
 * @author miche
 */
public class Book implements Serializable{
    private String name = null;
    private String descr = null;
    
    public Book(String name,String descr){
        this.name = name;
        this.descr = descr;
    }
    
    public String getName(){
        return name;
    }
    public String getDescr(){
        return descr;
    }
}
/*
 Writetofile<Book> write = new Writetofile<>("book.dat");
        
        Book book = new Book("diario blu","Un piccolo diario blue, probabilmente del bambino che dormiva in questa cameretta."
                + " Il diario racconta di come la famiglia passasse molto tempo tutti insieme, e di come il bambino fosse"
                + " contento che il padre, sempre chiuso nel suo studio a lavorare, trovasse tutti i giorni del tempo per"
                + " giocare ai cavaglieri con lui. Due pagine sono vuote. Poi riprende. Da quanto scritto dal bambino la"
                + " sorella Lindsey e' satta infettatta dal morbillo di drago, (tu ti ricordi che un'epidemia di questo"
                + " tipo non capita da 100 anni). A quanto pare l'unica che si potesse prendere cura della piccola era"
                + " la balia Biri perche' aveva un antenato dragoide e quindi per lei era un semplice morbillo, che"
                + " aveva preso già da piccolina. Lindsey sta peggiorando. E il diario finisce qui."
                + " ");
        write.write_file(book);
        book = new Book("libro nero","Questo libro porta sulla copertina lo stemma della famiglia che abita questa magione."
                + " Sembra un diario dei capi della familia che si sono succeduti. Il libro racconta di come questa famiglia"
                + " sia diventata ricca dando la caccia alle creature della notte, umani dalla pelle albina che si nutrivano"
                + " del sangue delle loro vittime e di come ne abbiano catturato uno vivo, diverso dagli altri, molto antico"
                + " e bestiale . Dalle loro ricerce risulta che questo essere e' pobabilmente uno dei primi non morti del"
                + " tipo vampirico e che dal suo velenoso sangue nero forse e' possibile creare un'antidodo per coloro che sono"
                + " stati trasformati in vampiri. Il diario continua dicendo che nussuno ha piu' ritrovato quell'essere, fino"
                + " a quando l'ultimo padrene di casa scopre una stanza segreta propio sotto la sua casa, in cui legato ha trovato"
                + " ancora viva la creatura. Da allora riprese con gli studi, fino a quando la figlia morì per il morbillo di drago."
                + " Da qui l'obbiettivo delle sue ricerce e' cambiato. Il diario si riempie di simboli strani, disegni con accanto rune"
                + " che sembrano spiegare il funzionamento di quello che hai capito essere cerchi magici. Il diario ormai incomprensibile"
                + " finisce.");
        write.write_file(book);
*/