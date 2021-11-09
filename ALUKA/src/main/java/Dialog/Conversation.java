/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialog;

import enumeration.Arguments;
import enumeration.Pngname;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author miche
 */
public class Conversation {

    private ArrayList<Fhrase> fhrase = null;
    
    public Conversation() {
        fhrase = new ArrayList<>();
        
    }

    public void selectPng(Pngname pngname) {
        if (Pngname.blacksmith == pngname) {
            blacksmithfhrase();
        }
        if (Pngname.alchemist == pngname) {
            alchemistfhrase();
        }
        if (Pngname.evil == pngname) {
            evilfhrase();
        }
        if (Pngname.priest == pngname) {
            priestfhrase();
        }
        if (Pngname.gunsmith == pngname) {
            gunsmithfhrase();
        }
    }
    
    public String takeConversation(Arguments argum){
        Iterator ite = this.fhrase.iterator();
        while(ite.hasNext()){
            Fhrase fhra = (Fhrase)ite.next();
            if(fhra.getArgument() == argum)
                return fhra.getDialog();
        }
        
        return "Gioco :Mi dispiace ma non so dirti molto di questo.";
    }
    
    private void blacksmithfhrase() {
        this.fhrase = new ArrayList<>();

        this.fhrase.add(new Fhrase("Non dirlo a me, mio cognato Arkus e' sparito, e lui era un cazzo di orco. Qualsiasi cosa"
                + " sia che uccide,se non viene fermata tra non molto dovro' andare via.", Arguments.murder));
        this.fhrase.add(new Fhrase("Se vuoi per delle monete vedo se riesco a migliorarlo quello scudo.", Arguments.shield));
        this.fhrase.add(new Fhrase("Se vuoi per delle monete vedo se riesco a migliorarlo quella spada.", Arguments.weapon));
        this.fhrase.add(new Fhrase("Mi dispiace ma io forgio solo armi e scudi.", Arguments.armor));
        this.fhrase.add(new Fhrase("Ovvio che ne ho sentito parlare, sono andato anche io a scuola sai, ma a parte quello che abbiamo studiato non so dirti altro.", Arguments.vampire));
        this.fhrase.add(new Fhrase("So solo una cosa: con una buona spada li apri in due come una zucca.", Arguments.zombie));
    }
    
    private void alchemistfhrase(){
        this.fhrase = new ArrayList<>();
        this.fhrase.add(new Fhrase("Lo sai fratellino che non mi interessa.", Arguments.shield));
        this.fhrase.add(new Fhrase("Sono inutili quando studi magia fratello mio.", Arguments.weapon));
        this.fhrase.add(new Fhrase("Sono inutili quando studi magia fratello mio.", Arguments.armor));
        this.fhrase.add(new Fhrase("Hai sentito ? Sono spariti altri due bambini, tu sei l'ultima guardia che rimane e da solo non puoi proteggere tutti. Ormai mi rimani solo tu, non morire.", Arguments.murder));
        this.fhrase.add(new Fhrase("Vampiri, vampiri da quello che ho studiato i vampiri sono non morti creati attraverso un rito molto particolare. Ma il rito e' molto complicato e"
                + " richiede tante vite ed e' molto probabile che alla fine fallisca uccidento anche l'esecutore.", Arguments.vampire));
        this.fhrase.add(new Fhrase("Gli zombie sono cadaveri reanimati da magia necromantica. Sono stupidi e fanno solo quello che gli ordina il loro creatore. Non sentolo dolore, e combattono non curandosi del proprio corpo."
                + " Si pensa che l'unica cosa che sentano e' la fame di carne.", Arguments.zombie));
        this.fhrase.add(new Fhrase("Si dice che qualche maestro stregone diventato vampiro abbia trasformato la magia in magia del sangue. Un tipo di magia maledetta che sfrutta il sangue che i vampiri bevono "
                + "dalle loro vittime. Se usata da un essere vivente hanno uno strano effetto.", Arguments.magicblood));    
    }
    
    private void evilfhrase(){
        this.fhrase = new ArrayList<>();
        this.fhrase.add(new Fhrase("Qualche beota ha messo in giro la voce che la magia del sangue derivi dalla piccola,debole e pallida magia di questo mondo,"
                + " ma in realta' e' un dono che un grande Signore dei demoni ha fatto per quei pochi esseri inferiori che hanno mosso il"
                + " primo passo verso la vera grandezza.", Arguments.magicblood)); 
        this.fhrase.add(new Fhrase("Non sono altro che bugie di demoni che mentoni a loro stessi.", Arguments.miracle)); 
        this.fhrase.add(new Fhrase("Si lo so caro mio piccolo amico mortale, ma non sono omicidi, sono sacrifici che qualche piccolo essere insignificante come te"
                + " ha eseguito per avicinarsi alla grandezza di noi demoni.",Arguments.murder));
        this.fhrase.add(new Fhrase("Un essere inutile che ha mosso i primi passi per diventare qualcosa di piu' potente.",Arguments.vampire));
        this.fhrase.add(new Fhrase("Non sono altro che pupazzi affamati.",Arguments.zombie));
    }
    
    private void priestfhrase(){
         this.fhrase = new ArrayList<>();
        this.fhrase.add(new Fhrase("La magia del sangue e' la magia utilizzata da folli che hanno scelto di aggirare la morte."
                + "E' un tipo di magia proibita e macabra che trova la sua forza consumando il suo utilizzatore. Qualche folle crede che"
                + " questo tipo di magia sia un dono benevolo da parte di un signore dei demoni, ma i demoni non sono generosi, non sono leali"
                + " e soprattuto non sono benevoli verso i mortali. L'unica cosa che gli interessa e di portare piu' anime mortali nei loro reami per poterle divorare.", Arguments.magicblood)); 
        this.fhrase.add(new Fhrase("I miracoli sono particolari pregiere che volgiamo agli dei. Pronunciando queste antiche preghiere possiamo attingere dal loro amore un grande potere che puo' salvare la vita"
                + " nostra o di qualcuno che vogliamo salvare", Arguments.miracle)); 
        this.fhrase.add(new Fhrase("Non credo che questi omicidi siano casuali, ho studiato durante il mio corso per diventare chierico che esistono antichi rituali che sfruttano un consistente numero di amine"
                + " per poter fermare il proprio tempo vitale, e quindi diventare immortali.",Arguments.murder));
        this.fhrase.add(new Fhrase("I vampiri sono creature folli portatrici di un'orribile maledizione, essi ingannano la morte fermando il loro tempo vitale e diventando non morti,esseri contemporaneamente sia vivi che morti"
                + " un vero insulto per gli dei. Possono nascere o attraverso un rituale immondo o quando un vampiro igneta il proprio veleno in un essere senziente. Non pochi cacciatori di vampiri"
                + " si sono fatti ucidere dai loro compagni paladini perche' trasformati da vampiri che si sono voluti fare beffa della loro morale.",Arguments.vampire));
        this.fhrase.add(new Fhrase("Gli dei che noi preghiamo sono sei, ed ognuno di essi ha dato origine a una delle razze senzienti che oggi vivoni in questo mondo. Un tempo queste sei razze si combattevano l'un altra"
                + " perche' ogni razza credeva che il proprio dio fosse l'unico vero dio e ibridi come te, tua sorella e Droghera, quella simpatica mezzorca che ti piace erano considerati abominii e uccisi alla nascita. Ma per grazia"
                + " degli dei essi scesero dai loro troni qui in questo piano materiale, e ogni dio parlo' al proprio popolo sgridandoli per quello che stavano facendo e ordinando loro di rispettare ogni vita che nasce, indipendentemente dalla razza.",Arguments.gods));
    }
    
    private void  gunsmithfhrase(){
        this.fhrase = new ArrayList<>();
        this.fhrase.add(new Fhrase("Le ormature servono a proteggere il tuo corpo. Ti permetto di poter evitare qualche colpo.",Arguments.armor));
        this.fhrase.add(new Fhrase("Gli scudi ti permettono di ridurre il danno di un colpo che non riesci ad evitare e di solito si tengono con la mano sinistra.",Arguments.shield));
        this.fhrase.add(new Fhrase("Servono per combattere e fare male al tuo nemico.",Arguments.weapon));
        this.fhrase.add(new Fhrase("Ho sentito di tante persone scomparse in questi ultimi mesi, ache mio zio Arkus e' sparito. Fai attenzione la fuori.",Arguments.murder));

    }
}
