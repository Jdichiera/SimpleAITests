package com.example.simpleaitests;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

// We will extend new objects from this class to make the player and enemy game objects
public class GameObject {
    // Our game object will be a rectangle
    private RectF gameObject;

    // The dimensions of our object
    private float length;
    private float height;

    public GameObject(int unitLeft, int unitTop, float unitLength, float unitHeight) {
        length = unitLength;
        height = unitHeight;

        gameObject = new RectF(unitLeft, unitTop, unitLength, unitHeight);
    }

    // Returns the game object
    public RectF getGameObject() {
        return gameObject;
    }

    public void update() {

    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(gameObject, paint);
    }

    // Sets the position of the game object
    public void setPosition(float positionX, float positionY) {
        gameObject.left = positionX;
        gameObject.right = positionX + length;
        gameObject.top = positionY;
        gameObject.bottom = positionY - height;
    }

    // Gives the dimensions
    public float getX() {
        return this.gameObject.centerX();
    }

    public float getY() {
        return this.gameObject.centerY();
    }
}
