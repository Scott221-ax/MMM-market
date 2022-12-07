package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import model.adPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Test implements Initializable {

    @FXML
    private Button button;

    @FXML
    private StackPane stacktest;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        adPane adpane = new adPane(200, 810);
        StackPane stackPane = adpane.getadPane();

        stacktest.getChildren().add(stackPane);
//        Thread thread=new Thread(()->{
//            adPane adpane = new adPane(200, 810);
//            StackPane stackPane = adpane.getadPane();
//            Platform.runLater(()->{
//
////error!
//
//                sub.setRoot(stackPane);
//            });
//        });
    }
}

