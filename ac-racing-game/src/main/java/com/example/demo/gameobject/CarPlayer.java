package com.example.demo.gameobject;

import com.example.demo.grid.position.GridPosition;

public class CarPlayer extends GameObject {
    public CarPlayer(GridPosition position) {
        super(position, GameObjectType.PLAYER);
    }
}
