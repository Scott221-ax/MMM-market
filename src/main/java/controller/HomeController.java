package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.home;
import model.Item;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ImageView chosenCardImgView;

    @FXML
    private Button btAccount;

    @FXML
    private Button btCart;

    @FXML
    private VBox chosenItemCard;

    @FXML
    private Label chosenCardName;

    @FXML
    private Label chosenCardPrice;

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;


    private List<Item> items = new ArrayList<>();
    private Image chosenCardImage;

    private List<Item> getdata() {
        //此处从数据库中获得item_list
        List<Item> items = new ArrayList<>();
        Item item;
        for (int i = 0; i < 2; i++) {
            item = new Item();
            item.setUnit("kg");
            item.setNum(100);
            item.setName("hhh");
            item.setPrice((float) 1.33);
            item.setImgsrc("/image/orangeT.png");
            items.add(item);
        }
        return items;
    }

    private void setChosenItemCard(Item item) {
        chosenCardName.setText(item.getName());
        chosenCardPrice.setText(home.CURRENCY + item.getPrice());
        chosenCardImage = new Image(getClass().getResourceAsStream(item.getImgsrc()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //home.mySQL.test();
        items.addAll(getdata());
        if (items.size() > 0) {
            setChosenItemCard(items.get(0));
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/main/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setdata(items.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}




































