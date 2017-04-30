package ru.catstack.tester.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.time.Stopwatch;
import ru.catstack.fx_engine.time.Timer;

public class StopwatchController implements GController{

    @FXML
    public Label time;


    @Override
    public void onShow() throws Exception {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.startStopwatch();

        Timer timer = new Timer(Duration.millis(10), () -> {});

        timer.setAction(() -> {
            System.out.println(stopwatch.getTime());
            time.setText(String.valueOf(Math.round(stopwatch.getTime())));

            if(stopwatch.getTime() >= 100) {
                stopwatch.stopStopwatch();
                timer.stop();
            }
        });

        timer.run();
    }
}
