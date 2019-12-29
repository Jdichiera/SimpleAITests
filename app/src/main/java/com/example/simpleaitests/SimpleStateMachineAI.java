package com.example.simpleaitests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class SimpleStateMachineAI extends Activity {
    // Gameview holds the game loop and logic
    GameView gameView;
    // Just a button
    Button button;
    // This is the main layout and allows us to have the game view and the button
    FrameLayout frame;
    // This holds the button and anything else we want to have on top of the game view
    LinearLayout widgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frame = new FrameLayout(this);
        gameView = new GameView(this);
        widgets = new LinearLayout(this);
        button = new Button(this);
        button.setWidth(300);
        button.setText("Back");
        widgets.addView(button);
        frame.addView(gameView);
        frame.addView(widgets);
//        setContentView(R.layout.activity_second);
        setContentView(frame);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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

        // We use SurfaceHolder to hold Canvas and Paint
        SurfaceHolder surfaceHolder;
        Canvas canvas;
        Paint paint;

        // A flag to control the game loop
        volatile boolean playing;

        boolean paused = true;

        // Tracks the games framerate
        long fps;

        // Time on the current frame
        private long timeThisFrame;

        // The constructor uses surfaceView to construct
        public GameView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint = new Paint();
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
            gameThread = new Thread();
            gameThread.start();
        }

        // SurfaceView implements OnTouchListener so we can override ontouchEvent to detect screen touches
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                // Screen is touched
                case MotionEvent.ACTION_DOWN:
                    break;

                // Screen is not touched
                case MotionEvent.ACTION_UP:
                    break;
            }

            return true;
        }
    }
}
