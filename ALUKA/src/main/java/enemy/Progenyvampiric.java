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
//Enemy(int ca, int initiative, int hptot, int str, int des, String name, int exp)
public class Progenyvampiric extends Enemy{
    int damage = 0;
    public Progenyvampiric(String name) {
        super(17,1,35,15,12, name,600);
    }

    @Override
    public Specialattack attack(int pgca) {
          Random random = new Random();
        int chance = random.nextInt(99) + 1;
        if (chance <= 25) {
            this.damage = this.normalattack(pgca);
            return Specialattack.normal;
        }
        if (chance > 25 && chance <= 50) {
            this.damage = this.bite(pgca);
            return Specialattack.bite;
        }
        if (chance > 50 && chance <= 75) {
            this.damage = this.normalattack(pgca);
            return Specialattack.normal;
        }
        if (chance > 75) {
            this.damage = this.bite(pgca);
            return Specialattack.bite;
        }
        return null;
    }

    @Override
    public int getdamage() {
        return damage;
    }
    
    private int normalattack(int pgca){
        return this.normalAttack(pgca, 8);
    }
    
    private int bite(int pgca){
        Random random = new Random();
        int damage = 0;
        if(pgca<random.nextInt(19)+1+8){
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
    
}
