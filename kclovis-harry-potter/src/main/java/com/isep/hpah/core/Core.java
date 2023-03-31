package com.isep.hpah.core;

import com.isep.hpah.support.Rand;

public enum Core {
    PHOENIX_FEATHER,
    DRAGON_HEARTSTRING;

    @Override
    public String toString() {
        String s = "";

        if (super.equals(Core.PHOENIX_FEATHER)) {
            s = "phoenix feather";
        } else if (super.equals(Core.DRAGON_HEARTSTRING)) {
            s = "dragon heartstring";
        }

        return s;
    }
}
