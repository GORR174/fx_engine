package ru.catstack.tester.controllers;

import javafx.event.ActionEvent;
import ru.catstack.fx_engine.impl.GController;

public class SampleController implements GController {

    @Override
    public void onShow() {
        System.out.println("Showed!");
    }

    public void onButtonClick(ActionEvent actionEvent) {
        System.out.println("Clicked!");
    }
}
