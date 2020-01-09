package com.example.simpleaitests;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class EnemyState {
    public enum State {
        IDLE,
        DETECTED,
        SEEN
    }

    public State activeState;

    public void setState(State state) {
        activeState = state;
    }

    public void draw(GameObject enemy, Canvas canvas, Paint paint) {
        switch (activeState) {
            case IDLE:
                paint.setColor(Color.argb(255,  0, 200, 0));
                break;
            case DETECTED:
                paint.setColor(Color.argb(255,  255, 255, 0));
                break;
            case SEEN:
                paint.setColor(Color.argb(255,  255, 0, 0));
                break;
        }

        canvas.drawRect(enemy.getGameObject(), paint);
        // Set the paint style to stroke to draw an outline and draw the detection area for the enemy
        paint.setStyle(Paint.Style.STROKE);
        RectF t = enemy.getGameObject();
        canvas.drawRect(new RectF(t.left - 300, t.top - 300, t.right + 300, t.bottom + 300), paint);
        paint.setStyle(Paint.Style.FILL);
    }
}
