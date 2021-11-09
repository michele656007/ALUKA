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
public class Vampire extends Enemy{
    private int damage = 0;
    
    public Vampire(String name) {
        super(23, 8, 200, 30, 18, name,4000);
    }

    @Override
    public Specialattack attack(int pgca) {
          Random random = new Random();
        int chance = random.nextInt(99) + 1;
        if (chance <= 30) {
            this.damage = this.normalattack(pgca);
            return Specialattack.normal;
        }
        if (chance > 30 && chance <= 60) {
            this.damage = this.bite(pgca);
            return Specialattack.bite;
        }
        if (chance > 60 && chance <= 100) {
            this.specialattack(pgca);
            return Specialattack.slash;
        }
        return null;
    }

    @Override
    public int getdamage() {
        return this.damage;
    }
    
    private int normalattack(int pgca){
        return this.normalAttack(pgca, 8);
    }
    //2d10 di danno (1d10 di cura per il boss) 
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
     
    private int specialattack(int pgca) {
        int damage = 0;
        Random random = new Random();

        if (pgca < random.nextInt(19) + 1 + 10) {
            for (int j = 0; j < 4; j++) {
                damage = damage + random.nextInt(10) + 1;
            }
        }
        return damage;
    }
}
