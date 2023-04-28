package com.example.demo.gameobject;

import com.example.demo.grid.GridColor;

public enum GameObjectType {
    TREE(GridColor.GREEN),
    MUSTANG(GridColor.BLUE),
    PLAYER(GridColor.MAGENTA);

    private GridColor color;

    GameObjectType(GridColor color) {
        this.color = color;
    }

    public GridColor getColor() {
        return this.color;
    }
}
