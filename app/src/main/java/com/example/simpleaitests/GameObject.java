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

    public GameObject(int unitX, int unitY, float unitLength, float unitHeight, float unitSpeed) {
        x = unitX;
        y = unitY;
        length = unitLength;
        height = unitHeight;

        gameObject = new RectF(x, y, length, height);
    }

    // Returns the game object
    public RectF getGameObject() {
        return gameObject;
    }

    public void update() {
    }

    // Sets the position of the game object
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
