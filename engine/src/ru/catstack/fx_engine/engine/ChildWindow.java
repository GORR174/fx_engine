package ru.catstack.fx_engine.engine;

import javafx.stage.Stage;
import ru.catstack.fx_engine.impl.GController;

import java.net.URL;

public class ChildWindow {
    private Stage stage;
    private URL url;
    private GController controller;

    public ChildWindow(Stage stage, URL url, GController controller) {
        this.stage = stage;
        this.url = url;
        this.controller = controller;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public GController getController() {
        return controller;
    }

    public void setController(GController controller) {
        this.controller = controller;
    }
}
