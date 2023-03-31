package com.isep.hpah.core;

import com.isep.hpah.core.spells.Spell;
import com.isep.hpah.support.ConsoleColors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Wizard extends Character {

    private Pet pet;
    private Wand wand;
    private House house;

    private int accuracy;
    private List<Spell> spells;
    private List<Potion> potions;

    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    private void addHealth(int i) {
        this.setHealth(this.getHealth() + i);
    }

    public void initPotions() {
        potions = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            potions.add(new Potion(25));
        }
    }

    @Override
    public Character attack(Character target) {
        // disarm
        if (this.getDamage() == -1) {
            target.setPossessingWand(false);
            return target;
        }

        target.setHealth(target.getHealth() - this.getDamage());

        // display dialogue
        System.out.println("Tu as fait " + ConsoleColors.RED + this.getDamage() + " dégats" + ConsoleColors.RESET);
        System.out.println("L'ennemi a " + ConsoleColors.CYAN + target.getHealth() + " PV" + ConsoleColors.RESET);

        // reset damage for next turn
        this.setDamage(0);

        return target;
    }

    public void statsDialogue() {
        System.out.println("PV : " + this.getHealth());
        System.out.println();
    }

    public void drinkPotion() {
        if (potions.size() > 0) {
            int healthBoost = potions.get(0).getBaseHealthBoost();
            if (house == House.HUFFLEPUFF) {
                healthBoost += 10;
            }

            addHealth(healthBoost);
            potions.remove(0);

            System.out.println("Tu as gagné " + ConsoleColors.GREEN + healthBoost + " PV" +
                    ConsoleColors.RESET + ". Tu as maintenant " + this.getHealth() + " PV");        } else {
            System.out.println("Tu as perdu ton tour à chercher dans ton sac pour des potions mais il n'en reste plus");
        }
    }

}
