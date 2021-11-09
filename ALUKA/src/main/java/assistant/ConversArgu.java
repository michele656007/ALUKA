/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistant;

import enumeration.Arguments;
import enumeration.Pngname;

/**
 *
 * @author miche
 */
//classe per la conversione di parole in argomenti di discussione
public class ConversArgu {

    public ConversArgu() {

    }

    public Arguments conversion(String word) {
        if (word.equals("armatura di pelle") || word.equals("armatura di ferro") || word.equals("armatura di acciaio")) {
            return Arguments.armor;
        }
        if (word.equals("magia del sangue")) {
            return Arguments.magicblood;
        }
        if (word.equals("miracolo")) {
            return Arguments.miracle;
        }
        if (word.equals("omicidio")) {
            return Arguments.murder;
        }
        if (word.equals("vampiro") || word.equals("vampiri")) {
            return Arguments.vampire;
        }
        if (word.equals("dei") || word.equals("dio")) {
            return Arguments.gods;
        }
        if (word.equals("scudo di legno") || word.equals("scudo di ferro") || word.equals("scudo di acciaio")) {
            return Arguments.shield;
        }
        if (word.equals("spada corta") || word.equals("stella del mattino") || word.equals("spada lunga")
                || word.equals("martello da guerra") || word.equals("spadone")) {
            return Arguments.weapon;
        }
        return null;
    }

    public Pngname namepngcov(String pngname) {
        if (pngname.equals("gregorio")) {
            return Pngname.blacksmith;
        }
        if (pngname.equals("padre antonio")) {
            return Pngname.priest;
        }
        if (pngname.equals("elsbeth")) {
            return Pngname.alchemist;
        }
        if (pngname.equals("drogheda")) {
            return Pngname.gunsmith;
        }
        if (pngname.equals("azazel")) {
            return Pngname.evil;
        }
        return null;
    }

}
