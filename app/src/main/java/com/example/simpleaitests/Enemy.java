package com.example.simpleaitests;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy extends GameObject {


    private Boolean enemyDetected = false;
    public Integer detectionRadius = 300;
    EnemyState enemyState;

    public Enemy(int unitX, int unitY, float unitLength, float unitHeight) {
        super(unitX, unitY, unitLength, unitHeight);
        enemyState = new EnemyState();
        enemyState.setState(EnemyState.State.IDLE);
    }

    public void setEnemyDetected(Boolean enemyDetected) {
        if(enemyDetected == true) {
            enemyState.setState(EnemyState.State.DETECTED);
        } else {
            enemyState.setState(EnemyState.State.IDLE);
        }
        this.enemyDetected = enemyDetected;
    }

    public Boolean isEnemyDetected() {
        return enemyDetected;
    }

    public void draw(Canvas canvas, Paint paint) {
        enemyState.draw(this, canvas, paint);
    }

    public void update() {

    }
}
