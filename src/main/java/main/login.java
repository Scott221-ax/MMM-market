package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //设置登录scene
            FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent paneSignIn = homePaneLoader.load();
            Scene sceneSignIn = new Scene(paneSignIn, 450, 600);

            primaryStage.setResizable(false);
            primaryStage.setScene(sceneSignIn);
            primaryStage.setTitle("MMM-market");
            primaryStage.getIcons().add(new Image("D:\\E\\COMMON\\junior\\java\\otherCode\\demo\\src\\main\\resources\\images\\iconNew.png"));
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


