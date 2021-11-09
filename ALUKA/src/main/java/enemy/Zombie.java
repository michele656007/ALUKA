/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enemy;

import Equipe.Weapon;
import enumeration.Specialattack;
import java.util.Vector;

/**
 *
 * @author miche
 */
public class Zombie extends Enemy {

    
    private int damage = 0;

    public Zombie(String name) {
        super(11, 0, 12, 15, 10, name, 200);
    }

    @Override
    public Specialattack attack(int pgca) {

        damage = normalAttack(pgca, 1);
        return Specialattack.normal;
        
    }

    @Override
    public int getdamage() {
        return this.damage;
    }

}
