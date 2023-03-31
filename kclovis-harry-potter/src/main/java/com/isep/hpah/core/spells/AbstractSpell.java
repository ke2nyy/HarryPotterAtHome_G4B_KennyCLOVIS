package com.isep.hpah.core.spells;

import com.isep.hpah.core.Character;
import com.isep.hpah.core.enemies.AbstractEnemy;
import com.isep.hpah.support.Rand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractSpell {

    private int accuracy;
    private int damage;

    public abstract void spellDialogue();

    public boolean didAccuratelyCastSpell() {
        int castingChance = Rand.getInstance().randomInt(100);
        return castingChance <= accuracy;
    }

    public abstract int castSpell(AbstractEnemy enemy);
}
