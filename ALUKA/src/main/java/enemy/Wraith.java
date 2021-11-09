/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import Equipe.Weapon;
import enumeration.Specialattack;
import java.util.Random;

/**
 *
 * @author miche
 */
public class Wraith extends Enemy{
    
    private int damage = 0;
    
    public Wraith(String name) {
        super(18,7,47,10,16,name,1200);
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
            this.damage = this.specialattack(pgca);
            return Specialattack.touch;
        }
        if (chance > 50 && chance <= 75) {
            this.damage = this.normalattack(pgca);
            return Specialattack.normal;
        }
        if (chance > 75) {
            this.damage = this.specialattack(pgca);
            return Specialattack.touch;
        }
        return null;
    }

    @Override
    public int getdamage() {
        return this.damage;
    }
    
    private int normalattack(int pgca){
        return super.normalAttack(pgca,3);
    }
    
    private int specialattack(int pgca){
        Random random = new Random();
        int damage = 0;
        if(pgca<random.nextInt(19)+1+3){
            damage = random.nextInt(5)+1;
            this.restoreHp(damage);
            damage = damage + random.nextInt(5)+1;
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
