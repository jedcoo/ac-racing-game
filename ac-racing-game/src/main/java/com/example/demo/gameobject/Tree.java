package com.example.demo.gameobject;

import com.example.demo.grid.position.GridPosition;

public class Tree extends GameObject {

    public Tree(GridPosition pos) {
        super(pos, GameObjectType.TREE);
    }
}
