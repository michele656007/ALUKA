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
public class MagicLoad {

    private String name;
    private Boolean isSpel;

    public MagicLoad(String name, Boolean isSpel) {
        this.name = name;
        this.isSpel = isSpel;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getIsSpel() {
        return this.isSpel;
    }

}
/*
"create table if not exists Magic("
                            + "id int,"
                            + "Spel varchar(30),"
                            + "isSpel bool,"
                            + "FOREIGN KEY(id) references pgtable(idpg),"
                            + "primary key(id,spel)"
                            + ")";
*/
