/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import enumeration.Specialattack;

/**
 *
 * @author miche
 */
public class Skeleton extends Enemy {

    int damage = 0;

    public Skeleton(String name) {
        super(16, 6, 10, 15, 14,name, 150);
    }

    @Override
    public Specialattack attack(int pgca) {
        damage = super.normalAttack(pgca, 0);
        return Specialattack.normal;
    }

    @Override
    public int getdamage() {
        return damage;
    }

}
