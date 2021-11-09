/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemmanager;

import DBMS.BagLoad;
import DBMS.DBConnection;
import DBMS.MagicLoad;
import DBMS.PgLoad;
import Dialog.Conversation;
import Equipe.Book;
import Equipe.Item;
import Equipe.Key;
import Equipe.Potion;
import assistant.ConversArgu;
import assistant.Elementcatalog;
import assistant.GameAssistant;
import assistant.Merchant;
import character.Pg;
import enchantment.Spel;
import enemy.Enemy;
import enumeration.Specialattack;
import enumeration.Typeitem;
import gui.Gamescreen;
import gui.Welcomehall;
import interfaceclass.InterfaceDBMS;
import interfaceclass.InterfaceParser;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import parser.Parser;
import parser.Word;
import roomsystem.MasterOfRoom;
import roomsystem.Room;

/**
 *
 * @author miche
 */
public class GameManager extends Thread {

    private MasterOfRoom master = null;
    private Gamescreen game = null;
    private GameAssistant gassis = null;
    private Pg pg = null;
    private String command = "empty";
    private Conversation conversation = null;
    private ConversArgu conarg = null;

    private Parser parser = null;
    private int parError = 0;

    private Merchant merchant = null;

    private Vector<Word> tokenp = null;
    //private Vector<Enemy> enemy = null;
    private Vector<String> itemroom = null;
    private Vector<Enemy> combatvet = null;

    private Boolean killThread = false;
    private Boolean stopgame = false; //se il comando non e' valido il gioco non fa niente
    private Boolean cgCommand = false;
    private Boolean lookinachest = false; // falso se si guarda solo la stanza, vero se si cerca in un posto specifico
    private Boolean merchantspeack = false;
    private Boolean endgame = false;

    private InterfaceParser intefpar = null;

    private Specialattack spattack = null;

    private Random rand = null;

    public GameManager(PgLoad rspg) {

        this.buildgame();
        this.buildpg(rspg);
        game = new Gamescreen(this.pg);
        game.printMessage(master.getStorytelling());
        this.itemroom = master.getOntheGround();
        if (this.itemroom.size() > 0) {
            this.game.printMessage("Sistema: per terra noti:");
            for (int i = 0; i < this.itemroom.size(); i++) {
                this.game.printMessage("-" + this.itemroom.get(i));
            }
        }
        this.start();

    }

    public GameManager(Pg pg) {

        this.pg = pg;
        //enemy = new Vector<>();
        game = new Gamescreen(this.pg);
        //parser = new Parser();
        buildgame();
        master = new MasterOfRoom(gassis, intefpar);
        game.printMessage(master.getIntroduction());
        game.printMessage(master.getStorytelling());
        this.start();
    }

    private void buildgame() {
        this.intefpar = (parser = new Parser());
        this.itemroom = new Vector<>();
        this.merchant = new Merchant();
        this.conversation = new Conversation();
        this.conarg = new ConversArgu();
        this.gassis = new GameAssistant();
        this.combatvet = new Vector<>();
        this.rand = new Random();
    }

    @Override
    public void run() {
        do {
            if (!this.killThread) {
                cgCommand = game.canGetCommand();
                if (cgCommand) {
                    
                    command = game.getCommand();
                    this.parError = this.parser.runParsing(command);
                    //System.out.println(this.parError);
                    if (this.master.getIdRoom() == 703 && this.master.getEnemyVector().isEmpty()) {
                        if (!this.endgame) {
                            
                            this.game.printMessage(this.master.getStorytelling());
                            this.game.printMessage("Sistema:Premi invio per tornare al menu.");
                            InterfaceDBMS db = new DBConnection();
                            db.deleteGame(this.pg.getIdpg());
                            endgame = true;
                        } else {
                            this.loadGame();
                        }
                        //inserire procedura di chiusura
                    } else {
                        if (this.parError == 1) {
                            this.tokenp = parser.takeToken();
                            this.stopgame = false;
                        } else {
                            parErrorControl(); //metodo per la cattura e il controllo dell'errore parser
                        }
                        if (this.pg.getHpCurr() <= 0 || this.pg.getSanity() <= 0) {
                            this.stopgame = true;
                            this.game.printMessage("Sistema: sei morto. Puoi ricaricare l'ultimo salvataggio o incominciare una nuova partita.");
                        }
                        if (!this.stopgame) {
                            if (this.combatvet.isEmpty()) { //azioni che possono essere svolte solo fuori dal combattimento
                                if (tokenp.get(0).getTypology().equals("direction")) {
                                    movement(tokenp.get(0).getword());
                                }
                                if (tokenp.get(0).getTypology().equals("miracle")) {
                                    this.castmiracle();
                                }
                                if (tokenp.get(0).getTypology().equals("verb")) {
                                    if (tokenp.get(0).getword().equals("prendi")) {
                                        takeitem();
                                    }
                                    if (tokenp.get(0).getword().equals("lascia")) {
                                        leaveitem();
                                    }
                                    if (tokenp.get(0).getword().equals("guarda")) {
                                        look();
                                    }
                                    if (tokenp.get(0).getword().equals("parla")) {
                                        speak();
                                    }
                                    if (tokenp.get(0).getword().equals("chiedi")) {
                                        ask();
                                    }
                                    if (tokenp.get(0).getword().equals("compra")) {
                                        buy();
                                    }
                                    if (tokenp.get(0).getword().equals("vendi")) {
                                        sell();
                                    }
                                    if (tokenp.get(0).getword().equals("equipaggia")) {
                                        equips();
                                    }
                                    if (tokenp.size() == 2) {
                                        if (tokenp.get(0).getword().equals("usa") && (tokenp.get(1).getSecondTypology().equals("potionhp")
                                                || tokenp.get(1).getSecondTypology().equals("potionsa") || tokenp.get(1).getTypology().equals("miracle")
                                                || tokenp.get(1).getSecondTypology().equals("key"))) {

                                            System.out.println("Sono in usa/verbo");
                                            uses();
                                        }
                                    }
                                    if (tokenp.get(0).getword().equals("leggi")) {
                                        read();
                                    }
                                    if (tokenp.get(0).getword().equals("potenzia")) {
                                        powerUp();
                                    }

                                }
                                if (tokenp.get(0).getTypology().equals("command")) {
                                    if (tokenp.get(0).getword().equals("bag")) {
                                        bag();
                                    }
                                    if (tokenp.get(0).getword().equals("levelup")) {
                                        levelup();
                                    }
                                    if (tokenp.get(0).getword().equals("scheda pg")) {
                                        showfeatures();
                                    }
                                    if (tokenp.get(0).getword().equals("esamina")) {
                                        examine();
                                    }
                                    if (tokenp.get(0).getword().equals("salva")) {
                                        this.save();
                                    }
                                    if (tokenp.get(0).getword().equals("esci")) {
                                        this.game.close();
                                        this.exit();
                                    }
                                    if (tokenp.get(0).getword().equals("carica")) {
                                        this.loadGame();
                                    }

                                }
                                if (tokenp.get(0).getTypology().equals("features")) {
                                    increasefeatures();
                                }
                            } else {
                                if (tokenp.get(0).getword().equals("attacca")) {
                                    this.attack();
                                }
                                if (tokenp.get(0).getTypology().equals("spel")) {
                                    this.spelattack(tokenp.get(0).getword());
                                }
                                if (tokenp.get(0).getTypology().equals("miracle")) {
                                    this.castmiracle();
                                }
                                if (tokenp.get(0).getword().equals("usa")) {
                                    uses();
                                }
                                if (tokenp.get(0).getTypology().equals("command")) {
                                    if (tokenp.get(0).getword().equals("bag")) {
                                        bag();
                                    }
                                    if (tokenp.get(0).getword().equals("salva")) {
                                        this.save();
                                    }
                                    if (tokenp.get(0).getword().equals("esci")) {
                                        this.exit();
                                    }
                                    if (tokenp.get(0).getword().equals("carica")) {
                                        this.loadGame();
                                    }
                                }
                            }

                        } else {
                            if (!tokenp.isEmpty()) {
                                if (tokenp.get(0).getword().equals("carica")) {
                                    this.loadGame();
                                }
                            }
                        }
                    }
                }
                this.tokenp = new Vector<>();
            } else {
                break;
            }
        } while (true);
    }

    private void movement(String direction) {
        int roomError = 0;
        if ((roomError = this.master.movenextRoom(direction)) > 2) {
            this.game.printMessage(this.master.getStorytelling());
            //this.enemy = master.getEnemyVector();
            this.combatvet = new Vector<>();
            this.lookinachest = false;
            this.itemroom = master.getOntheGround();
            if (this.itemroom.size() > 0) {
                this.game.printMessage("Sistema: per terra noti:");
                for (int i = 0; i < this.itemroom.size(); i++) {
                    this.game.printMessage("-" + this.itemroom.get(i));
                }
            }
            if (this.master.getEnemyVector().size() > 0) {
                combatpreparation();
                this.game.printMessage("Sistema:nemici che ti puntano:");
                for (int i = 0; i < this.combatvet.size(); i++) {
                    this.game.printMessage("-" + this.combatvet.get(i).getName());
                }

            }
            if (!master.getPngname().equals("noone")) {
                this.conversation.selectPng(this.conarg.namepngcov(master.getPngname()));
            }
            this.merchantspeack = false;
            this.pg.initializesprovisionalfeatures();

        } else {
            if (roomError == 2) {
                this.game.printMessage("Sistema: non puoi ancora passare da qui.");
            }
            if (roomError == 1) {
                this.game.printMessage("Sistema: non vai da nessuna parte da qui");
            }
        }
    }

    private void examine() {
        if (this.tokenp.size() > 1) {
            for (int i = 1; i < tokenp.size(); i++) {
                if (tokenp.get(i).getTypology().equals("spel")) {
                    this.game.printMessage(this.gassis.createSpel(this.tokenp.get(i).getword()).getDescr());
                }
                if (tokenp.get(i).getTypology().equals("miracle")) {
                    this.game.printMessage(this.gassis.createMiracle(this.tokenp.get(i).getword()).getDescr());
                }
                if (tokenp.get(i).getSecondTypology().equals("weapon")) {
                    this.game.printMessage(this.gassis.createItem(this.parser.getWord(tokenp.get(i).getword())).getWeapon().getDescription());
                }
                if (tokenp.get(i).getSecondTypology().equals("shield")) {
                    this.game.printMessage(this.gassis.createItem(this.parser.getWord(tokenp.get(i).getword())).getShield().getDescription());
                }
                if (tokenp.get(i).getSecondTypology().equals("armor")) {
                    this.game.printMessage(this.gassis.createItem(this.parser.getWord(tokenp.get(i).getword())).getArmor().getDescription());
                }
                if (tokenp.get(i).getSecondTypology().equals("key")) {
                    this.game.printMessage(this.gassis.createItem(this.parser.getWord(tokenp.get(i).getword())).getKey().getDescription());
                }
            }
        } else {
            this.game.printMessage("Sistema: specifica cosa vuoi esaminare e ripeti il comando.");
        }
    }

    private void powerUp() {

        Boolean check = true;

        if (this.merchantspeack) {
            if (this.tokenp.size() == 1) {
                this.game.printMessage("Sistema: ripeti il comando e inserisci quello che vuoi potenziare.");
            }
            if (this.tokenp.size() > 1) {
                for (int j = 1; j < tokenp.size(); j++) {
                    if (tokenp.get(j).getSecondTypology().equals("weapon") || tokenp.get(j).getSecondTypology().equals("shield")
                            || tokenp.get(j).getSecondTypology().equals("armor")) {
                        check = true;
                    } else {
                        check = false;
                    }
                }
                if (check) {
                    for (int i = 1; i < tokenp.size(); i++) {
                        if (this.pg.removeMO(100)) {
                            if (tokenp.get(i).getSecondTypology().equals("weapon")) {
                                if (pg.getWeapon().getName().equals(tokenp.get(i).getword())) {
                                    if (pg.getWeapon().weaponLVup()) {
                                        this.game.printMessage("Gioca:" + pg.getWeapon().getName() + " migliorata");
                                        this.game.printMessage("Gioca: danno aumentato a:" + pg.getWeapon().getDamage());
                                        this.game.reloadTree();
                                    } else {
                                        this.game.printMessage("Gioca:" + pg.getWeapon().getName() + " non puo' essere migliarata di piu'.");
                                    }
                                } else {
                                    this.game.printMessage("Sistema: puoi migliorare solo l'arma che impugni");
                                    this.pg.addMO(100);
                                }
                            }
                            if (tokenp.get(i).getSecondTypology().equals("shield")) {
                                if (pg.getShield().getName().equals(tokenp.get(i).getword())) {
                                    if (pg.getShield().weaponLVup()) {
                                        this.game.printMessage("Gioca:" + pg.getShield().getName() + " migliorata");
                                        this.game.printMessage("Gioca: difesa aumentato a:" + pg.getShield().getDefence());
                                        this.game.reloadTree();
                                    } else {
                                        this.game.printMessage("Gioca:" + pg.getShield().getName() + " non puo' essere migliarata di piu'.");
                                    }
                                } else {
                                    this.game.printMessage("Sistema: puoi migliorare solo lo scudo che impugni");
                                    this.pg.addMO(100);
                                }
                            }
                        } else {
                            this.game.printMessage("Gioco: io non lavoro gratis ragazzo.");
                        }
                    }
                }
            } else {
                this.game.printMessage("Sistema: fai attenzione a quello che scrivi sole le armi posso essere migliorate.");
            }
        }
    }

    private void read() {
        if (tokenp.size() == 2) {
            if (tokenp.get(1).getSecondTypology().equals("book")) {
                Book book = null;
                if ((book = this.pg.leaveitems(tokenp.get(1).getword()).getBook()) != null) {
                    this.game.printMessage("Gioco:" + book.getDescr());
                    this.pg.additemsinBag(new Item(book));
                } else {
                    this.game.printMessage("Gioco: non possiedi il libro che vuoi leggere.");
                }
            }
        }
        if (tokenp.size() == 1) {
            this.game.printMessage("Gioco: leggere fa bene alla mente ma ti serve un libro per farlo.");
        }
    }

    private void uses() {
        if (this.combatvet.size() > 0) {
            if (this.tokenp.get(1).getTypology().equals("spel")) {
                this.tokenp.remove(0);
                this.spelattack(this.tokenp.get(0).getword());
                return;
            }
        }
        if (this.tokenp.get(1).getTypology().equals("miracle")) {
            this.tokenp.remove(0);
            this.castmiracle();
            return;
        }
        if (this.tokenp.get(1).getSecondTypology().equals("potionhp")
                || this.tokenp.get(1).getSecondTypology().equals("potionsa")) {
            this.tokenp.remove(0);
            usePotion();
            return;
        }
        if (this.combatvet.isEmpty()) {
            if (this.tokenp.get(1).getSecondTypology().equals("key")) {
                Key key = this.pg.leaveitems(this.tokenp.get(1).getword()).getKey();
                if (master.openDoor(key)) {
                    this.game.printMessage("Gioco: la chiave aprendo la serratura si spezza, ma ormai la serratura e' aperta.");
                } else {
                    this.game.printMessage("Gioco: questa chiave qui non apre niente");
                    this.pg.additemsinBag(new Item(key));
                }
                return;
            }
        }
        if (tokenp.size() == 1) {
            this.game.printMessage("Sistema: fai attenzione se non ricordi il comando guarda la guida.");
        }
    }

    private void usePotion() {
        //hp3d12
        Potion potion = this.gassis.createItem(this.tokenp.get(0)).getPotion();
        int effect = 0;

        for (int i = 0; i < Integer.parseInt("" + potion.getEffect().charAt(2)); i++) {
            effect = effect + this.rand.nextInt(Integer.parseInt(potion.getEffect().substring(4)) - 1) + 1;
        }
        if (this.tokenp.get(0).getSecondTypology().equals("potionhp")) {
            this.pg.recover(effect);
            this.pg.leaveitems(this.tokenp.get(0).getword());
            this.game.printMessage("Gioco: la pozione ti cura per " + effect + " hp.");
        }
        if (this.tokenp.get(0).getSecondTypology().equals("potionsa")) {
            this.pg.recoverSanity(effect);
            this.pg.leaveitems(this.tokenp.get(0).getword());
            this.game.printMessage("Gioco: la pozione ti calma la mente per " + effect + " sp.");//sanity point
        }
        this.game.reloadTree();
    }

    private void castmiracle() {
        if (this.pg.searchinBook("miracle", this.tokenp.get(0).getword())) {
            if (!this.combatvet.isEmpty()) {

                if (!tokenp.get(0).getword().equals("deusvult")) {
                    if (tokenp.size() == 1) {
                        if (this.pg.miracleEffect(tokenp.get(0).getword(), game) == 0) {
                            this.combatSystem(0, "all");
                        }
                    }
                } else {
                    if (tokenp.get(0).getword().equals("deusvult")) {

                        if (this.combatvet.size() == 1) {
                            int damage = this.pg.miracleEffect(tokenp.get(0).getword(), game);
                            if (damage > -1) {
                                this.combatSystem(damage, this.combatvet.get(0).getName());
                            }
                        } else {
                            if (this.tokenp.get(1).getTypology().equals("enemy")) {
                                int damage = this.pg.miracleEffect(tokenp.get(0).getword(), game);
                                if (damage > -1) {
                                    this.combatSystem(damage, tokenp.get(1).getword());
                                }
                            }
                        }
                    }
                }
            } else {
                if (tokenp.get(0).getword().equals("fiamma") || tokenp.get(0).getword().equals("calore")) {
                    this.pg.miracleEffect(tokenp.get(0).getword(), game);
                    this.game.reloadTree();
                } else {
                    this.game.printMessage("Sistema: non tutti i miracoli possono essere usati fuori dal combattimento.");
                }
            }
        } else {
            this.game.printMessage("Sistema: non conosci questo miracolo.");
        }
    }

    private void spelattack(String spelname) {

        int damage = 0;

        Spel spel = this.gassis.createSpel(spelname);
        if (this.pg.searchinBook("spel", spelname)) {
            damage = this.pg.spellDamage(spel.getEffect());
            this.pg.takedamagesanity(spel.getSpelLv());
            this.game.reloadTree();
            if (spel.getName().equals("tocco del vampiro")) {
                this.pg.recover(damage);
                this.game.printMessage("Gioco: assorbi calore dal nemico. Ti curi di " + damage + " hp.");
            }
            if (tokenp.size() == 1) {
                if (spel.getArea()) {
                    this.combatSystem(damage, "all");
                } else {
                    this.game.printMessage("Sistema: non e' un incantesimo ad area devi specificare il bersaglio.");
                }
            }
            if (tokenp.size() == 2) {
                if (tokenp.get(1).getTypology().equals("enemy")) {
                    if (!spel.getArea()) {
                        this.combatSystem(damage, tokenp.get(1).getword());
                    } else {
                        this.game.printMessage("Sistema: questo e' un incantesimo ad area, non ti serve specifare il bersaglio.");
                        this.combatSystem(damage, "all");
                    }
                } else {
                    this.game.printMessage("Sistema:se hai dificolta' con i comandi leggi la guida non andare a tentativi.");
                }
            }
        } else {
            this.game.printMessage("Sistema: non conosci questo incantesimo.");
        }
    }

    private void showfeatures() {

        Vector<String> featurevet = this.pg.getFeatures();
        this.game.printMessage("Scheda personaggio:");
        this.game.printMessage("Nome:" + pg.getName());
        this.game.printMessage("Livello:" + pg.getLv());
        this.game.printMessage("Forza :" + featurevet.get(0));
        this.game.printMessage("Costituzione :" + featurevet.get(1));
        this.game.printMessage("Destrezza :" + featurevet.get(2));
        this.game.printMessage("Intelligenza :" + featurevet.get(3));
        this.game.printMessage("Fede :" + featurevet.get(4));
        this.game.printMessage("Classe Armatura:" + this.pg.getCA());
        String bab = null;
        bab = "+" + this.pg.getBab().get(0);
        for (int i = 1; i < this.pg.getBab().size(); i++) {
            bab = bab + "/+" + this.pg.getBab().get(i);
        }
        this.game.printMessage("BonusAttaccoBasa(bab) :" + bab);
    }

    private void increasefeatures() {
        if (this.pg.getUpPoint() >= this.tokenp.size()) {
            for (int i = 0; i < tokenp.size(); i++) {
                pg.levelUp(tokenp.get(i).getword());
            }
            this.game.printMessage("Gioco: level up avvenuto.");
            this.game.reloadTree();
        } else {
            this.game.printMessage("Gioco:non hai accumulato abbastanza punti.");
        }
    }

    private void levelup() {

        if (this.pg.getUpPoint() > 0) {
            this.game.printMessage("Gioco:hai accumulato " + pg.getUpPoint() + " punti.Scegli le caratteristiche da aumentare.");
            this.game.printMessage("Forza(str): " + pg.getFeatures().get(0));
            this.game.printMessage("Costituzione(cos): " + pg.getFeatures().get(1));
            this.game.printMessage("Destrezza(des): " + pg.getFeatures().get(2));
            this.game.printMessage("Intelligenza(sma): " + pg.getFeatures().get(3));
            this.game.printMessage("Fede(fed): " + pg.getFeatures().get(4));
            this.game.printMessage("Vedi la guida se non ti ricordi a cosa servono.Attenzione cio' che fai non potra' esse cambiato.");

        } else {
            this.game.printMessage("Gioco: non sei aumentato di livello.");
        }
    }

    private void combatSystem(int damage, String enemyname) {

        Boolean checkcomb = true;
        int enemydam = 0;
        Specialattack spatt = null;
        
        int i = 0;
        do {
            if (checkcomb) {
                if (i < this.combatvet.size()) {
                    
                    if (this.combatvet.get(i).getInitiative() > this.pg.getInitiative()) {
                        
                        spatt = combatvet.get(i).attack(pg.getCA());
                        enemydam = combatvet.get(i).getdamage() - this.pg.calculateShieldprotection();
                        if (enemydam < 0) {
                            enemydam = 0;
                        }
                        if (enemydam > 0) {
                            game.printMessage("Gioco:" + combatvet.get(i).getName() + " " + this.gassis.attackSpecialDescription(spatt, true));
                        } else {
                            game.printMessage("Gioco:" + combatvet.get(i).getName() + " " + this.gassis.attackSpecialDescription(spatt, false));
                        }
                        this.game.printMessage("Gioco:" + combatvet.get(i).getName() + " ti causa " + enemydam + "danni.");
                        this.pg.takedamage(enemydam);
                        this.game.reloadTree();
                        i++;
                    } else {
                        if (enemyname.equals("all")) {
                            if (damage > 0) {
                                this.attackAllEnemy(enemyname, damage);
                            }
                        } else {
                            this.attackOneEnemy(enemyname, damage);
                        }
                        checkcomb = false;
                    }
                } else {
                    if (enemyname.equals("all")) {
                        if (damage > 0) {
                            this.attackAllEnemy(enemyname, damage);
                        }
                        checkcomb = false;
                    } else {
                        
                        this.attackOneEnemy(enemyname, damage);
                        checkcomb = false;
                    }
                }
            } else {
                
                spatt = combatvet.get(i).attack(pg.getCA());
                enemydam = combatvet.get(i).getdamage();
                if (enemydam > 0) {
                    game.printMessage("Gioco:" + combatvet.get(i).getName() + " " + this.gassis.attackSpecialDescription(spatt, true));
                } else {
                    game.printMessage("Gioco:" + combatvet.get(i).getName() + " " + this.gassis.attackSpecialDescription(spatt, false));
                }
                this.game.printMessage("Gioco:" + combatvet.get(i).getName() + " ti causa " + enemydam + "danni.");
                this.pg.takedamage(enemydam);
                this.game.reloadTree();
                i++;
            }
            
        } while (i < this.combatvet.size() || checkcomb);
        
    }

    private void attackAllEnemy(String enemyname, int damage) {
        if (enemyname.equals("all") && damage > 0) {
            for (int n = 0; n < this.combatvet.size(); n++) {
                this.game.printMessage("Gioco:" + this.combatvet.get(n).getName() + " subisce " + damage + " danni.");
                if (!combatvet.get(n).takedamage(damage)) {
                    
                    pg.addExp(combatvet.get(n).getExp());
                    combatvet.remove(n);
                    pg.addMO(200);
                    this.game.reloadTree();
                    if (!this.combatvet.isEmpty()) {
                        this.game.printMessage("Gioco:rimangono da eliminare");
                        for (int k = 0; k < this.combatvet.size(); k++) {
                            this.game.printMessage("-" + combatvet.get(k).getName());
                        }
                    }
                    if (combatvet.isEmpty()) {
                        this.game.printMessage("Gioco: hai eliminato tutti i nemici.");
                        this.master.enemiesEliminated();
                        this.pg.initializesprovisionalfeatures();
                        if (this.pg.getUpPoint() > 0) {
                            this.game.printMessage("Sistema: sei salito di livello. Usa il comando levelup per aumentare le tue statistiche.");
                        }
                    }
                }
            }
        }
    }

    private void attackOneEnemy(String enemyname, int damage) {
        
        for (int j = 0; j < combatvet.size(); j++) {
            if (combatvet.get(j).getName().equals(enemyname)) {

                this.game.printMessage("Gioco:" + this.combatvet.get(j).getName() + " subisce " + damage + " danni.");
                if (!combatvet.get(j).takedamage(damage)) {
                    
                    pg.addExp(combatvet.get(j).getExp());
                    combatvet.remove(j);
                    pg.addMO(200);
                    this.game.reloadTree();
                    if (!this.combatvet.isEmpty()) {
                        this.game.printMessage("Gioco:rimangono da eliminare");
                        for (int k = 0; k < this.combatvet.size(); k++) {
                            this.game.printMessage("-" + combatvet.get(k).getName());
                        }
                    }
                    if (combatvet.isEmpty()) {
                        this.game.printMessage("Gioco: hai eliminato tutti i nemici.");
                        this.master.enemiesEliminated();
                        this.pg.initializesprovisionalfeatures();
                        if (this.pg.getUpPoint() > 0) {
                            this.game.printMessage("Sistema: sei salito di livello. Usa il comando levelup per aumentare le tue statistiche.");
                        }
                    }
                }
            }
        }

    }

    private void attack() {

        String enemyname = null;
        int damage = 0;
        if (this.combatvet.size() >= 1) {
            if ((enemyname = this.gassis.controlCommandAttack(this.tokenp, this.combatvet, this.pg)) != null) {
                
                for (int i = 0; i < this.combatvet.size(); i++) {
                    if (this.combatvet.get(i).getName().equals(enemyname)) {
                        damage = this.pg.weaponDamage(this.combatvet.get(i).getCa());
                        break;
                    }

                }
                combatSystem(damage, enemyname);
            } else {
                this.game.printMessage("Gioco: quel nemico non e' qui.");
            }
        }

    }

    private void combatpreparation() {
        //ordinare vettore per iniziativa
        for (int i = 0; i < this.master.getEnemyVector().size(); i++) {
            this.combatvet.add(master.getEnemyVector().get(i));
        }
        for (int i = 0; i < combatvet.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < combatvet.size(); j++) {
                if (combatvet.get(j).getInitiative() > combatvet.get(index).getInitiative()) {
                    index = j;
                }
                Enemy temp = combatvet.get(index);
                combatvet.set(index, combatvet.get(i));
                combatvet.set(i, temp);
            }
        }
    }

    private void bag() {
        
        if (pg.getBag().size() > 0) {
            this.game.printMessage("Sistema: la tua borsa contiene:");
            for (int i = 0; i < pg.getBag().size(); i++) {
                this.game.printMessage("-" + this.pg.getBag().get(i).getNameItem());
            }
            if (this.pg.getBagFreeSpace() >= 1) {
                this.game.printMessage("Hai ancora posto per " + this.pg.getBagFreeSpace() + " cose.");
            } else {
                this.game.printMessage("Sistema: la borsa e' piena.");
            }
        } else {
            this.game.printMessage("Sistema: la tua borsa e' vuota");
        }
    }

    private void equips() {
        for (int i = 1; i < this.tokenp.size(); i++) {
            if (tokenp.get(i).getSecondTypology().equals("weapon")) {
                this.pg.equipWeapon(this.tokenp.get(i).getword());
            }
            if (tokenp.get(i).getSecondTypology().equals("armor")) {
                this.pg.equipArmor(this.tokenp.get(i).getword());
            }
            if (tokenp.get(i).getSecondTypology().equals("shield")) {
                this.pg.equipeShield(this.tokenp.get(i).getword());
            }
        }
        this.game.reloadTree();
    }

    private void sell() {
        if (this.merchantspeack) {
            if (this.master.getPngname().equals("drogheda")) {
                for (int i = 1; i < this.tokenp.size(); i++) {
                    if (this.merchant.searchinCatalog(this.tokenp.get(i).getword()) > 0) {
                        if (pg.leaveitems(this.tokenp.get(i).getword()) != null) {
                            pg.addMO(this.merchant.searchinCatalog(this.tokenp.get(i).getword()));
                            this.game.reloadTree();
                        }else{
                            this.game.printMessage("Sistema: puoi vendere solo quello che hai in borsa.");
                        }
                    }
                }
            } else {
                this.game.printMessage(this.master.getPngname() + ": mi dispace ma non posso comprare niente. Vai da Drogheda.");
            }
        } else {
            this.game.printMessage("Sistema: puoi vendere solo a Drogheda.");
        }
    }

    private void buy() {
        if (this.merchantspeack) {

            for (int i = 1; i < this.tokenp.size(); i++) {
                Iterator ite = merchant.takeCatalog().iterator();
                while (ite.hasNext()) {
                    Elementcatalog elmcat = (Elementcatalog) ite.next();
                    if (tokenp.get(i).getword().equals(elmcat.getElem())) {
                        if (this.master.getPngname().equals("padre antonio")) {
                            if (pg.removeMO(elmcat.getPrice())) {
                                pg.addMiracle(elmcat.getElem());
                            } else {
                                this.game.printMessage("Padre Antonio: non hai abbastanza soldi.");
                            }
                        }
                        if (this.master.getPngname().equals("azazel")) {
                            if (pg.removeLife(elmcat.getPrice())) {
                                pg.addSpel(elmcat.getElem());
                            } else {
                                this.game.printMessage("Azazel: non hai abbastanza vita, non vorrai morire.");
                            }
                        }
                        if (this.master.getPngname().equals("drogheda")) {
                            if (pg.removeMO(elmcat.getPrice())) {
                                if (!pg.additemsinBag(this.gassis.createItem(this.parser.getWord(elmcat.getElem())))) {
                                    pg.addMO(elmcat.getPrice());
                                    this.game.printMessage("Drogheda: non hai abbastanza spazio nella tua borsa.");
                                } else {
                                    this.game.printMessage("Sistema: acquisto avvenuto.");
                                }
                            } else {
                                this.game.printMessage("Drogheda: non hai abbastanza soldi.");
                            }
                        }
                        if (this.master.getPngname().equals("elsbeth")) {
                            if (pg.removeMO(elmcat.getPrice())) {
                                if (!pg.additemsinBag(this.gassis.createItem(this.parser.getWord(elmcat.getElem())))) {
                                    pg.addMO(elmcat.getPrice());
                                    this.game.printMessage("Elsbeth: non hai abbastanza spazio nella tua borsa.");
                                } else {
                                    this.game.printMessage("Sistema: acquisto avvenuto.");
                                }
                            } else {
                                this.game.printMessage("Elsbeth: non hai abbastanza soldi.");
                            }
                        }
                    }
                }
                game.reloadTree();
            }
        }
    }

    private void ask() {
        if (this.tokenp.size() == 2) {
            this.game.printMessage(this.master.getPngname() + ":" + this.conversation.takeConversation(this.conarg.conversion(tokenp.get(1).getword())));
        }
    }

    private void speak() {

        if (this.tokenp.size() == 1) {
            if (!master.getPngname().equals("noone")) {
                if (this.merchant.searchMerchant(this.master.getIdRoom())) {
                    this.game.printMessage(this.master.getPngname() + ": vuoi comprare qualcosa o chiedermi di qualcosa?");
                    Iterator ite = merchant.takeCatalog().iterator();
                    while (ite.hasNext()) {
                        Elementcatalog elmcat = (Elementcatalog) ite.next();
                        game.printMessage("-" + elmcat.getElem() + " costo:" + elmcat.getPrice());
                    }
                }
                if (this.master.getPngname().equals("gregorio")) {
                    this.game.printMessage(this.master.getPngname() + ": per 100 monete posso migliorare qualcosa o vuoi chiedermi qualcosa?");
                }
                this.merchantspeack = true;
            } else {
                this.game.printMessage("Sistema: non c'e' nessuno qui con cui parlare");
            }
        } else {
            if (this.tokenp.get(1).getTypology().equals("png")) {

                if (master.getPngname().equals(this.tokenp.get(1).getword())) {
                    if (this.merchant.searchMerchant(this.master.getIdRoom())) {
                        this.game.printMessage(this.master.getPngname() + ": per 100 monete posso migliorare qualcosa o vuoi chiedermi qualcosa?");
                        Iterator ite = merchant.takeCatalog().iterator();
                        while (ite.hasNext()) {
                            Elementcatalog elmcat = (Elementcatalog) ite.next();
                            game.printMessage("-" + elmcat.getElem() + " costo:" + elmcat.getPrice());
                        }
                    }
                    if (this.master.getPngname().equals("gregorio")) {
                        this.game.printMessage(this.master.getPngname() + ": vuoi migliorare qualcosa o chiedermi di qualcosa?");
                    }
                    this.merchantspeack = true;
                } else {
                    this.game.printMessage("Sistema: non e' qui.");
                }
            } else {
                game.printMessage(this.conversation.takeConversation(this.conarg.conversion(this.tokenp.get(1).getword())));
            }
        }
    }

    private void look() {
        if (this.tokenp.size() == 1) {
            this.game.printMessage(this.master.getStorytelling());
        } else {
            if (this.tokenp.size() == 2 && this.tokenp.get(1).getSecondTypology().equals("chest")) {
                if (this.master.getChestname() != null) {
                    if (this.master.getChestname().equals(tokenp.get(1).getword())) {
                        this.lookinachest = true;
                        this.itemroom = master.getChest();
                        this.game.printMessage(master.getChestStorytelling());
                        this.game.printMessage("Contiene");
                        for (int i = 0; i < this.itemroom.size(); i++) {
                            this.game.printMessage("-" + this.itemroom.get(i));
                        }
                    }
                } else {
                    this.game.printMessage("Game: non c'e' niente di interessante qui.");
                }
            }
        }
    }

    private void leaveitem() {
        Boolean check = true;
        for (int i = 1; i < this.tokenp.size(); i++) {
            if (!this.tokenp.get(i).getTypology().equals("thing")) {
                check = false;
                break;
            }
        }
        if (check) {
            for (int i = 1; i < this.tokenp.size(); i++) {
                if (!this.lookinachest) {
                    Item item = pg.leaveitems(tokenp.get(i).getword());
                    if (item != null) {
                        this.master.addItemontheGround(item);
                    } else {
                        game.printMessage("Sistema: non puoi lasciare quello che non hai.");
                    }
                } else {
                    Item item = pg.leaveitems(tokenp.get(i).getword());
                    if (item != null) {
                        this.master.addItemintheChest(item);
                    } else {
                        game.printMessage("Sistema: non puoi lasciare quello che non hai.");
                    }
                }
            }
        }
        if (!this.lookinachest) {
            this.itemroom = master.getOntheGround();
            if (this.itemroom.size() > 0) {
                this.game.printMessage("Sistema: per terra noti:");
                for (int i = 0; i < this.itemroom.size(); i++) {
                    this.game.printMessage(this.itemroom.get(i));
                }
            }
        } else {
            //aggiungere metodo per vedere nella chest
        }
    }

    private void takeitem() {

        Boolean check = true;
        for (int i = 1; i < this.tokenp.size(); i++) {
            if (this.tokenp.get(i).getTypology().equals("thing")) {
                check = true;
            } else {
                check = false;
                break;
            }
        }
        if (check) {
            for (int i = 1; i < this.tokenp.size(); i++) {
                if (!this.lookinachest) {
                    Item item = master.takefromOntheGround(tokenp.get(i).getword());
                    if (item != null) {
                        if (!pg.additemsinBag(item)) {
                            this.master.addItemontheGround(item);
                            this.game.printMessage("Sistem: borsa piena, non puoi piu' prendere niente");
                        }
                    } else {
                        this.game.printMessage("Sistema: qui non c'e' quello che cerchi");
                    }
                } else {
                    Item item = master.takefromChest(tokenp.get(i).getword());
                    if (item != null) {
                        if (!pg.additemsinBag(item)) {
                            this.master.addItemintheChest(item);
                            this.game.printMessage("Sistem: borsa piena, non puoi piu' prendere niente");
                        }
                    } else {
                        this.game.printMessage("Sistema: guarda bene.");
                    }
                }
            }
        } else {
            this.game.printMessage("Sistema: sicuro di voler prendere proprio quello?");
        }
    }

    private void parErrorControl() {

        this.stopgame = true;
        if (parError == -3 || parError == -1) {
            game.printMessage("Errore: attento a quello che scrivi.");
        }
        if (parError == 0) {
            game.printMessage("Errore: comando vuoto.");
        }
    }

    private void buildpg(PgLoad rspg) {

        //Pg(int idpg,String name,int str,int des,int cos,int sma,int fed,int lv,int hpcurr,int hptot, int currSanity,int mo)
        this.pg = new Pg(rspg);

        InterfaceDBMS db = new DBConnection();
        Vector<BagLoad> rsbag = db.LoadBag(pg.getIdpg());

        if (!rsbag.isEmpty()) {
            for (int i = 0; i < rsbag.size(); i++) {
                Item item = this.gassis.createItem(this.parser.getWord(rsbag.get(i).getName()));
                if (rsbag.get(i).getLv() > 1) {
                    for (int j = 1; j < rsbag.get(i).getLv(); j++) {
                        if (item.getType() == Typeitem.weapon) {
                            item.getWeapon().weaponLVup();
                        }
                        if (item.getType() == Typeitem.shield) {
                            item.getShield().weaponLVup();
                        }
                    }
                }
                if (rsbag.get(i).getHolds()) {
                    this.pg.setEquip(item);
                } else {
                    this.pg.additemsinBag(item);
                }
            }
        }
        Vector<MagicLoad> rsmagic = db.LoadMagic(this.pg.getIdpg());
        if (!rsmagic.isEmpty()) {
            for (int i = 0; i < rsmagic.size(); i++) {
                if (rsmagic.get(i).getIsSpel()) {
                    pg.addSpel(rsmagic.get(i).getName());
                } else {
                    pg.addMiracle(rsmagic.get(i).getName());
                }
            }
        }
        db.close();
        master = new MasterOfRoom(gassis, intefpar, pg.getIdpg(), rspg.getCurrRoom());

    }

    private void save() {
        InterfaceDBMS db = new DBConnection();
        Vector<Room> roomvet = new Vector<>();

        Iterator ite = this.master.getRoomList().iterator();
        while (ite.hasNext()) {
            Room room = (Room) ite.next();
            roomvet.add((Room) room.clone());
        }
        db.saveGame(pg, roomvet, this.master.getIdRoom());
        db.close();

        this.game.printMessage("Sistema: partita salvata.");
    }

    private void loadGame() {
        this.killThread = true;
        this.game.close();
        Welcomehall welHall = new Welcomehall();
    }

    private void exit() {
        this.killThread = true;
    }
}
