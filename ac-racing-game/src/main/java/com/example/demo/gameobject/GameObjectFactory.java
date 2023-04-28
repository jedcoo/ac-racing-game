package com.example.demo.gameobject;

import com.example.demo.grid.Grid;

public class GameObjectFactory {

    public static GameObject getGameObject(Grid grid) {

        int random = (int) (Math.random() * GameObjectType.values().length);
        GameObjectType gameObjectType = GameObjectType.values()[random];

        switch (gameObjectType) {
            case TREE:
                return new Tree(grid.makeGridPosition());
            case MUSTANG:
                return new Mustang(grid.makeGridPosition());
            default:
                return new Tree(grid.makeGridPosition());
        }
    }
}
