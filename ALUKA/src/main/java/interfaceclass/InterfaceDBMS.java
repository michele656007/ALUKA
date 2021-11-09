/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceclass;

import DBMS.BagLoad;
import DBMS.MagicLoad;
import DBMS.PgLoad;
import DBMS.RoomChestLoad;
import DBMS.RoomLoad;
import character.Pg;
import java.util.Vector;
import roomsystem.Room;

/**
 *
 * @author miche
 */
public interface InterfaceDBMS {
    
    public void saveGame(Pg pg, Vector<Room> rooms, int currRoom);
    
    public int CheckName(String name, int idpg);
    public Vector<String> LoadName(); 
    public PgLoad LoadPg(String name);
    public Vector<RoomLoad> LoadRoom(int idpg);
    public Vector<MagicLoad> LoadMagic(int idpg); 
    public Vector<BagLoad> LoadBag(int idpg);
    public Vector<RoomChestLoad> LoadChestRoom(int idpg,int idroom);
    public void deleteGame(int idpg);
    public void close();
}
