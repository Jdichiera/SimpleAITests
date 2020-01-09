package com.example.simpleaitests;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy extends GameObject {


    private Boolean enemyDetected = false;
    public Integer detectionRadius = 300;
    public Integer detectionTime = 15000; // The number of MS that an enemy detects the player before changing state
    EnemyState enemyState;
    Long enemyDetectedStartTime = 0L;
    Long enemyDetectedLength = 0L;


    public Enemy(int unitX, int unitY, float unitLength, float unitHeight) {
        super(unitX, unitY, unitLength, unitHeight);
        enemyState = new EnemyState();
        enemyState.setState(EnemyState.State.IDLE);
    }

    public EnemyState getState() {
        return enemyState;
    }
    public void setEnemyDetected(Boolean enemyDetected, Long playerDetectionStartTime) {
        if (enemyDetectedStartTime == 0) {
            enemyDetectedStartTime = playerDetectionStartTime;
        }
        if(enemyDetected == true) {
            if (enemyDetectedLength < detectionTime) {
                enemyDetectedLength += System.currentTimeMillis() - enemyDetectedStartTime;
                enemyState.setState(EnemyState.State.DETECTED);
            } else {
                enemyState.setState(EnemyState.State.SEEN);
            }
        } else {
            enemyDetectedStartTime = 0L;
            enemyDetectedLength = 0L;
            enemyState.setState(EnemyState.State.IDLE);
        }
        this.enemyDetected = enemyDetected;
    }

    public Long getEnemyDetectedTime() {
        return enemyDetectedLength / 1000;
    }

    public Boolean isEnemyDetected() {
        return enemyDetected;
    }

    public Boolean isEnemySighted() {
        return enemyDetected;
    }


    public void update() {

    }
}
