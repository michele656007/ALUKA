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
public class PgLoad {
    private int idpg =0;
    private String name = null;
    private int lv;
    private int exp;
    private int nextlv;
    private int hpcur;
    private int hptot;
    private int sancurr;
    private int str;
    private int des;
    private int cos;
    private int sma;
    private int fed;
    private int currRoom;
    private int mo;
    
    public PgLoad(int idpg,String name,int lv,int exp,int nextlv,int hpcur,
            int hptot,int sancurr,int str,int des,int cos,int sma,int fed,int currRoom,int mo){
        
        this.idpg = idpg;
        this.name = name;
        this.lv = lv;
        this.exp = exp;
        this.nextlv = nextlv;
        this.hpcur = hpcur;
        this.hptot = hptot;
        this.sancurr = sancurr;
        this.str = str;
        this.des = des;
        this.cos = cos;
        this.sma = sma;
        this.fed = fed;
        this.currRoom = currRoom;
        this.mo = mo;
        
    }
    
    public int getIdpg(){
        return this.idpg;
    }
    public String getName(){
        return this.name;
    }
    public int getLv(){
        return this.lv;
    }
    public int getExp(){
        return this.exp;
    }
    public int getNextLv(){
        return this.nextlv;
    }
    public int getHpCurr(){
        return this.hpcur;
    }
    public int getHpTot(){
        return this.hptot;
    }
    public int getSanCurr(){
        return this.sancurr;
    }
    public int getStr(){
        return this.str;
    }
    public int getDes(){
        return this.des;
    }
    public int getCos(){
        return this.cos;
    }
    public int getSma(){
        return this.sma;
    }
    public int getFed(){
        return this.fed;
    }
    public int getCurrRoom(){
        return this.currRoom;
    }
    public int getMo(){
        return this.mo;
    }
}
