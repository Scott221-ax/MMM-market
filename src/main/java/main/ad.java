package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.SQL;
import model.adPane;

import java.sql.SQLException;

public class ad extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        //设置登录scene
//            FXMLLoader homePaneLoader = new FXMLLoader(getClass().getResource("adScrollDisplay.fxml"));
//            AnchorPane paneSignIn = homePaneLoader.load();
        adPane adpane = new adPane(200, 810);
        SQL sql = new SQL();
        StackPane stackPane = adpane.getadPane();
//        StackPane stackPane1=new StackPane(stackPane);
        Scene sceneSignIn = new Scene(stackPane, 811, 200);

        //primaryStage.setResizable(false);
        primaryStage.setScene(sceneSignIn);
        primaryStage.setTitle("MMM-market");
        primaryStage.getIcons().add(new Image("D:\\E\\COMMON\\junior\\java\\otherCode\\demo\\src\\main\\resources\\images\\iconNew.png"));
        primaryStage.show();
    }


}
