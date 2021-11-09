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
//Classe base per la definizione delle caratteristiche dei nemici
public abstract class Enemy {

    int ca = 0;
    int initiative = 0;
    int hptot = 0;
    int exp = 0;
    int hpcurr = 0;
    int str = 0;
    int des = 0;
    String name = null;

    public Enemy(int ca, int initiative, int hptot, int str, int des, String name, int exp) {
        this.ca = ca;
        this.des = des;
        this.initiative = initiative;
        this.hptot = hptot;
        this.str = str;
        this.name = name;
        this.hpcurr = this.hptot;
        this.exp = exp;
    }

    public abstract Specialattack attack(int pgca);

    public abstract int getdamage();

    public int normalAttack(int pgca, int bab) {
        Random random = new Random();
        int damage = 0;
        if (pgca < (random.nextInt(19) + 1 + bab)) {
            damage = random.nextInt(3) + 1 + ((this.str - 10) / 2);
        }

        return damage;
    }

    public int getInitiative() {
        return this.initiative + ((this.des - 10) / 2) + 10;
    }

    public Boolean takedamage(int damage) {
        this.hpcurr = this.hpcurr - damage;
        if (this.hpcurr > 0) {
            System.out.println("mi rimangono hp:"+this.hpcurr);
            return true;
        } else {
            return false;
        }
    }

    public int getCa() {
        return this.ca;
    }

    public String getName() {
        return this.name;
    }

    public int getExp() {
        return this.exp;
    }
}
