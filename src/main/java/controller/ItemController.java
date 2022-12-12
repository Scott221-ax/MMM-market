package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.MyListener;
import main.home;
import model.Item;

import java.net.MalformedURLException;


public class ItemController {

    @FXML
    private ImageView itemImg;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;
    @FXML
    private Label itemNumLabel;
    private final String chosenColor = "-fx-background-color: linear-gradient(to bottom, #a1c4fd 0%, #c2e9fb 100%);";
    private final String noChosenColor = "-fx-background-color: linear-gradient(to bottom, #f5f7fa 0%, #c3cfe2 100%);";
    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox itemVbox;
    private String nowColor = noChosenColor;//现在商品框的颜色，默认为灰，被选中为蓝色
    @FXML
    private Label itemUnit;
    private MyListener myListener;

    private Item item;

    @FXML
    public void click(javafx.scene.input.MouseEvent mouseEvent) throws MalformedURLException {
        myListener.onClickListener(item);

        if (item.isIfSelected()) {//被选中再点击就是取消选中
            item.setIfSelected(false);
            nowColor = noChosenColor;
        } else {
            item.setIfSelected(true);
            nowColor = chosenColor;
        }
        itemVbox.setStyle(nowColor);
        System.out.println(item.isIfSelected());
    }

    public void setdata(Item item, MyListener myListener) throws MalformedURLException {
        this.item = item;
        this.myListener = myListener;
        itemName.setText(item.getName());
        itemPrice.setText(home.CURRENCY + item.getPrice());
        Image image = new Image(item.getImgsrc());
        itemImg.setImage(image);
        itemNumLabel.setText(String.valueOf(item.getNum()));
        itemUnit.setText(item.getUnit());
    }

    public void setOriginColor() {
        itemVbox.setStyle(noChosenColor);
        nowColor = noChosenColor;
        item.setIfSelected(false);
        //itemVbox.setStyle(nowColor);
    }

    public void setChosenColor() throws MalformedURLException {
        itemVbox.setStyle(chosenColor);
        nowColor = chosenColor;
    }

    public void mouseExit(javafx.scene.input.MouseEvent mouseEvent) {
        if (nowColor.equals(noChosenColor))
            itemVbox.setStyle("-fx-background-color: linear-gradient(to bottom, #f5f7fa 0%, #c3cfe2 100%);");
    }

    public void mouseEnter(MouseEvent mouseEvent) {
        itemVbox.setStyle("-fx-background-color: linear-gradient(to bottom, #a1c4fd 0%, #c2e9fb 100%);");
    }
}
