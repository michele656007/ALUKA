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
//custode della cripta
public class Keepercrypts extends Enemy {

    private int damage = 0;
    //bab = 6
    public Keepercrypts(String name) {
        super(19, 6, 52, 19, 14, name, 800);
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
            return Specialattack.clawed;
        }
        if (chance > 50 && chance <= 75) {
            this.damage = this.normalattack(pgca);
            return Specialattack.normal;
        }
        if (chance > 75) {
            this.damage = this.specialattack(pgca);
            return Specialattack.clawed;
        }
        return null;

    }

    @Override
    public int getdamage() {
        return this.damage;
    }

    private int normalattack(int pgca) {
        return super.normalAttack(pgca, 6);
    }

    private int specialattack(int pgca) {
        Random random = new Random();
        int damage = 0;
        if (pgca < random.nextInt(19) + 1 + 10) {
            for (int i = 1; i <= 2; i++) {
                damage = random.nextInt(7) + 5+((super.str-10)/2);
            }
        }
        return damage;
    }
}
