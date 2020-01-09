package com.example.simpleaitests;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Enemy extends GameObject {
    Boolean enemyDetected = false;
    Integer detectionRadius = 300;
    RectF detectionArea;

    public Enemy(int unitX, int unitY, float unitLength, float unitHeight) {
        super(unitX, unitY, unitLength, unitHeight);
        detectionArea = new RectF(
                gameObject.left - detectionRadius,
                gameObject.top - detectionRadius,
                gameObject.right + detectionRadius,
                gameObject.bottom + detectionRadius
        );
    }

    public void setEnemyDetected(Boolean enemyDetected) {

        this.enemyDetected = enemyDetected;
    }

    // Sets the position of the detection area
//    public void setDetectionAreaPosition(float positionX, float positionY) {
//        detectionArea.left = gameObject.left - detectionRadius;
//        detectionArea.top = gameObject.top - detectionRadius;
//        detectionArea.right = gameObject.right + detectionRadius;
//        detectionArea.bottom = gameObject.bottom  + detectionRadius;
//    }

    public Boolean isEnemyDetected() {
        return enemyDetected;
    }
//
//    public Integer getDetectionRadius() {
//        return detectionRadius;
//    }
//
    public RectF getDetectionArea() {
        return detectionArea;
    }

    public void update() {
//        setDetectionAreaPosition(getX(), getY());

    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(getGameObject(), paint);

        // Set the paint style to stroke to draw an outline and draw the detection area for the enemy
        paint.setStyle(Paint.Style.STROKE);
//                canvas.drawCircle(enemy.getX(), enemy.getY(), enemy.getDetectionRadius(), paint);
        // Starting with a rectangle then moving to circle.
        RectF t = getGameObject();
        canvas.drawRect(new RectF(t.left - detectionRadius, t.top - detectionRadius, t.right + detectionRadius, t.bottom + detectionRadius), paint);
        paint.setStyle(Paint.Style.FILL);
    }


}
