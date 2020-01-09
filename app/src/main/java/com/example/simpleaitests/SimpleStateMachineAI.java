package com.example.simpleaitests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SimpleStateMachineAI extends Activity {
    // Gameview holds the game loop and logic
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);

        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    // This is the inner implementation of the GameView class
    // Implement runnable so we have a separate thread and we
    // can override the run method for our game loop
    class GameView extends SurfaceView implements Runnable {
        Thread gameThread = null;
        Player player;
        Enemy enemy;

        // We use SurfaceHolder to hold Canvas and Paint
        SurfaceHolder surfaceHolder;
        Canvas canvas;
        Paint paint;

        // A flag to control the game loop
        volatile boolean playing;

        boolean paused = false;

        // Tracks the games framerate
        long fps;

        // Time on the current frame
        private long timeThisFrame;

        // The constructor uses surfaceView to construct
        public GameView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint = new Paint();
            player = new Player(0, 0, 50, 100);
            enemy = new Enemy(100, 300, 50, 100);

            player.setPosition(400, 500);
            enemy.setPosition(1300, 500);
        }

        // Run holds our game loop
        @Override
        public void run() {
            while (playing) {
                // Get the start frame time
                long startFrameTime = System.currentTimeMillis();

                if (!paused) {
                    update();
                }

                draw();

                // calculate the FPS. We will use the result to time animations and smooth movement
                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }

        // Everything that needs to update during a game frame happens here.
        // Logic, collision, etc
        public void update() {
            isColliding();
            player.update();
            enemy.update();

        }

        // Draw the scene
        public void draw() {
            // Verify that the drawing surface is good
            if (surfaceHolder.getSurface().isValid()) {
                // Lock the canvas so we can use it
                canvas = surfaceHolder.lockCanvas();

                // Draw the background
                canvas.drawColor(Color.argb(255,  0, 0, 0));

                // Set the brush color that you draw with
                paint.setColor(Color.argb(255,  255, 255, 255));

                // Draw the player
//                canvas.drawRect(player.getGameObject(), paint);
                player.draw(canvas, paint);


                // Draw the score
                paint.setTextSize(40);
                canvas.drawText("p-left: " + player.getGameObject().left
                        + " p-right: " + player.getGameObject().right
                        + " p-top: " + player.getGameObject().top
                        + " p-bottom: " + player.getGameObject().bottom, 10, 50, paint);

                canvas.drawText("e-left: " + (enemy.getGameObject().left - enemy.detectionRadius)
                        + " e-right: " + (enemy.getGameObject().right + enemy.detectionRadius)
                        + " e-top: " + (enemy.getGameObject().top - enemy.detectionRadius)
                        + " e-bottom: " + (enemy.getGameObject().bottom + enemy.detectionRadius), 10, 100, paint);




//                canvas.drawText("playerX: " + player.getGameObject().centerX() +
//                        " playerY: " + player.getGameObject().centerY(), 10, 50, paint);
//                canvas.drawText("enemyDetectX: " + player.getGameObject().centerX() +
//                        " playerY: " + player.getGameObject().centerY(), 10, 50, paint);
                canvas.drawText("Detected: " + enemy.isEnemyDetected(), 10, 200, paint);

                // Draw the enemy and detectection circle
                paint.setColor(Color.argb(255,  255, 0, 0));
//                canvas.drawRect(enemy.getGameObject(), paint);
                enemy.draw(canvas, paint);

                // Draw everything to the screen
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

        // If this activity is paused the shut down the game thread
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }
        }

        // If the activity is started then we start the game thread
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        // Check for player and enemy detection radius collision
        public void isColliding() {
            if (isHorizontalCollision() && isVerticalCollision()) {
                enemy.setEnemyDetected(true);
            } else {
                enemy.setEnemyDetected(false);
            }
        }

        public Boolean isHorizontalCollision() {
            if (player.getGameObject().right > enemy.getGameObject().left - enemy.detectionRadius
                    && player.getGameObject().left < enemy.getGameObject().right + enemy.detectionRadius) {
                return true;
            } else {
                return false;
            }
        }

        public Boolean isVerticalCollision() {
            if (player.getGameObject().top > enemy.getGameObject().top - enemy.detectionRadius
                    && player.getGameObject().bottom < enemy.getGameObject().bottom + enemy.detectionRadius) {
                return true;
            } else {
                return false;
            }
        }
        // SurfaceView implements OnTouchListener so we can override ontouchEvent to detect screen touches
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                // Screen is touched
                case MotionEvent.ACTION_DOWN:
                    paused = false;
                    break;

                // Screen is not touched
                case MotionEvent.ACTION_UP:
                    break;

                // Screen is touched and finger dragged.
                case MotionEvent.ACTION_MOVE:
                    gameView.player.setPosition( motionEvent.getX(),  motionEvent.getY());
                    break;
            }

            return true;
        }
    }
}
