package com.isep.hpah.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Character {

    private int health = 30;
    private int damage;
    private boolean isPossessingWand;

    public abstract Character attack(Character target);
    public boolean isAlive() {
        return this.getHealth() > 0;
    }
    public int getHealth() {
        return health < 0 ? 0 : health;
    }
}
