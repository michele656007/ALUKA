/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMS;

import Equipe.Item;
import character.Pg;
import interfaceclass.InterfaceDBMS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import roomsystem.Room;

/**
 *
 * @author miche
 */
public class DBConnection implements InterfaceDBMS {

    private Connection cn = null;
    private ResultSet rs = null;
    private String url = "jdbc:h2:./src/dbfile/aluka";
    private String query = null;

    public DBConnection() {

        try {
            Class.forName("org.h2.Driver");
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            if (cn != null) {
                try (Statement st = cn.createStatement()) {
                    query = "CREATE TABLE IF NOT EXISTS Pgtable("
                            + "Idpg INT,"
                            + "Name VARCHAR(20),"
                            + "Lv INT,"
                            + "Exp INT,"
                            + "Nextlv INT,"
                            + "Hpcur INT,"
                            + "Hptot INT,"
                            + "SanCurr INT,"
                            + "Forz INT,"
                            + "Des INT,"
                            + "Cos INT,"
                            + "Int INT,"
                            + "Fed INT,"
                            + "CurrRoom INT,"
                            + "Gold int,"
                            + "PRIMARY KEY (Idpg)"
                            + ")";
                    st.executeUpdate(query);
                    //cn = DriverManager.getConnection(url, "pulsa", "danura");
                    query = "CREATE TABLE IF NOT EXISTS Room("
                            + "id int primary key)";
                    st.executeUpdate(query);

                    query = "CREATE TABLE IF NOT EXISTS RoomPG("
                            + "Idroom int,"
                            + "Id int,"
                            + "noEnemy bool,"
                            + "blockroom int,"
                            + "Emptychest bool,"
                            + "PRIMARY KEY(Idroom,id),"
                            + "FOREIGN KEY(Id) REFERENCES Pgtable(Idpg)"
                            + ")";
                    st.executeUpdate(query);

                    //cn = DriverManager.getConnection(url, "pulsa", "danura");
                    query = "CREATE TABLE IF NOT EXISTS Roomchest("
                            + "Idroom int,"
                            + "Idpg int,"
                            + "Item varchar(30),"
                            + "Lv int,"
                            + "Quantity int,"
                            + "IsinChest bool,"
                            + "PRIMARY KEY(Idroom,Idpg,Item),"
                            + "FOREIGN KEY(Idpg) REFERENCES PgTable(Idpg),"
                            + "FOREIGN KEY(Idroom) REFERENCES Room(Id)"
                            + ")";
                    st.executeUpdate(query);
                    //cn = DriverManager.getConnection(url, "pulsa", "danura");
                    query = "CREATE TABLE IF NOT EXISTS Bag("
                            + "Idpg int,"
                            + "Obname varchar(30),"
                            + "Holds bool,"
                            + "Lv int,"
                            + "Quantity int,"
                            + "PRIMARY KEY(Idpg,Obname,Holds,Lv),"
                            + "FOREIGN KEY(Idpg) REFERENCES Pgtable(Idpg)"
                            + ")";
                    st.executeUpdate(query);
                    //cn = DriverManager.getConnection(url, "pulsa", "danura");
                    query = "create table if not exists Magic("
                            + "id int,"
                            + "Spel varchar(30),"
                            + "isSpel bool,"
                            + "primary key(id,spel),"
                            + "FOREIGN KEY(id) references pgtable(idpg)"
                            + ")";
                    st.executeUpdate(query);
                }
            }
            System.out.println("Connessione avvenuta");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean createConnection() {
        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            return cn != null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void savePg(Pg pg, int currRoom) {

        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            Statement st = this.cn.createStatement();
            if (this.CheckName(pg.getName(), pg.getIdpg()) == 1) {
                query = "update pgtable set "
                        + "lv = " + pg.getLv() + " ,"
                        + "exp = " + pg.getExp() + ","
                        + "nextlv = " + pg.getNextLv() + ","
                        + "hpcur =" + pg.getHpCurr() + " ,"
                        + "hptot =" + pg.getHptot() + ","
                        + "sancurr =" + pg.getSanity() + ","
                        + "forz =" + pg.getStrstat() + " ,"
                        + "des =" + pg.getDesstat() + " ,"
                        + "cos =" + pg.getCosstat() + " ,"
                        + "int =" + pg.getSmastat() + " ,"
                        + "fed =" + pg.getFedstat() + " ,"
                        + "currRoom =" + currRoom + " ,"
                        + "gold =" + pg.getMo() + " "
                        + "where idpg =" + pg.getIdpg();

                try {
                    st.executeUpdate(query);
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    cn = DriverManager.getConnection(url, "pulsa", "danura");
                    PreparedStatement pstm = this.cn.prepareStatement("insert into pgtable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    pstm.setInt(1, pg.getIdpg());
                    pstm.setString(2, pg.getName());
                    pstm.setInt(3, pg.getLv());
                    pstm.setInt(4, pg.getExp());
                    pstm.setInt(5, pg.getNextLv());
                    pstm.setInt(6, pg.getHpCurr());
                    pstm.setInt(7, pg.getHptot());
                    pstm.setInt(8, pg.getSanity());
                    pstm.setInt(9, pg.getStrstat());
                    pstm.setInt(10, pg.getDesstat());
                    pstm.setInt(11, pg.getCosstat());
                    pstm.setInt(12, pg.getSmastat());
                    pstm.setInt(13, pg.getFedstat());
                    pstm.setInt(14, currRoom);
                    pstm.setInt(15, pg.getMo());

                    pstm.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveBag(Vector<Item> bag, Pg pg) {
        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            Statement st = this.cn.createStatement();

            query = "delete from bag where idpg =" + pg.getIdpg();
            st.executeUpdate(query);
            PreparedStatement prst = this.cn.prepareStatement("delete from bag where idpg = ?");
            prst.setInt(1, pg.getIdpg());
            prst.executeUpdate();
            prst.close();

            prst = this.cn.prepareStatement("insert into bag values(?,?,?,?,?)");
            //salvataggio arma
            prst.setInt(1, pg.getIdpg());
            prst.setString(2, pg.getWeapon().getName());
            prst.setBoolean(3, true);
            prst.setInt(4, pg.getWeapon().getLV());
            prst.setInt(5, 1);
            prst.executeUpdate();
            //salvataggio armatura
            prst.setInt(1, pg.getIdpg());
            prst.setString(2, pg.getArmor().getName());
            prst.setBoolean(3, true);
            prst.setInt(4, 0);
            prst.setInt(5, 1);
            prst.executeUpdate();
            //salvataggio scudo 
            if (pg.getShield() != null) {
                prst.setInt(1, pg.getIdpg());
                prst.setString(2, pg.getShield().getName());
                prst.setBoolean(3, true);
                prst.setInt(4, pg.getShield().getLV());
                prst.setInt(5, 1);
                prst.executeUpdate();
            }
            //salvataggio borsa
            if (bag.size() > 0) {
                for (int i = 0; i < bag.size(); i++) {
                    Item item = bag.get(i);
                    int quantity = 1;
                    for (int j = i + 1; j < bag.size(); j++) {
                        if (item.getNameItem().equals(bag.get(j).getNameItem())) {
                            if (item.getLvItem() == bag.get(j).getLvItem()) {
                                quantity++;
                                bag.remove(j);
                            }
                        }
                    }
                    prst.setInt(1, pg.getIdpg());
                    prst.setString(2, item.getNameItem());
                    prst.setBoolean(3, false);
                    prst.setInt(4, item.getLvItem());
                    prst.setInt(5, quantity);
                    prst.executeUpdate();
                }
            }
            prst.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveRoom(int idpg, Vector<Room> rooms) {

        Statement st;
        Boolean isEmpty;
        Item item;
        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            st = this.cn.createStatement();

            for (int i = 0; i < rooms.size(); i++) {

                isEmpty = rooms.get(i).getEnemies().isEmpty();
                query = "select * from room where id =" + rooms.get(i).getIdroom();
                rs = st.executeQuery(query);
                if (!rs.next()) {
                    query = "insert into room values(" + rooms.get(i).getIdroom() + ")";
                    st.executeUpdate(query);
                }

                query = "delete from roomchest where idpg =" + idpg + "and idroom =" + rooms.get(i).getIdroom();
                st.executeUpdate(query);
                query = "delete from roompg where id =" + idpg + "and idroom =" + rooms.get(i).getIdroom();
                st.executeUpdate(query);

                query = "insert into roompg values("
                        + rooms.get(i).getIdroom() + ","
                        + idpg + ","
                        + isEmpty + ","
                        + rooms.get(i).getBlockedDoor() + ","
                        + rooms.get(i).getChest().isEmpty()
                        + ")";
                //cn = DriverManager.getConnection(url, "pulsa", "danura");
                st.executeUpdate(query);
                //System.out.println("sto salvando le chest");
                if (rooms.get(i).getChestItem().size() > 0) {
                    for (int j = 0; j < rooms.get(i).getChestItem().size(); j++) {
                        item = rooms.get(i).getChestItem().get(j);
                        System.out.println(item.getNameItem());
                        int quantity = 1;
                        for (int k = j + 1; k < rooms.get(i).getChestItem().size(); k++) {
                            if (item.getNameItem().equals(rooms.get(i).getChestItem().get(k).getNameItem())) {
                                if (item.getLvItem() == rooms.get(i).getChestItem().get(k).getLvItem()) {
                                    quantity++;
                                    rooms.get(i).getChestItem().remove(k);
                                }
                            }
                        }//chiusura for(k)
                        //cn = DriverManager.getConnection(url, "pulsa", "danura");
                        query = "insert into Roomchest values("
                                + rooms.get(i).getIdroom() + ","
                                + idpg + ","
                                + "'" + item.getNameItem() + "',"
                                + item.getLvItem() + ","
                                + quantity + ","
                                + true + ""
                                + ")";
                        st.executeUpdate(query);
                    }
                }
                if (rooms.get(i).getGroundItem().size() > 0) {
                    for (int j = 0; j < rooms.get(i).getGroundItem().size(); j++) {
                        item = rooms.get(i).getGroundItem().get(j);
                        int quantity = 1;
                        for (int k = j + 1; k < rooms.get(i).getGroundItem().size(); k++) {
                            if (item.getNameItem().equals(rooms.get(i).getGroundItem().get(k).getNameItem())) {
                                if (item.getLvItem() == rooms.get(i).getGroundItem().get(k).getLvItem()) {
                                    quantity++;
                                    rooms.get(i).getGroundItem().remove(k);
                                }
                            }
                        }//chiusura for(k)
                        //cn = DriverManager.getConnection(url, "pulsa", "danura");
                        query = "insert into Roomchest values("
                                + rooms.get(i).getIdroom() + ","
                                + idpg + ","
                                + "'" + item.getNameItem() + "',"
                                + item.getLvItem() + ","
                                + quantity + ","
                                + false + ""
                                + ")";
                        st.execute(query);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveMagic(int idpg, Vector<String> spel, Vector<String> miracle) {
        try {
            PreparedStatement prst;
            try (Statement st = cn.createStatement()) {
                prst = cn.prepareStatement("insert into magic values(?,?,?)");
                ResultSet rs = st.executeQuery("select spel, isspel from magic where id =" + idpg);
                while (rs.next()) {
                    if (rs.getBoolean(2)) {
                        if (spel.indexOf(rs.getString(1)) >= 0) {
                            spel.remove(spel.indexOf(rs.getString(1)));
                        }
                    } else {
                        if (miracle.indexOf(rs.getString(1)) >= 0) {
                            miracle.remove(miracle.indexOf(rs.getString(1)));
                        }
                    }
                }
                for (int i = 0; i < spel.size(); i++) {
                    prst.setInt(1, idpg);
                    prst.setString(2, spel.get(i));
                    prst.setBoolean(3, true);
                    prst.executeUpdate();
                }
                for (int i = 0; i < miracle.size(); i++) {
                    prst.setInt(1, idpg);
                    prst.setString(2, miracle.get(i));
                    prst.setBoolean(3, false);
                    prst.executeUpdate();
                }
                st.close();
            }
            prst.close();

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveGame(Pg pg, Vector<Room> rooms, int currRoom) {

        Vector<Item> bag = new Vector<>();
        Vector<String> spel = new Vector<>();
        Vector<String> miracle = new Vector<>();

        for (int i = 0; i < pg.getBag().size(); i++) {
            bag.add(pg.getBag().get(i));
            System.out.println(pg.getBag().get(i).getNameItem());
        }
        this.savePg(pg, currRoom);
        this.saveBag(bag, pg);
        this.saveRoom(pg.getIdpg(), rooms);

        if (!pg.getBookofSpel().isEmpty()) {
            for (int i = 0; i < pg.getBookofSpel().size(); i++) {
                spel.add(pg.getBookofSpel().get(i));
            }
        }
        if (!pg.getBookofMiracle().isEmpty()) {
            for (int i = 0; i < pg.getBookofMiracle().size(); i++) {
                miracle.add(pg.getBookofMiracle().get(i));
            }
        }
        this.saveMagic(pg.getIdpg(), spel, miracle);

    }

    public int CheckName(String name, int randid) {

        query = "select name,idpg from pgtable";
        int searchidpg = 0;
        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            try (Statement st = this.cn.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                if (!rs.isClosed()) {
                    while (rs.next()) {
                        if (name.equals(rs.getString("name"))) {
                            //errore nome già utizzato
                            searchidpg = 1;
                            break;
                        }
                        if (randid == rs.getInt("idpg")) {
                            searchidpg = 2;
                            break;
                        }
                    }
                }
                st.close();
                cn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchidpg;
    }

    public Vector<String> LoadName() {

        rs = null;
        Vector<String> namepg = new Vector<>();
        if (this.createConnection()) {
            query = "select name from pgtable";
            try {
                Statement st = cn.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) {
                    namepg.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return namepg;

    }

    public PgLoad LoadPg(String name) {

        PgLoad pg = null;
        rs = null;
        query = "Select * from pgtable where Name = '" + name + "'";
        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            Statement st = cn.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()) {
                pg = new PgLoad(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15));
            }
            st.close();
            return pg;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pg;
    }

    public Vector<RoomLoad> LoadRoom(int idpg) {

        Vector<RoomLoad> vetrooml = new Vector<>();
        rs = null;
        query = "select * from roompg where id =" + idpg;

        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(query);
            rs.beforeFirst();
            while (rs.next()) {

                vetrooml.add(new RoomLoad(rs.getInt(1), rs.getBoolean(3), rs.getInt(4), rs.getBoolean(5)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vetrooml;
    }

    public Vector<MagicLoad> LoadMagic(int idpg) {

        Vector<MagicLoad> vetmagicL = new Vector<>();
        rs = null;
        query = "select * from magic where id = " + idpg;

        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            Statement st = this.cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(query);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    vetmagicL.add((new MagicLoad(rs.getString(2), rs.getBoolean(3))));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vetmagicL;
    }

    public Vector<BagLoad> LoadBag(int idpg) {

        Vector<BagLoad> vetbagL = new Vector<>();
        ResultSet rs = null;
        query = "select * from bag where idpg =" + idpg;

        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(query);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    System.out.println(rs.getString(2));
                    vetbagL.add(new BagLoad(rs.getString(2), rs.getBoolean(3), rs.getInt(4), rs.getInt(5)));
                }
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vetbagL;
    }

    public Vector<RoomChestLoad> LoadChestRoom(int idpg, int idroom) {

        Vector<RoomChestLoad> vetroomchest = new Vector<>();

        ResultSet rs = null;
        query = "select * from roomchest where idpg =" + idpg + " and idroom =" + idroom;
        try {
            cn = DriverManager.getConnection(url, "pulsa", "danura");
            Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(query);
            if (rs.next()) {
                rs.beforeFirst();
                while (rs.next()) {
                    System.out.println(rs.getString(3));
                    vetroomchest.add(new RoomChestLoad(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6)));
                }
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vetroomchest;
    }
    public void deleteGame(int idpg){
        try {
            Statement st = cn.createStatement();
            String query = " delete from roomchest where idpg ="+idpg;
            st.executeUpdate(query);
            query = " delete from magic where id ="+idpg;
            st.executeUpdate(query);
            query = " delete from bag where idpg ="+idpg;
            st.executeUpdate(query);
            query = " delete from roompg where id ="+idpg;
            st.executeUpdate(query);
            query = " delete from pgtable where idpg ="+idpg;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void close() {
        try {
            cn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
