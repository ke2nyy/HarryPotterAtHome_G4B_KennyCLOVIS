package com.isep.hpah.core;

import com.isep.hpah.core.enemies.AbstractEnemy;
import com.isep.hpah.core.enemies.Enemy;
import com.isep.hpah.core.spells.Spell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RpgMap {

    private int year;
    private String location;
    private AbstractEnemy enemy;
    private Spell giftedSpell;


    public void mapDetailsDialogue() {
        System.out.println("================");
        System.out.println("Année : " + year);
        System.out.println("Lieu : " + location);
        System.out.println("--------");
    }

    public void currentEnemyApparitionDialogue() {
        System.out.println("Un ennemi a apparu: " + enemy.getName() + "(" + enemy.getHealth() + " PV)");
        System.out.println();
    }
}
