package com.isep.hpah.core;

import com.isep.hpah.core.enemies.AbstractEnemy;
import com.isep.hpah.core.enemies.Enemy;
import com.isep.hpah.core.enemies.EnemyFactory;
import com.isep.hpah.core.spells.Spell;
import com.isep.hpah.support.ConsoleColors;
import com.isep.hpah.support.InputParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Game {

    Wizard wizard;
    List<RpgMap> maps;
    int round = 6;

    // Bill Pugh Singleton
    private static class GameHelper {
        private static final Game INSTANCE = new Game();
    }

    public static Game getInstance() {
        return GameHelper.INSTANCE;
    }

    public void startGame(){
        initVariables();
        initMaps();             // create Factory class to handle it

        startDialogue();
    }

    private void initVariables() {
        wizard = new Wizard();
        wizard.setSpells(new ArrayList<>());
    }

    private void startDialogue() {
        System.out.println("Bienvenue jeune sorcier! Commençons par t'équiper!\n");
        System.out.println("Voici le chemin de Traverse. Ici tu peux prendre tout ce dont il te faut pour tes aventures!");
        System.out.println("Commençons par te chercher une baguette magique chez Ollivander");

        // setting the new wand
        getNewWand();

        // setting the pet
        choosePetDialogue();
        choosePet();

        System.out.println("Tu es maintenant prêt à aller à Poudlard!");
        System.out.println("--------");

        // setting the house
        houseSelectionDialogue();
        sortInHouse();


        System.out.println("Il est temps que tu commences ta première année..");
        System.out.println("Tiens! Un petit cadeau pour commencer tes aventures (+5 potions)");

        wizard.initPotions();

        playRound(round);
    }

    private void getNewWand() {
        Wand wand = new Wand();
        wizard.setWand(wand);
        wizard.setPossessingWand(true);

        wizard.setHealth(100);

        // display wand dialogue
        System.out.println(wand);
    }

    private void choosePetDialogue() {
        System.out.println("Il est temps de choisir ton animal de compagnon:");

        // display pet options
        Pet[] pets = Pet.values();
        for (int i = 0; i < pets.length; i++) {
            System.out.println((i+1) + ". " + pets[i].name().toLowerCase());
        }
    }

    private void choosePet() {
        Pet[] pets = Pet.values();

        int choice = InputParser.questionWithIntOutput("Entrer le numéro correspondant pour choisir l'animal: ",
                    1,
                    pets.length);

        // select pet based on choice
        Pet pet = pets[choice - 1];
        wizard.setPet(pet);

        // display chosen pet dialogue
        System.out.println("Félicitations! Tu as adopté un '" + pet.toString() + "'.");
    }

    private void houseSelectionDialogue() {
        System.out.println("Bienvenue à Poudelard! On va commencer par t'assigner une maison.");
        System.out.println("Assis toi sur cette chaise. Professeur McGonagall va placer le Choixpeau sur ta tête pour déterminer ta maison");
        System.out.println("hmmm... Le Choixpeau à du mal à te cerner...");
    }

    private void sortInHouse() {
        // select random house
        House house = SortingHat.getInstance().chooseHouse();

        wizard.setHouse(house);

        System.out.println(house.name());
        System.out.println("Félictations!! C'est une très bonne maison.");
    }

    private void initMaps() {

        EnemyFactory enemyFactory = EnemyFactory.getInstance();
        // map 1

        RpgMap map1 = new RpgMap(1, "Toilettes du donjon",
                enemyFactory.getEnemies().get("troll"), new Spell(SpellType.WINGARDIUM_LEVIOSA, 15, 95));

        RpgMap map2 = new RpgMap(2, "Chambre des secrets",
                enemyFactory.getEnemies().get("basilic"), new Spell(SpellType.ACCIO, 15, 95));

        RpgMap map3 = new RpgMap(3, "Lac dans la Forêt Interdite",
                enemyFactory.getEnemies().get("detraqueur"), new Spell(SpellType.EXPECTO_PATRONUM, 15, 95));

        RpgMap map4 = new RpgMap(4, "Cimetière de Little Hangleton",
                enemyFactory.getEnemies().get("pettigrew"), new Spell(SpellType.EXPELLIARMUS, 0, 80));

        RpgMap map5 = new RpgMap(5, "Salle d’examen de Poudlard",
                enemyFactory.getEnemies().get("ombrage"), new Spell(SpellType.WINGARDIUM_LEVIOSA, 15, 95));

        RpgMap map6 = new RpgMap(6, "Tour d’astronomie",
                enemyFactory.getEnemies().get("mangemort"), new Spell(SpellType.SECTUMSEMPRA, 15, 95));

        RpgMap map7 = new RpgMap(7, "Poudlard",
                enemyFactory.getBosses().get("boss voldemort"), null);

        maps = new ArrayList<>();
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);
        maps.add(map4);
        maps.add(map5);
        maps.add(map6);
        maps.add(map7);
    }

    private void playRound(int r) {

        RpgMap currentMap = maps.get(r - 1);
        currentMap.mapDetailsDialogue();
        currentMap.currentEnemyApparitionDialogue();

        // add new spell for map
        if (currentMap.getGiftedSpell() != null) {
            wizard.addSpell(currentMap.getGiftedSpell());
        }

        AbstractEnemy currentEnemy = currentMap.getEnemy();

        while (wizard.isAlive() && currentEnemy.isAlive()) {
            System.out.println();
            currentEnemy = playTurn(currentEnemy);

            // enemy attacks wizard
            Character attackedWizard = currentEnemy.attack(wizard);
            wizard.setHealth(attackedWizard.getHealth());
            wizard.setPossessingWand(attackedWizard.isPossessingWand());

            System.out.println("Tu as " + ConsoleColors.CYAN + wizard.getHealth() + " PV" + ConsoleColors.RESET);        }

        if (!currentEnemy.isAlive()) {
            System.out.println("L'ennemi est mort");
            if (round == 7) {
                System.out.println("FELICITATIONS! TU AS GAGNÉ");
                return;
            }
            round++;
            playRound(round);
            return;
        }

        if (!wizard.isAlive()) {
            System.out.println("GAME OVER..");
        }
    }

    private void playerTurn() {
        wizard.statsDialogue();
        System.out.println();

    }

    public AbstractEnemy playTurn(AbstractEnemy enemy) {
        choiceDialogue();

        int choice = InputParser.questionWithIntOutput("Que vas-tu faire ? ", 1, 2);

        switch (choice) {
            case 1:
                chooseSpell(enemy);
                Character attackedEnemy = wizard.attack(enemy);
                enemy.setHealth(attackedEnemy.getHealth());
                enemy.setPossessingWand(attackedEnemy.isPossessingWand());
                break;
            case 2:
                drinkPotion();
                break;

        }

        return enemy;
    }

    private void choiceDialogue() {
        System.out.println("C'est ton tour");
        System.out.println("1. Jeter un sort");
        System.out.println("2. Utiliser une potion");
    }

    private void chooseSpell(AbstractEnemy target) {
        for(int i = 0; i < wizard.getSpells().size(); i++) {
            System.out.println((i+1) + ". " + wizard.getSpells().get(i).getSpellType());
        }

        int choice = InputParser.questionWithIntOutput("Quel sort veux tu jeter ? ", 1, wizard.getSpells().size());
        int damage = wizard.getSpells().get(choice - 1).castSpell(target); // target is returned from cast spell

        wizard.setDamage(damage);
    }

    private void drinkPotion() {
        wizard.drinkPotion();
    }
}
