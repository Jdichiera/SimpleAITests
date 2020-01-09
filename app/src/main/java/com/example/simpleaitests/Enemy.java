package com.example.simpleaitests;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy extends GameObject {


    private Boolean enemyDetected = false;
    public Integer detectionRadius = 300;
    public Integer detectionTime = 15000; // The number of MS that an enemy detects the player before changing state
    public EnemyState enemyState;
    public Long enemyDetectedStartTime = 0L;
    public Long enemyDetectedStopTime = 0L;
    public Long enemyDetectedLength = 0L;
    public Integer homeX = 1300;
    public Integer homeY = 500;


    public Enemy(int unitX, int unitY, float unitLength, float unitHeight) {
        super(unitX, unitY, unitLength, unitHeight);
        returnHome();
        enemyState = new EnemyState();
        enemyState.setState(EnemyState.State.IDLE);
    }

    public EnemyState getState() {
        return enemyState;
    }
    public void setEnemyDetected(Boolean enemyDetected) {





        if(enemyDetected) {
            enemyDetectedStopTime = 0L;

            if (enemyDetectedStartTime == 0) {
                enemyDetectedStartTime = System.currentTimeMillis();
            }
            
            if (enemyDetectedLength < detectionTime) {
                enemyDetectedLength += System.currentTimeMillis() - enemyDetectedStartTime;
                enemyState.setState(EnemyState.State.DETECTED);
            } else {
                enemyState.setState(EnemyState.State.SEEN);
            }
        } else {
            enemyDetectedStartTime = 0L;

            if (enemyDetectedStopTime == 0) {
                enemyDetectedStopTime = System.currentTimeMillis();
            }

            if (enemyDetectedLength > 0) {
                enemyDetectedLength -= System.currentTimeMillis() - enemyDetectedStopTime;
                enemyState.setState(EnemyState.State.DETECTED);
            } else {
                enemyState.setState(EnemyState.State.IDLE);
            }
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

    public void returnHome() {
        setPosition(homeX, homeY);
    }

    public void update() {

    }
}
