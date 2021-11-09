/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import enumeration.Specialattack;
import java.util.Random;

/**
 *
 * @author miche
 */

/*
Boss segreto trovato solo se il modificatore di intelligenza > 8
*/
public class Nosferatu extends Enemy{
    
    private int damage = 0;
    
    public Nosferatu(String name) {
        super(30,11,200,30,24, name,4800);
    }

    @Override
    public Specialattack attack(int pgca) {
        Random random = new Random();
        int chance = random.nextInt(99) + 1;
        if (chance <= 10) {
            this.damage = this.normalattack(pgca);
            return Specialattack.normal;
        }
        if (chance > 10 && chance <= 50) {
            this.damage = this.bite(pgca);
            return Specialattack.bite;
        }
        if (chance > 50 && chance <= 90) {
            this.damage = this.sneakattack(pgca);
            return Specialattack.sneakattack;
        }
        if (chance > 90) {
            return Specialattack.createskeleton;
        }
        return null;
    }

    @Override
    public int getdamage() {
        return this.damage;
    }
    
    private int normalattack(int pgca){
        return this.normalAttack(pgca, 6);
    }
    //2d10 di danno (1d10 di cura per il boss) 
    private int bite(int pgca){
        Random random = new Random();
        int damage = 0;
        if(pgca<random.nextInt(19)+1+3){
            damage = random.nextInt(9)+1;
            this.restoreHp(damage);
            damage = damage + random.nextInt(9)+1;
        }
        
        return damage;
    }
    
    private void restoreHp(int hp){
        if(this.hptot<= this.hpcurr+hp)
            this.hpcurr = this.hptot;
        else
            this.hpcurr = this.hpcurr + hp;
    }
    
    private int sneakattack(int pgca){
        Random random = new Random();
        int damage = 0;
        if(pgca<random.nextInt(19)+1+10){
            for(int i =0;i<5;i++){
                damage = damage + random.nextInt(5)+1;
            }
            damage = damage + (this.des-10)/2;
        }
        return damage;
    }
}
