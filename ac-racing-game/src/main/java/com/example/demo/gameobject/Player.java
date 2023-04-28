package com.example.demo.gameobject;

import com.example.demo.grid.GridDirection;
import com.example.demo.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Player extends CarPlayer implements KeyboardHandler {

    private final int MAX_SPEED = 3;
    private Keyboard keyboard;
    private int speed = 0;

    public Player(GridPosition position) {
        super(position);
        keyboard = new Keyboard(this);
        init();
    }

    public void init() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
    }

    /**
     * @see GameObject#move()
     */
    @Override
    public void move() {
        accelerate(currentDirection, speed);
    }

    /**
     * @see GameObject#accelerate(GridDirection, int)
     */
    @Override
    public void accelerate(GridDirection direction, int speed) {

        // Crashed cars can not accelerate
        if (isCrashed()) {
            return;
        }

        // Accelerate in the choosen direction
        this.currentDirection = direction;
        for (int i = 0; i < speed; i++) {
            getPosition().moveInDirection(direction, 1);
            if (collisionDetector.isUnSafe(getPosition())) {
                crash();
                break;
            }
        }

    }

    /**
     * Handles key press events
     *
     * @param keyboardEvent the keyboard event
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            speed = MAX_SPEED;
            return;
        }

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                currentDirection = GridDirection.LEFT;
                break;
            case KeyboardEvent.KEY_RIGHT:
                currentDirection = GridDirection.RIGHT;
                break;
        }

        if (speed == 0) {
            accelerate(currentDirection, 1);
        }

    }

    /**
     * Handles key release events
     *
     * @param keyboardEvent the keyboard event
     */
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            speed = 0;
        }

    }
}
