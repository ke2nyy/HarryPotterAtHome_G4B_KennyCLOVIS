package com.isep.hpah.core;

import com.isep.hpah.support.Rand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SortingHat {

    // Bill Pugh Singleton
    private static class SortingHatHelper {
        private static final SortingHat INSTANCE = new SortingHat();
    }

    public static SortingHat getInstance() {
        return SortingHatHelper.INSTANCE;
    }

    public House chooseHouse() {
        // generate random number
        int randomHouseIndex = Rand.getInstance().randomInt(4);

        // pick house from random number
        House house = House.values()[randomHouseIndex];
        return house;
    }
}
