package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.home;
import model.Item;

import java.util.Objects;


public class ItemController {

    @FXML
    private ImageView itemImg;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;
    @FXML
    private Label itemNumLabel;


    @FXML
    private Label itemUnit;

    private Item item;

    public void setdata(Item item) {
        this.item = item;
        itemName.setText(item.getName());
        itemPrice.setText(home.CURRENCY + item.getPrice());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgsrc())));
        itemImg.setImage(image);
        itemNumLabel.setText(String.valueOf(item.getNum()));
        itemUnit.setText(item.getUnit());
    }


}
