package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class home extends Application {
    public static final String CURRENCY = "￥";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Group root = new Group();
            //设置登录scene
            FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            AnchorPane pane = homePaneLoader.load();
            Scene scene = new Scene(pane, 1800, 1000);
            primaryStage.setScene(scene);
            primaryStage.setTitle("MMM-market");
            primaryStage.getIcons().add(new Image("D:\\E\\COMMON\\junior\\java\\otherCode\\demo\\src\\main\\resources\\images\\iconNew.png"));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
