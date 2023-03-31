package com.isep.hpah.core.enemies;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class EnemyFactory {
    private Map<String, Enemy> enemies;
    private Map<String, Boss> bosses;

    private EnemyFactory() {
        initEnemies();
        initBosses();
    }
    private static class EnemyFactoryHelper {
        private static final EnemyFactory INSTANCE = new EnemyFactory();
    }

    public static EnemyFactory getInstance() {
        return EnemyFactoryHelper.INSTANCE;
    }

    private void initEnemies() {
        enemies = new HashMap<String, Enemy>();

        // troll
        Enemy troll = new Enemy(EnemyType.PHYSICAL, "troll", 7, 15);
        enemies.put("troll", troll);

        // basilic
        Enemy basilic = new Enemy(EnemyType.PHYSICAL, "basilic", 10, 70);
        enemies.put("basilic", basilic);

        // deatheater
        Enemy deatheather = new Enemy(EnemyType.PHYSICAL, "mangemort", 10, 70);
        enemies.put("mangemort", deatheather);

        // dementors
        Enemy dementor = new Enemy(EnemyType.NON_PHYSICAL, "détraqueur", 25, 80);
        enemies.put("detraqueur", dementor);

        // voldemort et pettigrew
        Enemy vAndPettigrew = new Enemy(EnemyType.NON_PHYSICAL, "détraqueur", 25, 80);
        enemies.put("pettigrew", vAndPettigrew);

        // ombrage
        Enemy ombrage = new Enemy(EnemyType.PHYSICAL, "ombrage", 10, 100);
        enemies.put("ombrage", ombrage);

        // BONUS
        // allied students
        Enemy corruptStudent = new Enemy(EnemyType.PHYSICAL, "étudiant corrompu", 20, 30);
        enemies.put("etudiant corrompu", corruptStudent);
    }

    private void initBosses() {
        bosses = new HashMap<String, Boss>();

        Boss voldemort = new Boss(EnemyType.PHYSICAL, "Voldemort et Bellatrix");
        bosses.put("boss voldemort", voldemort);
    }
}
