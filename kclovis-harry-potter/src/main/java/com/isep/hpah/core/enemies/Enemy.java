package com.isep.hpah.core.enemies;

import com.isep.hpah.core.Character;
import com.isep.hpah.support.ConsoleColors;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Enemy extends AbstractEnemy {

    private int damage;
    private int accuracy;

    public Enemy(EnemyType enemyType, String name, int damage, int accuracy) {
        this.setEnemyType(enemyType);
        this.setName(name);
        this.damage = damage;
        this.accuracy = accuracy;
    }

    @Override
    public Character attack(Character target) {
        target.setHealth(target.getHealth() - this.getDamage());

        // display dialogue
        System.out.println("L'ennemi a fait " + ConsoleColors.RED + this.getDamage() + " dégat(s)" + ConsoleColors.RESET);
        return target;
    }
}
