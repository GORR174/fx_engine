package ru.catstack.fx_engine.utility;

import javafx.util.Duration;

public class Stopwatch {
    private Timer timer;
    private float time = 0;

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
    }

    public void stopStopwatch(){
        timer.stop();
    }

    public float getTime() {
        return time;
    }
}
