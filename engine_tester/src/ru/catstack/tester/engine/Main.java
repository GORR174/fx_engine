package ru.catstack.tester.engine;

import ru.catstack.fx_engine.App;
import ru.catstack.fx_engine.GApp;
import ru.catstack.fx_engine.impl.GApplication;

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
        GApp.app.addWindow(Main.class.getResource("/fxml/sample.fxml"), "3");
        GApp.app.closeAllWindows();
    }
}
