package com.isep.hpah.core.spells;

import com.isep.hpah.core.SpellType;
import com.isep.hpah.core.enemies.AbstractEnemy;
import com.isep.hpah.core.enemies.EnemyType;
import lombok.Getter;

@Getter
public class Spell extends AbstractSpell {

    private SpellType spellType;

    public Spell(SpellType spellType, int damage, int accuracy) {
        this.spellType = spellType;
        this.setDamage(damage);
        this.setAccuracy(accuracy);
    }

    @Override
    public void spellDialogue() {
        System.out.println("Vous avez untilisé le sort " + spellType.toString() + ".");
    }

    @Override
    public int castSpell(AbstractEnemy enemy) {
        int damage =
        switch (spellType) {
            case WINGARDIUM_LEVIOSA -> wingardiumLeviosa(enemy);
            case ACCIO -> accioSword(enemy);
            case EXPELLIARMUS -> expelliarmus(enemy);
            case SECTUMSEMPRA -> sectumsempra(enemy);
            case EXPECTO_PATRONUM -> expectoPatronum(enemy);
            default -> throw new IllegalStateException("Unexpected value: " + spellType);
        };

        return damage;
    }

    private int expectoPatronum(AbstractEnemy enemy) {
        System.out.println();
        System.out.println("Expecto Patronum");

        if (didAccuratelyCastSpell()) {
            // only attack dementors
            if (enemy.getEnemyType() == EnemyType.NON_PHYSICAL) {
                return this.getDamage();
            }
        }

        return 0;
    }

    private int sectumsempra(AbstractEnemy enemy) {
        System.out.println();
        System.out.println("Sectumsempra");

        if (didAccuratelyCastSpell()) {
            return this.getDamage();
        }

        return 0;
    }

    private int wingardiumLeviosa(AbstractEnemy enemy) {
        System.out.println();
        System.out.println("Wingardium Leviosa");
        System.out.println("Tu as soulevé une pierre");

        if (!didAccuratelyCastSpell()) {
            System.out.println("Tu n'as pas tenu le sort assez longtemps. L'attaque a échoué");
            return 0;
        }

        System.out.println("Tu as laché la pierre sur la tête de l'ennemi!");
        return this.getDamage();
    }

    private int expelliarmus(AbstractEnemy enemy) {
        // spell accuracy
        if (!didAccuratelyCastSpell()) {
            System.out.println("Le sort n'a pas été jeté correctement");
            return -1; // -1 means disarming wand
        }

        return 0;
    }

    private int accioSword(AbstractEnemy enemy) {
        System.out.println();
        System.out.println("Accio");

        if (didAccuratelyCastSpell()) {
            System.out.println("");
            return this.getDamage();
        }

        return 0;
    }
}
