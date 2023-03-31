package com.isep.hpah.support;

import java.util.Random;

public class Rand {

    // Bill Pugh Singleton
    private Rand() {}

    private static class RandHelper {
        private static final Rand INSTANCE = new Rand();
    }

    public static Rand getInstance() {
        return RandHelper.INSTANCE;
    }

    public int randomInt(int upperBound) {
        Random random = new Random();
        int randInt = random.nextInt(upperBound);
        return randInt;
    }
}
