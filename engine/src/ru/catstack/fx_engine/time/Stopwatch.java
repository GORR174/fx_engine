package ru.catstack.fx_engine.time;

import javafx.util.Duration;

public class Stopwatch {
    private Timer timer;
    private float time = 0f;
    private boolean isRunning = false;

    public Stopwatch(){
        timer = new Timer(Duration.millis(1), new Runnable() {
            @Override
            public void run() {
                time+=0.001f;
            }
        });
    }

    public void startStopwatch(){
        timer.run();
        isRunning = true;
    }

    public void stopStopwatch(){
        timer.stop();
        isRunning = false;
    }

    public float getTime() {
        return time;
    }

    public boolean isRunning(){
        return isRunning;
    }
}
