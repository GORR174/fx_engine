package ru.catstack.fx_engine.utility;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Timer {

    private Timeline timeline;
    private int count = 0;
    private Runnable onFinished = new Runnable() {
        @Override
        public void run() {

        }
    };

    public Timer(int seconds, Runnable action){
        this(Duration.seconds(seconds), action);
    }

    public Timer(Duration duration, Runnable action){
        timeline = new Timeline(new KeyFrame(duration, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                count++;
                action.run();
            }
        }));
        setOnFinished(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void setAction(Runnable action){
        timeline = new Timeline(new KeyFrame(timeline.getCycleDuration(), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                count++;
                action.run();
            }
        }));
        setOnFinished(onFinished);
    }

    /**
     * run timer while the timer isn't stopped
     */
    public void run(){
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * run timer with one cycle
     */
    public void runOnce(){
        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     * run timer with CycleCount cycles
     * @param CycleCount count of cycles
     */
    public void runXTimes(int CycleCount){
        timeline.setCycleCount(CycleCount);
        timeline.play();
    }

    /**
     * stop the timer
     */
    public void stop(){
        timeline.stop();
        onFinished.run();
    }

    public int getCount(){
        return count;
    }

    public void setOnFinished(Runnable action){
        onFinished = new Runnable() {
            @Override
            public void run() {
                count = 0;
                action.run();
            }
        };
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onFinished.run();
            }
        });
    }
}
