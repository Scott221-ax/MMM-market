
package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MyListener;
import main.home;
import model.Item;
import model.SQL;
import model.alertMessage;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    private final List<Item> itemCheckOutList = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    @FXML
    private Button btAccount;
    @FXML
    private Button btCart;
    @FXML
    private ImageView chosenCardImgView;
    @FXML
    private Label chosenCardName;
    @FXML
    private Label chosenCardPrice;
    @FXML
    private VBox chosenItemCard;
    @FXML
    private HBox hboxContainScroll;
    @FXML
    private HBox hboxTips;
    @FXML
    private ScrollPane scroll;
    @FXML
    private SearchableComboBox<?> searchComboBox;
    @FXML
    private TilePane tilePane;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private VBox vboxRight;
    private MyListener myListener;
    private float totalPrice = 0;

    @FXML
    void searchKeyPressed(KeyEvent event) {

    }

    @FXML
    void iconClick(MouseEvent event) throws IOException {
        FXMLLoader signInPaneLoader = new FXMLLoader(getClass().getResource("/main/home.fxml"));
        Parent paneSignIn = signInPaneLoader.load();
        Scene sceneSignIn = new Scene(paneSignIn, 1800, 1000);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneSignIn);
        primaryStage.show();
    }

    @FXML
    void checkOutClick(MouseEvent event) {
        alertMessage alert = new alertMessage();
        if (totalPrice == 0) alert.Warning("You don't select any items !");
        else {
            if (alert.Confirmation("Are you sure to pal for these?"))
                alert.Info("Congratulations! Items will be shipped to your address soon.");
        }
    }

    @FXML
    void btAccountClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main/Account.fxml"));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene secondScene1 = new Scene(root, 1800, 1000);
        primaryStage.setScene(secondScene1);
    }

    private List<Item> getdata() throws SQLException {
        //TODO 此处从数据库中获得item_list

        SQL mysql = new SQL();
        mysql.setTable("tb_cart");
        List<Item> items = mysql.itemsInitialize();
        return items;
    }

    //TODO 现在解决问题：购物车中的物品不能重复选择
    private void setChosenItemCard(Item item) {
        //TODO
        // 需要判断是否重复加入
        //
        if (item.isIfSelected()) {
            totalPrice -= item.getPrice();
            itemCheckOutList.remove(item);
        } else {
            itemCheckOutList.add(item);
            totalPrice += item.getPrice();
        }
        totalPriceLabel.setText(home.CURRENCY + String.format("%.2f", totalPrice));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //先从数据库中获得购物车数据，再加载
        try {
            items.addAll(getdata());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (items.size() > 0) {
            myListener = new MyListener() {
                @Override
                public void onClickListener(Item item) throws MalformedURLException {
                    setChosenItemCard(item);
                }

            };
        }
        for (int i = 0; i < items.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/main/itemCart.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setdata(items.get(i), myListener);

                tilePane.getChildren().add(anchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

