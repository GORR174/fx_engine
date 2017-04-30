package ru.catstack.tester.testers;

import ru.catstack.fx_engine.engine.App;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.fx_engine.time.Timer;

public class TimerTester implements GApplication{

    public static void main(String[] args) {
        TimerTester main = new TimerTester();

        main.config.width = 1280;
        main.config.height = 720;
        main.config.title = "Engine Timer Tester";

        new App(main);
    }

    @Override
    public void onStart() throws Exception {
        GApp.app.setScene(Main.class.getResource("/fxml/sample.fxml"));

        Integer[] count = {0};

        Timer timer = new Timer(1, () -> {});

        //every "TimeDuration"
        timer.setAction(() -> {
            System.out.println(timer.getCount());
            if(timer.getCount() == 10){
                timer.stop();
                count[0]++;
            }
            if(count[0] == 1){
                timer.setOnFinished(() -> System.out.println("Finish"));
            }
        });

        timer.setOnFinished(() -> {
            timer.run();
            System.out.println("Finished");
        });

        timer.runXTimes(3);
    }
}
