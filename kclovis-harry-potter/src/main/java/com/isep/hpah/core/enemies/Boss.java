package com.isep.hpah.core.enemies;

import com.isep.hpah.core.Character;
import com.isep.hpah.support.ConsoleColors;
import com.isep.hpah.support.Rand;
import lombok.AllArgsConstructor;

public class Boss extends AbstractEnemy {

    public Boss(EnemyType enemyType, String name) {
        this.setEnemyType(enemyType);
        this.setName(name);
    }

    @Override
    public Character attack(Character target) {

        // has been disarmed
        if (!this.isPossessingWand()) {
            this.setPossessingWand(true);
            System.out.println("Le boss avait perdu sa baguette par expelliarmus");
            return target;
        }

        System.out.println("Le boss a utilisé avada kedavra");

        if (Rand.getInstance().randomInt(2) != 1) {
            System.out.println("L'attaque a échoué");
            return target;
        }

        target.setHealth(0);

        // display dialogue
        System.out.println("Le boss a fait "+ ConsoleColors.RED + this.getDamage() + " dégat(s)" + ConsoleColors.RESET);
        return target;
    }
}
