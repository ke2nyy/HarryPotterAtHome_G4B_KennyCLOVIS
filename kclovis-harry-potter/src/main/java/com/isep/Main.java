package com.isep;

import com.isep.hpah.core.Game;

public class Main {
    public static void main(String[] args) {

        Game game = Game.getInstance();
        game.startGame();
    }
}