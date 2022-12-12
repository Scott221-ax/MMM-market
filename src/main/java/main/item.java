package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class item extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //设置登录scene
            FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("item.fxml"));
            AnchorPane paneSignIn = homePaneLoader.load();
            Scene sceneSignIn = new Scene(paneSignIn);

            primaryStage.setResizable(false);
            primaryStage.setScene(sceneSignIn);
            primaryStage.setTitle("MMM-market");
            primaryStage.getIcons().add(new Image("file:/D:/E/COMMON/junior/java/MMM-market/src/main/resources/image/pea.png"));
//            primaryStage.getIcons().add(new Image("D:\\E\\COMMON\\junior\\java\\otherCode\\demo\\src\\main\\resources\\images\\iconNew.png"));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
