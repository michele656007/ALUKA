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
public class BagLoad {
    
    private String name;
    private Boolean holds;
    private int lv;
    private int quantity;
    
    public BagLoad(String name,Boolean holds,int lv,int quantity){
        this.name = name;
        this.holds = holds;
        this.lv = lv;
        this.quantity = quantity;
    }
    
    public String getName(){
        return this.name;
    }
    public Boolean getHolds(){
        return this.holds;
    }
    public int getLv(){
        return this.lv;
    }
    public int getQuantity(){
        return this.quantity;
    }
}
/*
"CREATE TABLE IF NOT EXISTS Bag("
                            + "Idpg int,"
                            + "Obname varchar(30),"
                            + "Holds bool,"
                            + "Lv int,"
                            + "Quantity int,"
                            + "FOREIGN KEY(Idpg) REFERENCES Pgtable(Idpg),"
                            + "PRIMARY KEY(Idpg,Obname,Holds,Lv)"
                            + ")";
*/