/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package character;

import DBMS.PgLoad;
import Equipe.*;
import enchantment.Spel;
import enumeration.Hand;
import enumeration.Typeitem;
import gui.Gamescreen;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author miche
 */
public class Pg {

    private int idpg;
    private String name = null;
    //caratteristiche di gioco su cui si andrà a calcolare il modificatore della caratteristica
    private int str = 0;//forza
    private int des = 0;//destrezza
    private int cos = 0;//costituzione
    private int sma = 0;//intelligenza
    private int fed = 0;//fede
    private int sanity = 0;
    private int sanitytot = 0;
    private int hpcurr = 0;//punti vita correnti
    private int hptot = 0;
    private Vector<String> bab = null;
    private int Lv = 1;
    private int expcurr = 0;
    private int nextlv = 100;
    private int mo = 0;
    private Boolean handr = false;
    private Boolean handl = false;
    private Hand hand = null;
    //caratteristiche temporanee
    private int tempSTR = 0;
    private int tempDES = 0;
    private int tempSMA = 0;
    private int upPoint = 0;

    //equipaggiamento e strumenti
    private Weapon weapon = null;
    private Armor armor = null;
    private Shield shield = null;
    private Vector<Item> bag = null;
    private int bagsize = 0;
    private Typeitem typeitem = null;

    //capacità magiche e miracolose
    private Vector<String> bookofspel = null;
    private Vector<String> bookofmiracle = null;

    private Random random = null;

    public Pg(PgLoad pgl) {
        this.idpg = pgl.getIdpg();
        this.name = pgl.getName();
        this.str = pgl.getStr();
        this.des = pgl.getDes();
        this.cos = pgl.getCos();
        this.sma = pgl.getSma();
        this.fed = pgl.getFed();
        this.mo = pgl.getMo();
        this.Lv = pgl.getLv();
        this.sanitytot = 10 + (this.Lv / 2) + this.getCos() - (this.getSma() / 2);
        this.sanity = pgl.getSanCurr();
        this.hptot = pgl.getHpTot();
        this.hpcurr = pgl.getHpCurr();
        this.bagsize = this.cos - 10;
        this.expcurr = pgl.getExp();
        this.nextlv = pgl.getNextLv();

        this.bab = new Vector<>();
        this.bab.add("1");

        for (int j = 1; j < this.Lv; j++) {
            if (j % 4 == 0) {
                this.bab.add("1");
            }
            for (int i = 0; i < bab.size(); i++) {
                int temp = Integer.parseInt(bab.get(i));
                temp = temp + 1;
                bab.set(i, "" + temp);
            }
        }
        this.bookofspel = new Vector<>();
        this.bookofmiracle = new Vector<>();
        this.bag = new Vector<>();
        random = new Random();
    }

    public Pg(String name, int str, int des, int cos, int sma, int fed, int lv, Armor armor, Weapon weapon, int hpcurr, int hptot, Vector<String> bab) {
        this.name = name;
        this.Lv = lv;
        this.cos = cos;
        this.des = des;
        this.fed = fed;
        this.sma = sma;
        this.str = str;

        this.armor = armor;
        this.weapon = weapon;
        if (this.weapon.getHand() == this.hand.RIGHT) {
            this.handr = true;
        }
        if (this.weapon.getHand() == this.hand.TWOHANDS) {
            this.handr = true;
            this.handl = true;
        }
        this.bagsize = this.cos - 10;
        this.hpcurr = hpcurr;
        this.hptot = hptot;
        this.bab = bab;
        this.bag = new Vector<>();
        this.bookofspel = new Vector<>();
        this.bookofmiracle = new Vector<>();
        if (this.Lv == 1) {
            this.bab = new Vector<>();
            this.bab.add("1");
        }
        this.sanitytot = 10 + (this.Lv / 2) + this.getCos() - (this.getSma() / 2);
        this.sanity = sanitytot;
        random = new Random();

        this.tempSMA = this.sma;
        this.tempDES = this.des;
        this.tempSTR = this.str;
    }

    public void setIdpg(int id) {
        this.idpg = id;
    }

    public int getStrstat() {
        return this.str;
    }

    public int getDesstat() {
        return this.des;
    }

    public int getCosstat() {
        return this.cos;
    }

    public int getSmastat() {
        return this.sma;
    }

    public int getFedstat() {
        return this.fed;
    }

    public int getIdpg() {
        return this.idpg;
    }

    //metodo per ottenere l'arma
    public Weapon getWeapon() {
        return this.weapon;
    }

    //metodo per ottenere l'armatura
    public Armor getArmor() {
        return this.armor;
    }

    //m per ottenere lo scudo
    public Shield getShield() {
        return this.shield;
    }

    //m per ottenere il quantitativo di monete d'oro portate
    public int getMo() {
        return this.mo;
    }

    //m per ottenere i max hp del personaggio 
    public int getHptot() {
        return this.hptot;
    }

    //m per ottenere gli hp correnti
    public int getHpCurr() {
        return this.hpcurr;
    }

    public int getUpPoint() {
        return this.upPoint;
    }

    public void setUpPoint(int upPoint) {
        this.upPoint = upPoint;
    }

    //m per settare exp e nextlv
    public void setExpandNextlv(int exp, int nextlv) {
        this.expcurr = exp;
        this.nextlv = nextlv;
    }

    //m per settare la lista degli incantesimi
    public void setBookofspel(Vector<String> book) {
        this.bookofspel = book;
    }

    //m per settare la lista dei miracoli
    public void setBookofmiracle(Vector<String> book) {
        this.bookofmiracle = book;
    }

    //m per aggiungere un nuovo incantesimo
    public Boolean addSpel(String spel) {
        if (this.bookofspel.indexOf(spel) == -1) {
            this.bookofspel.add(spel);
            return true;
        } else {
            return false;
        }
    }

    //m per aggiungere un nuovo miracolo
    public Boolean addMiracle(String miracle) {
        if (this.bookofmiracle.indexOf(miracle) == -1) {
            this.bookofmiracle.add(miracle);
            return true;
        } else {
            return false;
        }
    }

    //m per ottenere il nome del pg
    public String getName() {
        return this.name;
    }

    //m per ottenere le caratteristiche del pg
    public int getCA() {
        return 10 + ((this.tempDES - 10) / 2) + this.armor.getCA();
    }

    public int getStr() {
        return (this.tempSTR - 10) / 2;
    }

    public int getDes() {
        return (this.tempDES - 10) / 2;
    }

    public int getCos() {
        return (cos - 10) / 2;
    }

    public int getSma() {
        return (this.tempSMA - 10) / 2;
    }

    public int getFed() {
        return (fed - 10) / 2;
    }

    public int getInitiative() {
        return (10 + this.getDes());
    }

    //resettare caratteristiche temporanee
    public void initializesprovisionalfeatures() {
        this.tempDES = this.des;
        this.tempSMA = this.sma;
        this.tempSTR = this.str;
    }

    //-----------------------------------------
    //metodo per ottenere il lv del pg
    public int getLv() {
        return this.Lv;
    }

    public int getExp() {
        return this.expcurr;
    }

    public int getNextLv() {
        return this.nextlv;
    }

    public int getSanityTot() {
        return sanitytot;
    }

    public int getSanity() {
        return this.sanity;
    }

    public String weapondamage() {
        return this.weapon.getDamage();
    }

    public Vector<String> getBab() {
        return bab;
    }

    public int getBagFreeSpace() {
        return this.bagsize - this.bag.size();
    }

    public Boolean addExp(int exp) {

        Boolean check = false;
        this.expcurr = this.expcurr + exp;
        do {
            if (this.expcurr > this.nextlv) {
                nextlv = nextlv + nextlv / 2;
                this.upPoint = upPoint + 1;
                check = true;
            }
        } while (this.expcurr > this.nextlv);
        return check;
    }

    public Vector<String> getFeatures() {
        Vector<String> features = new Vector<>();

        features.add("" + this.str);
        features.add("" + this.cos);
        features.add("" + this.des);
        features.add("" + this.sma);
        features.add("" + this.fed);

        return features;
    }

    public void levelUp(String carat) {

        int hpPlus = 0;
        if (carat.equals("str")) {
            str = str + 1;
        }
        if (carat.equals("des")) {
            des = des + 1;
        }
        if (carat.equals("cos")) {
            cos = cos + 1;
            this.bagsize = cos - 10;
        }
        if (carat.equals("sma")) {
            sma = sma + 1;
        }
        if (carat.equals("fed")) {
            fed = fed + 1;
        }
        for (int i = 0; i < bab.size(); i++) {
            int temp = Integer.parseInt(bab.get(i));
            temp = temp + 1;
            bab.set(i, "" + temp);
        }
        if (this.Lv % 4 == 0) {
            this.bab.add("1");
        }
        this.Lv = Lv + 1;
        if (str > sma) {
            hpPlus = random.nextInt(9) + 1 + this.getCos();
            this.hptot = this.hptot + hpPlus;
            this.hpcurr = this.hpcurr + hpPlus;
        }
        if (sma > str) {
            hpPlus = random.nextInt(5) + 1 + this.getCos();
            this.hptot = this.hptot + hpPlus;
            this.hpcurr = this.hpcurr + hpPlus;
        }
        if (str == sma) {
            hpPlus = random.nextInt(7) + 1 + this.getCos();
            this.hptot = this.hptot + hpPlus;
            this.hpcurr = this.hpcurr + hpPlus;
        }
        this.sanitytot = 10 + (this.Lv / 2) + this.getCos() + this.getSma() - (this.getFed() / 2);

        this.upPoint = this.upPoint - 1;
    }

    public Vector<Item> getBag() {
        return this.bag;
    }

    public void setEquip(Item item) {
        if (item.getType() == Typeitem.weapon) {
            this.weapon = item.getWeapon();
        }
        if (item.getType() == Typeitem.shield) {
            this.shield = item.getShield();
        }
        if (item.getType() == Typeitem.armor) {
            this.armor = item.getArmor();
        }
    }

    public Boolean equipArmor(String name) {
        System.out.println("sono in equipeArmor2");
        for (int i = 0; i < this.bag.size(); i++) {
            System.out.println(bag.get(i).getNameItem());
            if (bag.get(i).getType() == this.typeitem.armor) {
                System.out.println("sono qui 33");
                if (bag.get(i).getArmor().getName().equals(name)) {
                    Item temp = new Item(this.armor);
                    this.armor = bag.get(i).getArmor();
                    bag.set(i, temp);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean equipWeapon(String name) {
        for (int i = 0; i < this.bag.size(); i++) {
            if (bag.get(i).getType() == Typeitem.weapon) {
                if (bag.get(i).getWeapon().getName().equals(name)) {
                    if (bag.get(i).getWeapon().getHand() == Hand.RIGHT) {
                        Item temp = new Item(this.weapon);
                        this.weapon = bag.get(i).getWeapon();
                        bag.set(i, temp);
                        this.handr = true;
                        if (bag.get(i).getWeapon().getHand() == Hand.TWOHANDS) {
                            this.handl = false;
                        }
                        return true;
                    }
                    if (bag.get(i).getWeapon().getHand() == Hand.TWOHANDS) {
                        if (!this.handl) {
                            Item temp = new Item(this.weapon);
                            this.weapon = bag.get(i).getWeapon();
                            bag.set(i, temp);
                            this.handr = true;
                            this.handl = true;
                            return true;
                        } else {
                            if (this.bag.size() == this.bagsize - 1) {
                                Item temp = new Item(this.weapon);
                                this.weapon = bag.get(i).getWeapon();
                                bag.set(i, temp);
                                if (this.shield != null) {
                                    temp = new Item(this.shield);
                                    this.bag.add(temp);
                                    this.shield = null;
                                    this.handl = false;
                                }
                                this.handr = true;
                                this.handl = true;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Boolean equipeShield(String name) {
        for (int i = 0; i < this.bag.size(); i++) {
            if (bag.get(i).getType() == Typeitem.shield) {
                if (bag.get(i).getShield().getName().equals(name)) {
                    if (bag.get(i).getShield().getHand() == Hand.LEFT) {
                        if (this.handl && (this.weapon.getHand() == Hand.RIGHT)) {
                            Item temp = new Item(this.shield);
                            this.shield = bag.get(i).getShield();
                            bag.set(i, temp);
                            return true;
                        } else {
                            if (this.weapon.getHand() == Hand.RIGHT) {
                                this.shield = bag.get(i).getShield();
                                bag.remove(i);
                                this.handl = true;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Boolean additemsinBag(Item item) {
        if (bag.size() < this.bagsize) {
            bag.add(item);
            return true;
        } else {
            return false;
        }
    }

    public Item leaveitems(String itemname) {
        Item temp = null;
        for (int i = 0; i < bag.size(); i++) {
            if (itemname.equals(bag.get(i).getNameItem())) {
                temp = bag.get(i);
                bag.remove(i);
                break;
            }
        }

        return temp;

    }

    public Boolean takedamage(int dam) {

        int damage = dam;
        if (damage > 0) {
            this.hpcurr = this.hpcurr - damage;
            if (this.hpcurr > 0) {
                return true;
            }
            return false;
        }
        return true;
    }

    public Boolean takedamagesanity(int damage) {
        this.sanity = this.sanity - damage;
        return this.sanity > 0;
    }

    public void addMO(int mo) {
        this.mo = this.mo + mo;
    }

    public Boolean removeMO(int mo) {
        if ((this.mo - mo) >= 0) {
            this.mo = this.mo - mo;
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeLife(int hp) {
        if ((this.hptot - hp) > 0) {
            this.hptot = this.hptot - hp;
            if ((this.hpcurr - hp) > 0) {
                this.hpcurr = this.hpcurr - hp;
            } else {
                this.hpcurr = 1;
            }
            return true;
        }
        return false;
    }

    public int weaponDamage(int enemyca) {

        int damage = 0;

        for (int i = 0; i < this.bab.size(); i++) {
            if ((Integer.parseInt(bab.get(i) + this.random.nextInt(19) + 1)) > enemyca) {
                damage = damage + calculateweapondamage();
            }
        }

        return damage;
    }

    private int calculateweapondamage() {
        String dams = this.weapon.getDamage();

        int dam = 0;
        int numdice = Integer.parseInt(dams.substring(0, 1));
        //System.out.println("num dice" + numdice);
        int dice = Integer.parseInt(dams.substring(2));
        //System.out.println("dice" + dice);
        for (int i = 1; i <= numdice; i++) {
            dam = dam + random.nextInt(dice - 1) + 1;
            //System.out.println(dam);
        }

        return dam + this.getStr();
    }

    public int calculateShieldprotection() {

        if (this.shield != null) {
            String dams = this.weapon.getDamage();
            Random rand = new Random();
            if (rand.nextInt(100) > 50) {
                int dam = 0;
                int numdice = Integer.parseInt(dams.substring(0, 1));
                //System.out.println("num dice" + numdice);
                int dice = Integer.parseInt(dams.substring(2));
                //System.out.println("dice" + dice);
                for (int i = 1; i <= numdice; i++) {
                    dam = dam + random.nextInt(dice - 1) + 1;
                    //System.out.println(dam);
                }

                return dam;
            }
        }
        return 0;

    }

    public void recover(int rec) {
        this.hpcurr = this.hpcurr + rec;
        if (hpcurr > hptot) {
            hpcurr = hptot;
        }
    }

    public void recoverSanity(int rec) {
        this.sanity = sanity + rec;
        if (sanity > sanitytot) {
            sanity = sanitytot;
        }
    }

    public int miracleEffect(String name, Gamescreen game) {
        if (name.equals("deusvult")) {
            return this.magicEffect(10, 4) + this.getFed() * 2;
        }
        if (name.equals("calore")) {
            int rec = (this.magicEffect(8, 1) + this.getFed());
            recover(rec);
            game.printMessage("Gioco: senti il tepore del sole primaverile dentro di te."
                    + " Ti curi per " + rec + " punti vita.");
            return 0;
        }
        if (name.equals("fiamma")) {
            int rec = this.magicEffect(12, 2) + this.getFed();
            recover(rec);
            this.recoverSanity(7);
            game.printMessage("Gioco: senti il calore del sole estivo dentro di te."
                    + " Ti curi per " + rec + " punti vita e 7 punti salute mentale.");
            return 0;
        }
        if (name.equals("intelligenza dell'aquila")) {
            this.tempSMA = this.tempSMA + 4;
            game.printMessage("Gioco: la tua intelligenza aumenta di 4.");
            return 0;
        }
        if (name.equals("forza del toro")) {
            this.tempSTR = this.tempSTR + 4;
            game.printMessage("Gioco: la tua forza aumenta di 4.");
            return 0;
        }
        if (name.equals("destrezza del gatto")) {
            this.tempDES = this.tempDES + 4;
            game.printMessage("Gioco: la tua destrezza aumenta di 4.");
            return 0;
        }

        return -1;
    }

    public int spellDamage(String speleffect) {

        int damage = 0;

        String dices = speleffect.substring(0, speleffect.indexOf("%"));
        String lvspel = speleffect.substring(speleffect.indexOf("%") + 1);
        int dice = 0;
        int ndice = 0;
        //1d6%2m10
        if (lvspel.charAt(1) == 'm') {
            //System.out.println(dices + " " + lvspel);
            if ((ndice = this.getLv() / Integer.parseInt("" + lvspel.charAt(0))) < 1) {
                ndice = 1;
            }
            dice = Integer.parseInt("" + dices.substring(2));
            System.out.println(ndice);
            if (ndice > Integer.parseInt(lvspel.substring(2))) {
                ndice = Integer.parseInt(lvspel.substring(2));
            }
            //System.out.println(ndice);
            //System.out.println(dices.charAt(2));

            //System.out.println("inizio for");
            for (int i = 0; i < ndice; i++) {
                //System.out.println("rand :"+rand);
                damage = this.magicEffect(dice, ndice);
                //System.out.println(damage);
            }
            //System.out.println(damage);
        }
        if (lvspel.charAt(1) == 'r') {
            //4d6%4r3
            System.out.println(dices + " " + lvspel);
            System.out.println(Integer.parseInt("" + lvspel.charAt(0)));
            int rolldice = this.getLv() / Integer.parseInt("" + lvspel.charAt(0));
            if (rolldice > Integer.parseInt("" + lvspel.charAt(2))) {
                rolldice = Integer.parseInt("" + lvspel.charAt(2));
            }
            dice = Integer.parseInt("" + dices.substring(2));
            ndice = Integer.parseInt("" + dices.charAt(0));
            System.out.println("rolldice:" + rolldice);
            System.out.println("dice:" + dice);
            System.out.println("ndice:" + ndice);
            for (int i = 0; i < rolldice; i++) {
                for (int j = 0; j < ndice; j++) {
                    damage = magicEffect(dice, ndice);
                    System.out.println(damage);
                }
            }
            //System.out.println(damage);
        }
        return damage + this.getSma();
    }

    public Vector<String> getBookofSpel() {
        return this.bookofspel;
    }

    public Vector<String> getBookofMiracle() {
        return this.bookofmiracle;
    }

    public Boolean searchinBook(String type, String name) {
        if (type.equals("spel") && (!this.bookofspel.isEmpty())) {
            for (int i = 0; i < this.bookofspel.size(); i++) {
                if (this.bookofspel.get(i).equals(name)) {
                    return true;
                }
            }
        }
        if (type.equals("miracle") && (!this.bookofmiracle.isEmpty())) {
            for (int i = 0; i < this.bookofmiracle.size(); i++) {
                if (this.bookofmiracle.get(i).equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int magicEffect(int dice, int numdice) {
        int damage = 0;
        for (int i = 0; i < numdice; i++) {
            damage = damage + random.nextInt(dice - 1) + 1;
        }

        return damage;
    }
}
