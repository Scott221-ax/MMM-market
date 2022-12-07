package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdScrollDisplay implements Initializable {


    @FXML
    private ImageView btleft;

    @FXML
    private ImageView btright;

    @FXML
    private ImageView leftImg;

    @FXML
    private ImageView middleImg;

    @FXML
    private ImageView rightImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/adScrollDisplay"));//将广告图加载出来，还未实现3D效果
            StackPane stackPane = fxmlLoader.load();
            AnchorPane ap = new AnchorPane();
            SubScene subScene = new SubScene(ap, 811, 150, true, SceneAntialiasing.BALANCED);
            PerspectiveCamera camera = new PerspectiveCamera();
            subScene.setCamera(camera);
            StackPane stackPane1 = new StackPane();
            stackPane1.getChildren().addAll(subScene, stackPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
