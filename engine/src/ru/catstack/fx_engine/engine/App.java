package ru.catstack.fx_engine.engine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.catstack.fx_engine.impl.GApplication;
import ru.catstack.fx_engine.impl.GController;
import ru.catstack.fx_engine.resources.EngineConfig;
import ru.catstack.fx_engine.resources.GApp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class App extends Application {

    private static GApplication main;
    private ArrayList<ChildWindow> childWindows = new ArrayList<>();

    private Stage pStage;

    @SuppressWarnings("unused")
    public App(){}

    public App(GApplication main){
        GApp.config = main.config;
        App.main = main;
        Application.launch(App.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pStage = primaryStage;

        GApp.app = this;
        updateSettings();

        main.onStart();
        pStage.show();
    }

    /**
     * This method update app settings from GApp.config
     */
    public void updateSettings() {

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

    }

    /**
     * @param config this method update app settings from this config
     */
    public void updateSettings(EngineConfig config) {

        pStage.setWidth(config.width);
        pStage.setHeight(config.height);
        pStage.setTitle(config.title);
        pStage.setResizable(config.resizable);
        pStage.setFullScreen(config.fullscreen);
        pStage.setMaximized(config.maximized);
        pStage.setAlwaysOnTop(config.alwaysOnTop);
        pStage.setMaxWidth(config.maxWidth);
        pStage.setMaxHeight(config.maxHeight);
        pStage.setMinWidth(config.minWidth);
        pStage.setMinHeight(config.minHeight);
        pStage.setOpacity(config.opacity);
        if(config.x != -777) pStage.setX(config.x);
        if(config.y != -777) pStage.setY(config.y);

    }

    /**
     * @param url URL of fxml file
     * @throws IOException
     */
    public void setScene(URL url) throws Exception {
        FXMLLoader loader = new FXMLLoader(url);
        GApp.app.pStage.setScene(new Scene(loader.load()));
        GController controller = loader.getController();
        controller.onShow();
    }

    public Stage getStage() {
        return pStage;
    }


    /**
     * <p>untested method</p>
     * @param url URL of fxml file
     */
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
            childWindows.add(new ChildWindow(stage, url, controller));
            controller.onShow();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method add modal window
     * @param isWait if isWait == true, the program will not be executed while this window will not close
     */
    public void addModalWindow(URL url, String title, Stage owner, boolean isWait) throws Exception {
        try {
            owner.show();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initOwner(owner);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            if(isWait)
                stage.showAndWait();
            else
                stage.show();
            GController controller = fxmlLoader.getController();
            childWindows.add(new ChildWindow(stage, url, controller));
            controller.onShow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url close the window with this URL
     */
    public void closeWindow(URL url){
        for (ChildWindow window : childWindows){
            if(window.getUrl().equals(url)){
                window.getStage().close();
                childWindows.remove(window);
                break;
            }
        }
    }


    /**
     * Close all child windows
     */
    public void closeAllWindows(){
        while (childWindows.size() != 0) {
            childWindows.get(0).getStage().close();
            childWindows.remove(childWindows.get(0));
        }
    }


    /**
     * @return ArrayList of child windows
     */
    public ArrayList<ChildWindow> getChildWindows() {
        return childWindows;
    }
}
