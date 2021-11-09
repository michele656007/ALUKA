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
//Cavagliere tombale
public class Graveknight extends Enemy {

    private int damage = 0;

    public Graveknight(String name) {
        super(25, 5, 139, 27, 12, name, 6400);
    }

    @Override
    public Specialattack attack(int pgca) {
        Random random = new Random();
        int chance = random.nextInt(99) + 1;
        if (chance <= 50) {
            this.normalattack(pgca);
            return Specialattack.normal;
        }
        if (chance > 50) {
            this.specialattack(pgca);
            return Specialattack.clawed;
        }
        return null;
    }

    private int normalattack(int pgca) {
        return super.normalAttack(pgca, 10);
    }

    private int specialattack(int pgca) {
        int damage = 0;
        Random random = new Random();

        if (pgca < random.nextInt(19) + 1 + 10) {
            for (int j = 0; j < 4; j++) {
                damage = damage + random.nextInt(9) + 1;
            }
        }

        return damage;
    }

    @Override
    public int getdamage() {
        return this.damage;
    }

}
