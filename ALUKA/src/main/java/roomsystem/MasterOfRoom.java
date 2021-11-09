/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomsystem;

//Creare sistema di codice per comprendere lo stato del sotto-sistema
//Codice idroom e' avvenuto lo spostamento nell'altra room
//Codice 1 la la stanza non ha quella direzione
//Codice 2 quella porta e' ancora bloccata
import DBMS.DBConnection;
import DBMS.RoomChestLoad;
import DBMS.RoomLoad;
import Equipe.Item;
import Equipe.Key;
import assistant.GameAssistant;
import enemy.Enemy;
import enemy.Graveknight;
import enemy.Keepercrypts;
import enemy.Nosferatu;
import enemy.Progenyvampiric;
import enemy.Skeleton;
import enemy.Vampire;
import enemy.Zombie;
import enumeration.Roomevent;
import enumeration.Typeitem;
import interfaceclass.InterfaceDBMS;
import interfaceclass.InterfaceParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import script.Script;
import script.Storytelling;

/**
 *
 * @author miche
 */
//Classe per la gestione delle stanze e delle descrizioni delle stanze
public class MasterOfRoom {

    private ArrayList<Room> rooms = null;
    private Room curr = null;
    private Roomtable table = null;
    private Script script = null;
    private Storytelling story = null;
    private Vector<Enemy> enemy = null;
    private GameAssistant gassist = null;
    private InterfaceParser intfpar = null;

    public MasterOfRoom(GameAssistant gassist, InterfaceParser intfpar) {
        rooms = new ArrayList<>();
        this.table = new Roomtable();
        script = new Script();
        Storytelling temp;
        Iterator ite = script.getScript().iterator();
        while (ite.hasNext()) {
            temp = (Storytelling) ite.next();
            for (int i = 0; i < temp.getRoomevent().size(); i++) {
                if (temp.getRoomevent().get(i) == Roomevent.start) {
                    story = temp;
                }
            }
        }
        this.gassist = gassist;
        this.intfpar = intfpar;
        this.curr = createRoom(100);
    }

    public MasterOfRoom(GameAssistant gassist, InterfaceParser intfpar, int idpg, int currRoom) {
        rooms = new ArrayList<>();
        script = new Script();
        this.gassist = gassist;
        this.intfpar = intfpar;
        this.buildroom(idpg, currRoom);
        this.table = new Roomtable(curr.getIdroom());
    }

    //public Room(int id_room, Vector<Roomevent> event,int blockedroom,Png png) {
    //metodo per la creazione delle stanze non visitate
    private Room createRoom(int id) {
        Room room = null;
        switch (id) {
            case 100:
                enemy = new Vector<>();
                room = new Room(100, Roomevent.noone, enemy, 0, "noone");
                break;
            case 101:

                enemy = new Vector<>();
                room = new Room(101, Roomevent.noone, enemy, 0, "gregorio");
                break;
            case 102:

                enemy = new Vector<>();
                room = new Room(102, Roomevent.noone, enemy, 0, "padre antonio");
                break;
            case 103:

                enemy = new Vector<>();
                room = new Room(103, Roomevent.noone, enemy, 0, "noone");
                break;
            case 104:

                enemy = new Vector<>();
                room = new Room(104, Roomevent.noone, enemy, 0, "drogheda");
                break;
            case 105:

                enemy = new Vector<>();
                room = new Room(105, Roomevent.noone, enemy, 0, "elsbeth");
                break;
            case 200:

                enemy = new Vector<>();
                room = new Room(200, Roomevent.closedor, enemy, 500, "noone");
                break;
            case 201:

                enemy = new Vector<>();
                enemy.add(new Zombie("giardiniere1"));
                enemy.add(new Zombie("giardiniere2"));
                room = new Room(201, Roomevent.noone, enemy, 0, "noone");
                break;
            case 202:

                enemy = new Vector<>();
                enemy.add(new Skeleton("scheletro"));
                room = new Room(202, Roomevent.noone, enemy, 0, "noone");
                break;
            case 203:

                enemy = new Vector<>();
                room = new Room(203, Roomevent.closedor, enemy, 301, "noone");
                break;
            case 301:

                enemy = new Vector<>();
                enemy.add(new Keepercrypts("guardiano"));
                room = new Room(301, Roomevent.noone, enemy, 0, "noone");
                break;
            case 302:

                enemy = new Vector<>();
                enemy.add(new Keepercrypts("guardiano"));
                enemy.add(new Skeleton("scheletro"));
                room = new Room(302, Roomevent.noone, enemy, 0, "noone");
                break;
            case 303:

                enemy = new Vector<>();
                enemy.add(new Graveknight("cavagliere tombale"));
                room = new Room(303, Roomevent.noone, enemy, 0, "noone");
                room.setChestname("sarcofago");
                room.addChest(this.gassist.createItem(this.intfpar.getWord("chiave grigia")));
                break;
            case 500:

                enemy = new Vector<>();
                enemy.add(new Progenyvampiric("magiordomo"));
                room = new Room(500, Roomevent.closedor, enemy, 200, "noone");
                room.setChestname("riquadro");
                room.addChest(this.gassist.createItem(this.intfpar.getWord("chiave bianca")));
                break;
            case 501:

                enemy = new Vector<>();
                enemy.add(new Progenyvampiric("cameriera"));
                enemy.add(new Zombie("cameriera2"));
                room = new Room(501, Roomevent.noone, enemy, 0, "noone");
                break;
            case 502:

                enemy = new Vector<>();
                room = new Room(502, Roomevent.noone, enemy, 0, "azazel");
                break;
            case 503:

                enemy = new Vector<>();
                room = new Room(503, Roomevent.noone, enemy, 0, "noone");
                break;
            case 504:

                enemy = new Vector<>();
                room = new Room(504, Roomevent.closedor, enemy, 700, "noone");
                break;
            case 505:

                enemy = new Vector<>();
                enemy.add(new Progenyvampiric("cuoco"));
                enemy.add(new Progenyvampiric("cuoca"));
                room = new Room(505, Roomevent.noone, enemy, 0, "noone");
                break;
            case 600:

                enemy = new Vector<>();
                room = new Room(600, Roomevent.noone, enemy, 0, "noone");
                break;
            case 601:

                enemy = new Vector<>();
                room = new Room(601, Roomevent.noone, enemy, 0, "noone");
                room.setChestname("cassapanca");
                room.addChest(this.gassist.createItem(this.intfpar.getWord("diario blu")));
                break;
            case 602:

                enemy = new Vector<>();
                enemy.add(new Progenyvampiric("balia"));
                room = new Room(602, Roomevent.noone, enemy, 0, "noone");
                break;
            case 603:

                enemy = new Vector<>();
                room = new Room(603, Roomevent.noone, enemy, 0, "noone");
                break;
            case 604:

                enemy = new Vector<>();
                room = new Room(604, Roomevent.noone, enemy, 0, "noone");
                room.setChestname("scrivania");
                room.addChest(this.gassist.createItem(this.intfpar.getWord("libro nero")));
                room.addChest(this.gassist.createItem(this.intfpar.getWord("chiave nera")));
                break;
            case 605:

                enemy = new Vector<>();
                enemy.add(new Progenyvampiric("cameriera"));
                enemy.add(new Progenyvampiric("baronessa"));
                room = new Room(605, Roomevent.noone, enemy, 0, "noone");
                room.setChestname("armadio");
                room.addChest(this.gassist.createItem(this.intfpar.getWord("armatura di acciaio")));
                break;
            case 606:

                enemy = new Vector<>();
                room = new Room(606, Roomevent.noone, enemy, 0, "noone");
                room.setChestname("baule");
                room.addChest(this.gassist.createItem(this.intfpar.getWord("scudo di acciaio")));
                room.addChest(this.gassist.createItem(this.intfpar.getWord("spadone")));
                break;
            case 700:

                enemy = new Vector<>();
                room = new Room(700, Roomevent.noone, enemy, 0, "noone");
                break;
            case 701:

                enemy = new Vector<>();
                room = new Room(701, Roomevent.noone, enemy, 0, "noone");
                break;
            case 702:

                enemy = new Vector<>();
                enemy.add(new Vampire("celter"));
                room = new Room(702, Roomevent.closedor, enemy, 703, "noone");
                room.setChestname("trono");
                room.addChest(this.gassist.createItem(this.intfpar.getWord("chiave vecchia")));
                break;
            case 703:

                enemy = new Vector<>();
                enemy.add(new Nosferatu("antico"));
                room = new Room(703, Roomevent.noone, enemy, 0, "noone");
                break;
        }
        this.rooms.add(room);
        return room;
    }

    //m. per ottenere il nome del png della stanza
    public String getPngname() {
        return this.curr.getPngname();
    }

    //m. per ottenere i nemici nella stanza
    public Vector<Enemy> getEnemyVector() {
        return this.curr.getEnemies();
    }

    //m. per controllare se e' possibile cambiare stanza
    public int movenextRoom(String direction) {
        int nextroom = this.table.nextRoom(direction);
        if (nextroom != 0) {
            if (this.curr.getBlockedDoor() != nextroom) {
                this.curr = this.searchRoom(nextroom);
                this.table.shiftroom();
                return this.curr.getIdroom();
            } else {
                return 2;
            }
        }
        return 1;
    }

    //m per ottenere l'id della stanza
    public int getIdRoom() {
        return this.curr.getIdroom();
    }

    //m. per aquisire l'introduzione al gioco
    public String getIntroduction() {
        return this.script.getRoomScript(Roomevent.start, Roomevent.afight);
    }

    //m per ottenere la descrizione della stanza
    public String getStorytelling() {
        Roomevent eventl = this.curr.getEvent();
        this.script.loadRoomScript(this.curr.getIdroom());
        if (this.curr.getEnemies().size() > 0) {
            return this.script.getRoomScript(eventl, Roomevent.bfight);
        }

        return this.script.getRoomScript(eventl, Roomevent.afight);
    }

    // m per ottenere la descrizione della chest
    public String getChestStorytelling() {
        if (this.curr.getEnemies().size() > 0) {
            return "Sistema: prima devi svuotare la stanza.";
        } else {
            return this.script.getRoomScript(Roomevent.chest, Roomevent.afight);
        }
    }

    //m per rimuovere i nemici sconfitti dalla stanza
    public void enemiesEliminated() {
        this.curr.removeAllEnemies();
    }

    //m per eliminare un evento svolto
    public void eventConcluded() {
        this.curr.modifyEvent(Roomevent.noone);
    }

    //m per ottenere l'elenco del contenuto della chest
    public Vector<String> getChest() {
        return this.curr.getChest();
    }

    public String getChestname() {
        return this.curr.getChestname();
    }

    //m per ottenere l'elenco di cio' che sta per terra
    public Vector<String> getOntheGround() {
        return this.curr.getOntheground();
    }

    //m per raccogliere oggetti da terra
    public Item takefromOntheGround(String itemname) {
        return this.curr.takeitemfromtheground(itemname);
    }

    //m per prendere oggetti dalla chest
    public Item takefromChest(String itemname) {
        return this.curr.takeItemfronChest(itemname);
    }

    //m per buttare oggetti per terra
    public void addItemontheGround(Item item) {
        this.curr.addWeaponontheGround(item);
    }

    //m per porre oggetti nella chest
    public void addItemintheChest(Item item) {
        this.curr.addChest(item);
    }

    public Boolean openDoor(Key key) {
        if (key.getOpenDoor() == this.curr.getIdroom()) {
            this.curr.modifyEvent(Roomevent.noone);
            this.curr.opendoor();
            if (key.getOpenDoor() == 500) {
                Iterator ite = this.rooms.iterator();
                while (ite.hasNext()) {
                    Room room = (Room) ite.next();
                    if (room.getIdroom() == 200) {
                        room.setEventVector(Roomevent.noone);
                        room.opendoor();
                    }
                }
            }
            return true;
        }
        return false;
    }

    //m per cercare la prossima stanza
    private Room searchRoom(int idroom) {
        Iterator ite = this.rooms.iterator();
        Room temp = null;
        while (ite.hasNext()) {
            temp = (Room) ite.next();
            if (temp.getIdroom() == idroom) {
                return temp;
            }
        }
        return this.createRoom(idroom);
    }

    public ArrayList<Room> getRoomList() {
        return this.rooms;
    }

    private void buildroom(int idpg, int currRoom) {

        InterfaceDBMS db = new DBConnection();
        Vector<RoomLoad> rsroom = db.LoadRoom(idpg);

        if (!rsroom.isEmpty()) {
            for (int i = 0; i < rsroom.size(); i++) {
                this.createRoom(rsroom.get(i).getIdRoom());
                
                Iterator ite = rooms.iterator();
                while (ite.hasNext()) {
                    Room room = (Room) ite.next();
                    if (room.getIdroom() == rsroom.get(i).getIdRoom()) {
                        if (room.getIdroom() == currRoom) {
                            this.curr = room;
                        }
                        if (rsroom.get(i).getNoEnemy()) {
                            room.removeAllEnemies();
                        }
                        if (rsroom.get(i).getEmptyChest()) {
                            room.removeAllinChest();
                        }
                        room.setBlockedDoor(rsroom.get(i).getBlockDoor());
                        Vector<RoomChestLoad> rschest = db.LoadChestRoom(idpg, room.getIdroom());
                        if (!rschest.isEmpty()) {
                            for (int k = 0; k < rschest.size(); k++) {
                                for (int m = 1; m <= rschest.get(k).getQuantity(); m++) {
                                    Item item = this.gassist.createItem(this.intfpar.getWord(rschest.get(k).getNameIt()));
                                    
                                    if (item.getLvItem() != 0) {
                                        for (int j = 1; j < rschest.get(k).getLv(); j++) {
                                            if (item.getType() == Typeitem.weapon) {
                                                item.getWeapon().weaponLVup();
                                            }
                                            if (item.getType() == Typeitem.shield) {
                                                item.getShield().weaponLVup();
                                            }
                                        }
                                    }
                                    if (rschest.get(k).getInInChest()) {
                                        if (!room.IsinChest(item.getNameItem())) {
                                            room.addChest(item);
                                        }
                                    } else {
                                        room.addWeaponontheGround(item);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
