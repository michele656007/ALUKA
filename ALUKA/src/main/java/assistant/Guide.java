/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistant;

/**
 *
 * @author miche
 */
public class Guide {
    
    private String guide = "Pulsa Danura e' un gioco creato sulla falsa riga di D&D. Il giocatore si trovera' ad affrontare mostri e percoli."
            + "Le caratteristiche del personaggio sono dati dai modificatori delle caratteristiche, ovvero non viene utilizzato"
            + " il valore della caratteristica ma uno dipendente da esso, ovvero (stat-10)/2. Cio' permette di avere un gioco piu'"
            + " equilibrato e divertente. Le caratteristiche prime del personaggio sono 5: forza(str),destrezza(des),fede(fed)"
            + " intelligenza(sma),costituzione(cos)."
            + " La stat. str influenza la forza con la quale colpisci i nemici."
            + " La stat. des definisce la tua capacita' di evitare i colpi."
            + " La stat. fed definisce quanta fede hai negli dei."
            + " La stat. sma definisce il grado del tuo intelletto."
            + " La stat. cos definisce la tua salute, e quinti quanti danni puoi prendere."
            + " Giocando a facile le caratteristiche sono molto alte, giocando a difficile le caratteristiche saranno molto basse."
            + " La modalità normale e' una via di mezzo.";
    
    public Guide(){}
    
    public String getGuide(){
        return this.guide;
    }
}
