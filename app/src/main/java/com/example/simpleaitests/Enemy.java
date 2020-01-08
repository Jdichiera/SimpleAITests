package com.example.simpleaitests;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Enemy extends GameObject {
    Boolean enemyDetected = false;
//    Integer detectionRadius = 350;
//    RectF detectionArea;

    public Enemy(int unitX, int unitY, float unitLength, float unitHeight) {
        super(unitX, unitY, unitLength, unitHeight);
//        detectionArea = new RectF(
//                unitX - detectionRadius,
//                unitY - detectionRadius,
//                unitLength + detectionRadius,
//                unitHeight + detectionRadius);
    }

    public void setEnemyDetected(Boolean enemyDetected) {
        this.enemyDetected = enemyDetected;
    }

    // Sets the position of the detection area
//    public void setDetectionAreaPosition(float positionX, float positionY) {
//        detectionArea.left = positionX - detectionRadius;
//        detectionArea.right = positionX + detectionArea.width() - detectionRadius;
//        detectionArea.top = positionY + detectionRadius;
//        detectionArea.bottom = positionY - detectionArea.height() + detectionRadius;
//    }

    public Boolean isEnemyDetected() {
        return enemyDetected;
    }
//
//    public Integer getDetectionRadius() {
//        return detectionRadius;
//    }
//
//    public RectF getDetectionArea() {
//        return detectionArea;
//    }

    public void update() {

    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(getGameObject(), paint);

        // Set the paint style to stroke to draw an outline and draw the detection area for the enemy
        paint.setStyle(Paint.Style.STROKE);
//                canvas.drawCircle(enemy.getX(), enemy.getY(), enemy.getDetectionRadius(), paint);
        // Starting with a rectangle then moving to circle.
        RectF t = getGameObject();
        canvas.drawRect(new RectF(t.left - 300, t.top - 300, t.right + 300, t.bottom + 300), paint);
        paint.setStyle(Paint.Style.FILL);
    }


}
