package com.example.simpleaitests;

import android.graphics.RectF;

// We will extend new objects from this class to make the player and enemy game objects
public class GameObject {
    // Our game object will be a rectangle
    private RectF gameObject;

    // Coordinates - x == far left; y == top
    private float x;
    private float y;

    // The dimensions of our object
    private float length;
    private float height;

    // How fast the game object can move
    private float movementSpeed;

    // Constants that we can use to tell the game how our unit is moving
    public final int STOPPED = 0;
    public final int UP = 1;
    public final int RIGHT = 2;
    public final int DOWN = 3;
    public final int LEFT = 4;

    // Holds the current movement state of our game object
    private int movementState = STOPPED;

    public GameObject(int unitX, int unitY, float unitLength, float unitHeight, float unitSpeed) {
        x = unitX;
        y = unitY;
        length = unitLength;
        height = unitHeight;
        movementSpeed = unitSpeed;

        gameObject = new RectF(x, y, length, height);
    }

    // Returns the game object
    public RectF getGameObject() {
        return gameObject;
    }

    // Set the movement state of the game object
    public void setMovementState(int movementState) {
        this.movementState = movementState;
    }

    // Moves the game object in the appropriate direction during the update call
    public void update(long fps) {
        switch (movementState) {
            case UP:
                // move up
                y = y + movementSpeed / fps;
                break;
            case RIGHT:
                // move right
                x = x + movementSpeed / fps;
                break;
            case DOWN:
                // move down
                y = y - movementSpeed / fps;
                break;
            case LEFT:
                // move left
                x = x - movementSpeed / fps;
                break;
        }

        gameObject.left = x;
        gameObject.right = x + length;
        gameObject.top = y;
        gameObject.bottom = y - height;
    }
}
