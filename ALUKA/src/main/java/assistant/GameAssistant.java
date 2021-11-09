/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistant;

import Dialog.Attackdescription;
import Equipe.Armor;
import Equipe.Book;
import Equipe.Item;
import Equipe.Key;
import Equipe.Potion;
import Equipe.Shield;
import Equipe.Weapon;
import I_O_to_file.Readtofile;
import character.Pg;
import enchantment.Miracle;
import enchantment.Spel;
import enemy.Enemy;
import enumeration.Specialattack;
import java.util.Vector;
import parser.Word;

/**
 *
 * @author miche
 */
public class GameAssistant {

    public GameAssistant() {
    }

    public Item createItem(Word word) {
        Item item = null;
        if (word.getSecondTypology().equals("armor")) {
            return createArmor(word.getword());
        }
        if (word.getSecondTypology().equals("weapon")) {
            return createWeapon(word.getword());
        }
        if (word.getSecondTypology().equals("shield")) {
            return createShield(word.getword());
        }
        if(word.getSecondTypology().equals("key")){
            return createKey(word.getword());
        }
        if(word.getSecondTypology().equals("book")){
            return createBook(word.getword());
        }
        if ((item = createPotion(word.getword())) != null) {
            return item;
        }
        return null;
    }
    
    private Item createBook(String name){
        Readtofile<Book> read = new Readtofile<>("book.dat");
        Book book = null;
        do{
            book = read.readtofile();
            if(book!=null){
                if(book.getName().equals(name)){
                    return new Item(book);
                }
            }
        }while(book!=null);
        return null;
    }
    
    private Item createKey(String name) {
        Readtofile<Key> read = new Readtofile<>("key.dat");
        Key key = null;
        do {
            key = read.readtofile();
            if (key != null) {
                if (key.getName().equals(name)) {
                    return new Item(key);
                }
            }
        } while (key != null);

        return null;
    }
    
    private Item createPotion(String name) {
        Readtofile<Potion> read = new Readtofile<>("potion.dat");
        Potion potion = null;
        do {
            potion = read.readtofile();
            if (potion != null) {
                if (potion.getName().equals(name)) {
                    return new Item(potion);
                }
            }
        } while (potion != null);

        return null;
    }
    
    private Item createArmor(String name) {
        Readtofile<Armor> read = new Readtofile<>("armor.dat");
        Armor armor = null;
        do {
            armor = read.readtofile();
            if (armor != null) {
                if (armor.getName().equals(name)) {
                    return new Item(armor);
                }
            }
        } while (armor != null);

        return null;
    }

    private Item createWeapon(String name) {
        Readtofile<Weapon> read = new Readtofile<>("weapon.dat");
        Weapon weapon = null;
        do {
            weapon = read.readtofile();
            if (weapon != null) {
                if (weapon.getName().equals(name)) {
                    return new Item(weapon);
                }
            }
        } while (weapon != null);

        return null;
    }

    private Item createShield(String name) {
        Readtofile<Shield> read = new Readtofile<>("shield.dat");
        Shield shield = null;
        do {
            shield = read.readtofile();
            if (shield != null) {
                if (shield.getName().equals(name)) {
                    return new Item(shield);
                }
            }
        } while (shield != null);

        return null;
    }

    public Spel createSpel(String name) {
        Readtofile<Spel> read = new Readtofile<>("spel.dat");
        Spel spel = null;
        do {
            spel = read.readtofile();
            if (spel.getName().equals(name)) {
                return spel;
            }
        } while (spel != null);

        return null;
    }

    public Miracle createMiracle(String name) {
        Readtofile<Miracle> read = new Readtofile<>("miracle.dat");
        Miracle miracle = null;
        do {
            miracle = read.readtofile();
            if (miracle != null) {
                if (miracle.getName().equals(name)) {
                    return miracle;
                }
            }
        } while (miracle != null);

        return null;
    }

    public String attackSpecialDescription(Specialattack spatt, Boolean hit) {

        Attackdescription attdesc = null;
        Readtofile<Attackdescription> read = new Readtofile<>("attackdesc.dat");

        do {
            attdesc = read.readtofile();
            if (attdesc != null) {
                if (attdesc.getSpecialattack() == spatt) {
                    if (hit && attdesc.getHit()) {
                        return attdesc.getDescription();
                    } else {
                        if (!hit && !attdesc.getHit()) {
                            return attdesc.getDescription();
                        }
                    }
                }
            }
        } while (attdesc != null);

        return null;
    }

    public String controlCommandAttack(Vector<Word> tokenp, Vector<Enemy> combatvet, Pg pg) {
        if (combatvet.size() == 1) {
            //caso verbo
            if (tokenp.size() == 1) {
                return combatvet.get(0).getName();
            }
            //caso verbo nemico/arma
            if (tokenp.size() == 2) {
                //caso verbo nemico
                if (tokenp.get(1).getTypology().equals("enemy")) {
                    if (tokenp.get(1).getword().equals(combatvet.get(0).getName())) {
                        return combatvet.get(0).getName();
                    } else {
                        return null;
                    }
                }
                //caso verbo arma
                if (tokenp.get(1).getSecondTypology().equals("weapon")) {
                    if (pg.getWeapon().getName().equals(tokenp.get(1).getword())) {
                        return combatvet.get(0).getName();
                    } else {
                        return null;
                    }
                }
            }
            if (tokenp.size() == 3) {
                //case verbo nemico arma
                if (tokenp.get(1).getTypology().equals("enemy")) {
                    if (tokenp.get(1).equals(combatvet.get(0).getName())) {
                        if (tokenp.get(2).equals(pg.getWeapon().getName())) {
                            return combatvet.get(0).getName();
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
                //caso verbo arma nemico
                if (tokenp.get(1).getSecondTypology().equals(pg.getWeapon().getName())) {
                    if (tokenp.get(2).equals(combatvet.get(0).getName())) {
                        return combatvet.get(0).getName();
                    } else {
                        return null;
                    }
                }
            }

        }//fine caso nemico unico
        //caso con piu' nemici
        if (combatvet.size() > 1) {
            String enemyname = null;
            for (int i = 1; i < tokenp.size(); i++) {
                if (tokenp.get(i).getTypology().equals("enemy")) {
                    enemyname = tokenp.get(i).getword();
                }
            }
            if (enemyname != null) {
                if (tokenp.size() == 3) {
                    for (int i = 1; i < tokenp.size(); i++) {
                        if (tokenp.get(i).getSecondTypology().equals("weapon")) {
                            if (pg.getWeapon().equals(tokenp.get(i).getword())) {
                                return enemyname;
                            }
                        }
                    }
                } else {
                    return enemyname;
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
