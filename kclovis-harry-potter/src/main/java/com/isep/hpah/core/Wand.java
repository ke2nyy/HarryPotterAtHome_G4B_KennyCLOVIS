package com.isep.hpah.core;

import com.isep.hpah.support.Rand;

import java.util.Random;

public class Wand {

    private Core core;
    private int size;

    public Wand() {
        // generate random core
        this.core = randomCore();
        this.size = Rand.getInstance().randomInt(30);
    }

    private Core randomCore() {
        int coreSize = Core.values().length;
        int randomIndex = Rand.getInstance().randomInt(coreSize);

        return Core.values()[randomIndex];
    }
    public String toString() {
        return "La baguette vous a choisi! Vous avez reçu une baguette de " + size + "cm à base de " + core.toString();
    }
}
