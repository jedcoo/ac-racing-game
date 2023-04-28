package com.example.demo;

import com.example.demo.grid.GridType;
import com.example.demo.gameobject.GameObject;
import com.example.demo.gameobject.GameObjectFactory;
import com.example.demo.gameobject.Player;
import com.example.demo.grid.Grid;
import com.example.demo.grid.GridFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Grid grid;
    private List<GameObject> gameObjects;
    private int delay;
    private CollisionDetector collisionDetector;
    private int manufacturedCars = 10;

    public Game(GridType gridType, int cols, int rows, int delay) {
        grid = GridFactory.makeGrid(gridType, cols, rows);
        this.delay = delay;
        this.gameObjects = new ArrayList<>();
    }

    public void init() {
        grid.init();

        collisionDetector = new CollisionDetector(gameObjects);

        createCarPlayer();
        createObstacles();
    }

    private void createCarPlayer() {
        GameObject playerGameObject = new Player(grid.makeGridPosition((grid.getCols() / 2), grid.getRows() - 2));
        playerGameObject.setCollisionDetector(collisionDetector);
        playerGameObject.setGrid(grid);
        gameObjects.add(playerGameObject);
    }

    public void start() throws InterruptedException {
        int turn = 0;
        while (true) {
            turn++;

            if (turn % 5 == 0 && gameObjects.size() < manufacturedCars) {
                createObstacles();
            }
            Thread.sleep(delay);

            moveAllCars();
        }
    }

    private void createObstacles() {
        GameObject gameObject = GameObjectFactory.getGameObject(grid);
        gameObject.setCollisionDetector(collisionDetector);
        gameObject.setGrid(grid);

        gameObjects.add(gameObject);
    }

    public void moveAllCars() {
        List<GameObject> removedGameObjects = new ArrayList<>();

        for (GameObject gameObject : gameObjects) {
            gameObject.move();
            collisionDetector.check(gameObject);

            if (gameObject.isHittingWall()) {
                removedGameObjects.add(gameObject);
                gameObject.getPosition().hide();
            }
        }
        for (GameObject gameObject : removedGameObjects) {
            gameObjects.remove(gameObject);

            //TODO refactor to factory
            createObstacles();
        }
    }
}
