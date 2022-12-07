package model;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class adPane {
    private double height;
    private double width;
    private double index0_x = 0;
    private double index1_x = 270;
    private double index2_x = 540;
    private double img_y = 0;
    private double img_z = 50;
    private Duration time = Duration.seconds(0.5);

    private ImageView left_button;
    private ImageView right_button;
    private ArrayList<ImageView> Img_list;

    public adPane() {
        this.width = 810;
        this.height = 150;
    }

    public adPane(double height, double width) {
        this.width = width;
        this.height = height;
    }

    private HBox arrowHbox() {
        left_button = new ImageView("D:\\E\\COMMON\\junior\\java\\MMM-market\\src\\main\\resources\\image\\leftArrow.png");
        left_button.setPreserveRatio(true);
        left_button.setFitWidth(50);
        right_button = new ImageView("D:\\E\\COMMON\\junior\\java\\MMM-market\\src\\main\\resources\\image\\rightArrow.png");
        right_button.setPreserveRatio(true);
        right_button.setFitWidth(50);
        HBox hBox = new HBox(width - (left_button.getFitWidth() * 2.7));
        hBox.getChildren().addAll(left_button, right_button);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    public StackPane getadPane() {
        double fitwidth = width / 2.5;
        ImageView v0 = new ImageView("D:\\E\\COMMON\\junior\\java\\MMM-market\\src\\main\\resources\\image\\1122.jpg");
        v0.setPreserveRatio(true);
        v0.setFitWidth(fitwidth);
        ImageView v1 = new ImageView("D:\\E\\COMMON\\junior\\java\\MMM-market\\src\\main\\resources\\image\\banner.jpg");
        v1.setPreserveRatio(true);
        v1.setFitWidth(fitwidth);
        ImageView v2 = new ImageView("D:\\E\\COMMON\\junior\\java\\MMM-market\\src\\main\\resources\\image\\banner2.jpg");
        v2.setPreserveRatio(true);
        v2.setFitWidth(fitwidth);

        index0_x = 0 - img_z;
        index1_x = ((width / 2) - (v1.getFitWidth() / 2));
        index2_x = width - v2.getFitWidth() + img_z;

        img_y = ((height / 2) - (v1.prefHeight(-1) / 2));
        v0.setTranslateX(index0_x);
        v1.setTranslateX(index1_x);
        v2.setTranslateX(index2_x);
        v0.setTranslateY(img_y);
        v1.setTranslateY(img_y);
        v2.setTranslateY(img_y);
        v0.setTranslateZ(img_z);
        v2.setTranslateZ(img_z);

        Img_list = new ArrayList<ImageView>();
        Img_list.add(v0);
        Img_list.add(v1);
        Img_list.add(v2);

        AnchorPane ap = new AnchorPane();
        ap.getChildren().addAll(v0, v1, v2);

        SubScene subScene = new SubScene(ap, width, height, true, SceneAntialiasing.BALANCED);
        PerspectiveCamera camrea = new PerspectiveCamera();
        subScene.setCamera(camrea);
        StackPane pane = new StackPane();
        pane.setStyle("-fx-background-color: black");
        HBox hBox = arrowHbox();
        pane.getChildren().addAll(subScene, hBox);

        left_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                right_to_left(Img_list);
            }
        });
        right_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                left_to_right(Img_list);
            }
        });
        return pane;
    }

    private void right_to_left(ArrayList<ImageView> list) {
        ImageView left = list.get(0);
        ImageView middle = list.get(1);
        ImageView right = list.get(2);

        TranslateTransition tt_right = new TranslateTransition();
        tt_right.setDuration(time);
        tt_right.setNode(right);
        tt_right.setFromX(index2_x);
        tt_right.setFromZ(img_z);
        tt_right.setToX(index1_x);
        tt_right.setToZ(0);
        tt_right.play();

        TranslateTransition tt_middle = new TranslateTransition();
        tt_middle.setDuration(time);
        tt_middle.setNode(middle);
        tt_middle.setFromX(index1_x);
        tt_middle.setFromZ(0);
        tt_middle.setToX(index0_x);
        tt_middle.setToZ(img_z);
        tt_middle.play();

        TranslateTransition tt_left = new TranslateTransition();
        tt_left.setDuration(time);
        tt_left.setNode(left);
        tt_left.setFromX(index0_x);
        tt_left.setFromZ(img_z);
        tt_left.setToX(index2_x);
        tt_left.setToZ(img_z);
        tt_left.play();
        list.clear();
        list.add(middle);
        list.add(right);
        list.add(left);
    }

    private void left_to_right(ArrayList<ImageView> list) {
        ImageView left = list.get(0);
        ImageView middle = list.get(1);
        ImageView right = list.get(2);
        TranslateTransition tt_right = new TranslateTransition();
        tt_right.setDuration(time);
        tt_right.setNode(right);
        tt_right.setFromX(index2_x);
        tt_right.setFromZ(img_z);
        tt_right.setToX(index0_x);
        tt_right.setToZ(img_z);
        tt_right.play();

        TranslateTransition tt_middle = new TranslateTransition();
        tt_middle.setDuration(time);
        tt_middle.setNode(middle);
        tt_middle.setFromX(index1_x);
        tt_middle.setFromZ(0);
        tt_middle.setToX(index2_x);
        tt_middle.setToZ(img_z);
        tt_middle.play();

        TranslateTransition tt_left = new TranslateTransition();
        tt_left.setDuration(time);
        tt_left.setNode(left);
        tt_left.setFromX(index0_x);
        tt_left.setFromZ(img_z);
        tt_left.setToX(index1_x);
        tt_left.setToZ(0);

        tt_left.play();
        list.clear();
        list.add(middle);
        list.add(right);
        list.add(left);
        list.clear();
        list.add(right);
        list.add(left);
        list.add(middle);
    }
}
