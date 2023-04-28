package com.example.demo;

import com.example.demo.grid.GridType;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game(GridType.SIMPLE_GFX, 15, 40, 200);

        game.init();
        game.start();
    }
}
