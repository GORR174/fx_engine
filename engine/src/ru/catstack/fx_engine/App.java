package ru.catstack.fx_engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.impl.IMain;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class App extends Application {

    private static IMain main;
    public ArrayList<OtherWindow> otherWindows = new ArrayList<>();

    private Stage pStage;

    public App(){};

    public App(IMain main){
        GApp.config = main.config;
        App.main = main;
        Application.launch(App.class);
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        pStage = primaryStage;

        GApp.app = this;
        main.onStart();

        updateDefaultSettings();
    }

    public void updateDefaultSettings() {

        pStage.setWidth(GApp.config.width);
        pStage.setHeight(GApp.config.height);
        pStage.setTitle(GApp.config.title);
        pStage.setResizable(GApp.config.resizable);
        pStage.setFullScreen(GApp.config.fullscreen);
        pStage.setMaximized(GApp.config.maximized);
        pStage.setAlwaysOnTop(GApp.config.alwaysOnTop);
        pStage.setMaxWidth(GApp.config.maxWidth);
        pStage.setMaxHeight(GApp.config.maxHeight);
        pStage.setMinWidth(GApp.config.minWidth);
        pStage.setMinHeight(GApp.config.minHeight);
        pStage.setOpacity(GApp.config.opacity);
        if(GApp.config.x != -777) pStage.setX(GApp.config.x);
        if(GApp.config.y != -777) pStage.setY(GApp.config.y);

        pStage.show();

    }

    public void setScene(URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader(url);
        GApp.app.pStage.setScene(new Scene(loader.load()));
        GController controller = loader.getController();
        controller.onShow();
    }

    public Stage getStage() {
        return pStage;
    }

    public void addWindow(URL url, String title){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle(title);
            stage.show();
            GController controller = fxmlLoader.getController();
            otherWindows.add(new OtherWindow(stage, url, controller));
            controller.onShow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWindow(URL url, String title, Stage owner){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(owner);
            stage.setTitle(title);
            stage.show();
            GController controller = fxmlLoader.getController();
            otherWindows.add(new OtherWindow(stage, url, controller));
            controller.onShow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(URL url){
        for (OtherWindow window : otherWindows){
            if(window.getUrl().equals(url)){
                window.getStage().close();
                otherWindows.remove(window);
                break;
            }
        }
    }

    public void closeAllWindows(){
        for (OtherWindow window : otherWindows){
            window.getStage().close();
            otherWindows.remove(window);
        }
    }
}
