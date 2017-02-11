package ru.catstack.tester.engine;

import ru.catstack.fx_engine.engine.App;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.resources.GApp;
import ru.catstack.fx_engine.utility.Timer;

public class Main extends GApplication {

    public static void main(String[] args) {
        Main main = new Main();

        main.config.width = 1280;
        main.config.height = 720;
        main.config.title = "Engine Tester";

        new App(main);
    }

    @Override
    public void onStart() throws Exception {
        GApp.app.setScene(Main.class.getResource("/fxml/sample.fxml"));
        GApp.app.addWindow(Main.class.getResource("/fxml/sample.fxml"), "1");
        GApp.app.addWindow(Main.class.getResource("/fxml/sample.fxml"), "2");
        GApp.app.addModalWindow(Main.class.getResource("/fxml/sample.fxml"), "3", GApp.app.getStage(), true);
        GApp.app.closeAllWindows();
        Integer[] count = {0};
        Timer timer = new Timer(1, () -> {});
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
