package com.example.simpleaitests;

public class Enemy extends GameObject {
    Boolean enemyDetected = false;
    Integer detectionRadius = 350;

    public Enemy(int unitX, int unitY, float unitLength, float unitHeight) {
        super(unitX, unitY, unitLength, unitHeight);
    }

    public Boolean isEnemyDetected() {
        return enemyDetected;
    }

    public Integer getDetectionRadius() {
        return detectionRadius;
    }


}
