package com.example.simpleaitests;

import android.graphics.RectF;

// We will extend new objects from this class to make the player and enemy game objects
public class GameObject {
    // Our game object will be a rectangle
    private RectF gameObject;

    // The dimensions of our object
    private float length;
    private float height;

    public GameObject(int unitX, int unitY, float unitLength, float unitHeight) {
        length = unitLength;
        height = unitHeight;

        gameObject = new RectF(unitX, unitY, length, height);
    }

    // Returns the game object
    public RectF getGameObject() {
        return gameObject;
    }

    public void update() {

    }

    // Sets the position of the game object
    public void setPosition(float x, float y) {
        gameObject.left = x;
        gameObject.right = x + length;
        gameObject.top = y;
        gameObject.bottom = y - height;
    }
}
