/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialog;

import enumeration.Specialattack;
import java.io.Serializable;

/**
 *
 * @author miche
 */
//descrizione dell'attacco del nemico
public class Attackdescription implements Serializable{
    private Specialattack spatt = null;
    private String descr = null;
    private Boolean hit = false;
    
    public Attackdescription(Specialattack spatt,String descr,Boolean hit){
        this.descr = descr;
        this.spatt = spatt;
        this.hit = hit;
    }
    
    public String getDescription(){
        return this.descr;
    }
    
    public Specialattack getSpecialattack(){
        return this.spatt;
    }
    
    public Boolean getHit(){
        return this.hit;
    }
}
/*
 Attackdescription att = null;
        String desc = null;
        write.write_file(new Attackdescription(Specialattack.normal,"ti da un colpo.",true));
        desc = "ti da un colpo, ma non ci voleva molto a schivarlo.";
        write.write_file(new Attackdescription(Specialattack.normal,desc,false));
        desc = "ti da uno schiaffo sul petto, non senti tanto il dolore, ma senti freddo, ti senti freddo, come se con quel tocco abbia rubato il tuo calore.";
        write.write_file(new Attackdescription(Specialattack.touch,desc,true));
        desc = "ti da uno schiaffo sul petto,e senti solo l'armatura vibrare un po'.";
        write.write_file(new Attackdescription(Specialattack.touch,desc,false));
        desc = "alza la spada sopra la testa, ti guarda negli occhi e poi cala la spada su di te.";
        write.write_file(new Attackdescription(Specialattack.slash,desc,true));
        desc = "alza la spada sopra la testa, ti guarda negli occhi e poi cala la spada su di te, ma ha aspettato troppo e tu riesci a schivare.";
        write.write_file(new Attackdescription(Specialattack.slash,desc,false));
        desc = "affonda i suoi denti dentro il tuo collo, ti sale un brivido lungo la schiena e senti che ti viene rubato il tuo calore.";
        write.write_file(new Attackdescription(Specialattack.bite,desc,true));
        desc = "Come senti l'aria fredda del suo alito sul tuo collo,fai una capriola davanti a te evitando cosi un morso sul collo";
        write.write_file(new Attackdescription(Specialattack.bite,desc,false));
        desc = "chiudi gli occhi per un secondo, gli riapri e il tuo nemico non c'e' piu', dopo neanche un secondo la schiena ti brucia a causa di un colpo subito.";
        write.write_file(new Attackdescription(Specialattack.sneakattack,desc,true));
        desc = "chiudi gli occhi per un secondo, gli riapri e il tuo nemico non c'e' piu', capisci al volo quello che sta per fare e scatti in avanti.";
        write.write_file(new Attackdescription(Specialattack.sneakattack,desc,false));
        desc = "alza una mano aperta verso di te, il suo palmo emana una luce rossa, e delle ossa sul pavimento si muovono e si sistemano per formare un non morto scheletro.";
        write.write_file(new Attackdescription(Specialattack.createskeleton,desc,true));
        desc = "sulle sue dite compaiono degli artigli neri, con i quali ti lacera.";
        write.write_file(new Attackdescription(Specialattack.clawed,desc,true));
        desc = "sulle sue dite compaiono degli artigli neri,cerca di colpirti ma riesci ad evitare il colpo.";
        write.write_file(new Attackdescription(Specialattack.clawed,desc,false));
        write.close_file();
        
*/