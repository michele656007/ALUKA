/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMS;

/**
 *
 * @author miche
 */
public class RoomLoad {
    private int idroom;
    private Boolean noenemy;
    private int blockdoor;
    private Boolean emptychest;
    
    public RoomLoad(int idroom,boolean noenemy,int blockdoor,Boolean emptychest){
        this.idroom = idroom;
        this.noenemy = noenemy;
        this.blockdoor = blockdoor;
        this.emptychest = emptychest;
    }
    
    public int getIdRoom(){
        return this.idroom;
    }
    
    public Boolean getNoEnemy(){
        return this.noenemy;
    }
    
    public int getBlockDoor(){
        return this.blockdoor;
    }
    
    public Boolean getEmptyChest(){
        return this.emptychest;
    }
}
/*
"CREATE TABLE IF NOT EXISTS Room("
                            + "Idroom int,"
                            + "Id int,"
                            + "noEnemy bool,"
                            + "FOREIGN KEY(Id) REFERENCES Pgtable(Idpg),"
                            + "PRIMARY KEY(Idroom)"
                            + ")";
*/