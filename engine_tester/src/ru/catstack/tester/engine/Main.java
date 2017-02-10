package ru.catstack.tester.engine;

import ru.catstack.fx_engine.App;
import ru.catstack.fx_engine.GApp;
import ru.catstack.fx_engine.impl.IMain;

import java.io.IOException;

public class Main extends IMain {

    public static void main(String[] args) {
        Main main = new Main();

        main.url = Main.class.getResource("");

        new App(main);
    }

    @Override
    public void onStart() throws IOException {
        GApp.app.setScene(Main.class.getResource("/fxml/sample.fxml"));
    }
}
