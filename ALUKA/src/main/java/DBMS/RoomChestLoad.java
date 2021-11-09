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
public class RoomChestLoad {
    private int idroom;
    private String nameit;
    private int lv;
    private int quantity;
    private Boolean isinchest;
    
    public RoomChestLoad(int idroom,String nameit,int lv,int quantity,Boolean isinchest){
        this.idroom = idroom;
        this.nameit = nameit;
        this.lv = lv;
        this.quantity = quantity;
        this.isinchest = isinchest;   
    }
    
    public int getIdroom(){
        return this.idroom;
    }
    public String getNameIt(){
        return this.nameit;
    }
    public int getLv(){
        return this.lv;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public Boolean getInInChest(){
        return this.isinchest;
    }
}
/*
"CREATE TABLE IF NOT EXISTS Roomchest("
                            + "Idroom int,"
                            + "Idpg int,"
                            + "Item varchar(30),"
                            + "Lv int,"
                            + "Quantity int,"
                            + "IsinChest bool,"
                            + "FOREIGN KEY(Idpg) REFERENCES Pgtable(Idpg),"
                            + "FOREIGN KEY(Idroom) REFERENCES Room(Idroom),"
                            + "PRIMARY KEY(Idroom,Idpg,Item)"
                            + ")";
*/