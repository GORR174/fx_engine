package ru.catstack.tester.testers;

import ru.catstack.fx_engine.engine.App;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.resources.GApp;

public class StopwatchTester implements GApplication{

    public static void main(String[] args) {
        StopwatchTester main = new StopwatchTester();

        main.config.width = 1280;
        main.config.height = 720;
        main.config.title = "Engine Stopwatch Tester";

        new App(main);
    }

    @Override
    public void onStart() throws Exception {

        GApp.app.setScene(Main.class.getResource("/fxml/stopwatch.fxml"));

    }
}
