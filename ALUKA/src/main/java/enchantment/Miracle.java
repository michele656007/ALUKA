/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enchantment;

/**
 *
 * @author miche
 */
public class Miracle extends Spel{
    
    public Miracle(String name, String effect, String desc, Boolean area, int lv) {
        super(name, effect, desc, area, lv);
    } 
}
/*
 desc = "E' la prima preghiera che padre Antonio ti ha insegnato. Un piccolo inno alla vita che ti scalda il cuore. Cura le tue ferite di 1 d8.";
        write.write_file(new Miracle("calore","1d8",desc,false,1));
        
        desc = "Non tutte le preghiere sono pacifice, questa e' una magia da battaglia che i paladini usano per combattere la non morte e i demoni.Questo miracolo aumenta temporaneamente la tua intelligenza di 4.";
        write.write_file(new Miracle("intelligenza dell'aquila","4sma",desc,false,3));
        
        desc = "Non tutte le preghiere sono pacifice, questa e' una magia da battaglia che i paladini usano per combattere la non morte e i demoni.Questo miracolo aumenta temporaneamente la tua forza di 4.";
        write.write_file(new Miracle("forza del toro","4str",desc,false,3));
        
        desc = "Non tutte le preghiere sono pacifice, questa e' una magia da battaglia che i paladini usano per combattere la non morte e i demoni.Questo miracolo aumenta temporaneamente la tua destrezza di 4.";
        write.write_file(new Miracle("destrezza del gatto","4des",desc,false,3));
        
        desc = "Senti il calore dell'amore degli dei che ti pervade, questo calore cura le tue ferite di 2d12 e calma la tua mente di 7 punti sanita' mentale.";
        write.write_file(new Miracle("fiamma","2d12",desc,false,7));

        desc = "Gli dei amano, gli dei odiano, e in questo caso odiano. Scaglia su un tuo aversario un fulmine scagliato dal cielo causando 4 d10 di danni.";
        write.write_file(new Miracle("deusvult","4d10",desc,false,10));
*/