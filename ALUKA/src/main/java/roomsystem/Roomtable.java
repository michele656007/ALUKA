/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomsystem;

/**
 *
 * @author miche
 */
public class Roomtable {

    private int curr = 0; //id della stanza dove si trova il giocatore
    private int next = 0;
    private int[] room = {100, 101, 102, 103, 104, 105, 200, 201, 202, 203, 301, 302, 303, 500, 501, 502, 503, 504, 505,
        600, 601, 602, 603, 604, 605, 606, 700, 701, 702, 703};
    private String[] cardlpoint = {"nord", "sud", "est", "ovest"};
    private int[][] nextroom = {
        {102, 200, 103, 101},//100 piazza
        {0, 0, 100, 0},//101 fabbro
        {0, 100, 0, 0},//102 tempio
        {104, 0, 105, 100},//103 mercato
        {0, 103, 0, 0},//104 armaiolo
        {0, 0, 0, 103},// 105 alchimista
        {100, 500, 202, 201},//200maniero ingresso   
        {0, 0, 200, 0,},//201giardino
        {0, 502, 203, 200},//202 cimitero
        {0, 0, 301, 202},//203 cripta esterno
        {0, 302, 0, 203},//301 cripta 1° piano
        {301, 303, 0, 0},//302 cripta 2° piano
        {302, 0, 0, 0}, //303 cripta 3° piano
        {200, 501, 0, 0},//500 ingresso
        {500, 0, 502, 503},//501 sala da pranzo
        {505, 504, 202, 501},//502 cucina
        {600, 501, 0, 0},//503 scale 2°piano
        {502, 700, 0, 0},//504 sgabuzino  
        {0, 502, 0, 0},//505dormitorio servitu  
        {503, 604, 603, 0},//600scale 1°scale
        {0, 603, 0, 0},//601 cameretta bimbo
        {0, 603, 0, 0},//602 cameretta bimba
        {601, 605, 602, 600},//603 corridoio
        {600, 606, 0, 0},//604 studio
        {603, 0, 0, 0},//605 camera da letto patronale
        {604, 0, 0, 0},//606 soffitta
        {504, 0, 0, 701},//700 dispensa
        {0, 702, 0, 700},//701 dispensa vini 
        {701, 703, 0, 0},//702 boss room
        {702, 0, 0, 0}//703 boos segreto
    };

    public Roomtable() {
        this.curr = 100;
    }

    public Roomtable(int idroom) {
        this.curr = idroom;
    }

    //metodo per la ricerca della stanza sucessiva
    public int nextRoom(String cardlpoint) {
        int cardlpoint_i = 0;
        int room_i = 0;

        for (int i = 0; i < this.cardlpoint.length; i++) {
            if (this.cardlpoint[i].equals(cardlpoint)) {
                cardlpoint_i = i;
                break;
            }
        }
        for (int j = 0; j < this.room.length; j++) {
            if (this.room[j] == this.curr) {
                room_i = j;
                break;
            }
        }
        
        try {
            this.next = this.nextroom[room_i][cardlpoint_i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.next = 0;
        }
        return this.next;
    }

    //metodo per cambiare effettivamente stanza
    public void shiftroom() {
        this.curr = this.next;
        this.next = 0;
    }
}
