package com.isep.hpah.core.spells;

import com.isep.hpah.core.Character;
import com.isep.hpah.core.enemies.AbstractEnemy;
import lombok.Setter;

@Setter
public class ForbiddenSpell extends AbstractSpell {

    private String name;

    @Override
    public void spellDialogue() {
        System.out.println("Vous avez untilisé un sort interdit. Vous avez essayé d'utiliser " + name + ".");
    }

    @Override
    public int castSpell(AbstractEnemy enemy) {
        return 0;
    }

}
