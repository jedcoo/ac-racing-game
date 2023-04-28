package com.example.demo.gameobject;

import com.example.demo.CollisionDetector;
import com.example.demo.grid.Grid;
import com.example.demo.grid.GridColor;
import com.example.demo.grid.GridDirection;
import com.example.demo.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

abstract public class GameObject {
    private GridPosition position;
    private Grid grid;
    private GameObjectType gameObjectType;
    private boolean crashed;
    private Picture picture;
    protected CollisionDetector collisionDetector;
    protected GridDirection currentDirection;

    public GameObject(GridPosition position, GameObjectType gameObjectType) {
        this.position = position;
        this.gameObjectType = gameObjectType;

        position.setColor(gameObjectType.getColor());
        currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public GridPosition getPosition() {
        return position;
    }

    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public void crash() {
        this.crashed = true;
        position.setColor(GridColor.RED);
    }

    public void move() {
        accelerate(GridDirection.DOWN, 1);
    }

    public void accelerate(GridDirection direction, int speed) {

        // Crashed cars can not accelerate
        if (isCrashed()) {
            return;
        }

        this.currentDirection = direction;
        for (int i = 0; i < speed; i++) {
            getPosition().moveInDirection(direction, 1);
            if (collisionDetector.isUnSafe(getPosition())) {
                crash();
                break;
            }
        }
    }

    public boolean isHittingWall() {
        return position.getRow() == grid.getRows() - 1;
    }
}

