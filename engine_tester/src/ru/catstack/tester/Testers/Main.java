package ru.catstack.tester.testers;

import ru.catstack.fx_engine.engine.App;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.resources.GApp;

public class Main implements GApplication {

    public static void main(String[] args) {
        Main main = new Main();

        main.config.width = 1280;
        main.config.height = 720;
        main.config.title = "Main Engine Tester";

        new App(main);
    }

    @Override
    public void onStart() throws Exception {
        GApp.app.setScene(Main.class.getResource("/fxml/sample.fxml"));
        GApp.app.addWindow(Main.class.getResource("/fxml/sample.fxml"), "1");
        GApp.app.addWindow(Main.class.getResource("/fxml/sample.fxml"), "2");
        GApp.app.addModalWindow(Main.class.getResource("/fxml/sample.fxml"), "3", GApp.app.getStage(), true);
        GApp.app.closeAllWindows();
    }
}
