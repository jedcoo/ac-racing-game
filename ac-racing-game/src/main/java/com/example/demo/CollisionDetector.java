package com.example.demo;

import com.example.demo.gameobject.GameObject;
import com.example.demo.grid.position.GridPosition;

import java.util.List;

public class CollisionDetector {

    private List<GameObject> gameObjects;

    public CollisionDetector(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public boolean isUnSafe(GridPosition position) {
        for (GameObject c : gameObjects) {
            if (c.getPosition() != position && c.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for collisions with specific car
     * Requires iterating the array once
     * @param gameObject
     */
    public void check(GameObject gameObject) {
        for (GameObject ic : gameObjects) {
            // No point in checking collisions with self
            if (ic == gameObject) {
                continue;
            }

            if (ic.getPosition().equals(gameObject.getPosition())) {
                ic.crash();
                gameObject.crash();
            }
        }
    }
}
