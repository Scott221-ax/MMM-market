package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
import java.util.*;

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
    @FXML


    private final List<Item> items = new ArrayList<>();
    private final List<Item> cartItemList = new ArrayList<>();//用来存放购物车中的物品
    @FXML
    private ComboBox<Integer> chosenCombox;
    @FXML
    private HBox hboxContainScroll;
    @FXML
    private VBox vboxRight;
    @FXML
    private HBox hboxTips;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private TilePane tilePane;//瓦片面板，用来陈列商品
    private Image chosenCardImage;
    @FXML
    private SearchableComboBox<String> searchComboBox;
    private MyListener myListener;
    private Map<String, ItemController> itemControllerMap;
    private ItemController lastItemController;
    private Map<String, Integer> searchComboBoxMap;//用来搜搜索框对应的,item_name与该item在list中的索引对应
    private Item serachItem;
    @FXML
    private Button btAddToCart;
    private Item chosenItem;
    private alertMessage alert;

    private List<Item> getdata() throws SQLException, MalformedURLException {
        //TODO 此处从数据库中获得item_list
        SQL mysql = new SQL();
        mysql.setTable("tb_items");
        List<Item> items = mysql.itemsInitialize();
        return items;
    }

    @FXML
    void btAddToCartClick(MouseEvent event) {//加入购物车按钮
        alert = new alertMessage();
        int chosenNum = 1;
        if (chosenCombox.getSelectionModel().getSelectedItem() == null) {

            alert.Error("Please confirm the amount of item which you have selected");
        } else {
            chosenNum = chosenCombox.getSelectionModel().getSelectedItem();
            if (alert.Confirmation("Would you like add this item to your cart?")) {
                System.out.println(chosenNum);
                System.out.println(chosenItem.getName());
                chosenItem.setChosenNum(chosenNum);
                cartItemList.add(chosenItem);//加入购物车列表
            }
        }
    }

    @FXML
    void btCartClick(MouseEvent event) throws IOException, SQLException {
        //TODO 此时要将购物车列表中的商品写入数据库
        if (!cartItemList.isEmpty()) {
            SQL mySql = new SQL();
            mySql.setTable("tb_cart");//当前要对购物车tb_cart这个表进行操作
            mySql.addItemsToCartSql(cartItemList);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main/cart.fxml"));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene secondScene1 = new Scene(root, 1800, 1000);
        Platform.runLater(() -> primaryStage.setScene(secondScene1));

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

    private void setChosenItemCard(Item item) throws MalformedURLException {
        chosenItem = item;
        if (lastItemController != null && lastItemController != itemControllerMap.get(item.getName())) {
            lastItemController.setOriginColor();//上一个变灰
            lastItemController = itemControllerMap.get(item.getName());//更新
        }

        chosenCardName.setText(item.getName());
        chosenCardPrice.setText(home.CURRENCY + item.getPrice());
        chosenCardImage = new Image(item.getImgsrc());
        chosenCardImgView.setImage(chosenCardImage);

        chosenCombox.getItems().clear();
        for (int i = 1; i <= item.getNum(); i++) {
            chosenCombox.getItems().add(i);
        }

    }

    private void setSearchComboBox(List<Item> list_item) {
        searchComboBox.getItems().clear();
        searchComboBoxMap = new HashMap<>(items.size());
        for (int i = 0; i < items.size(); i++) {
            searchComboBox.getItems().add(items.get(i).getName());//根据要求需要搜索框展示几条结果
            searchComboBoxMap.put(items.get(i).getName(), i);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            items.addAll(getdata());//从数据库中获得商品
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if (items.size() > 0) {
            setSearchComboBox(items);//设置搜索框候选值

            try {
                setChosenItemCard(items.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Item item) throws MalformedURLException {
                        setChosenItemCard(item);
                    }
                };
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        itemControllerMap = new HashMap<>(items.size());
        try {

            for (int i = 0; i < items.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/main/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setdata(items.get(i), myListener);
                itemControllerMap.put(items.get(i).getName(), itemController);
                tilePane.getChildren().add(anchorPane);
            }
            lastItemController = itemControllerMap.get(items.get(0).getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void searchKeyPressed(KeyEvent keyEvent) throws MalformedURLException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String s = searchComboBox.getSelectionModel().getSelectedItem();//搜索框中选中的商品
            searchComboBox.setPromptText(s);//将该商品名写在搜索框上
            serachItem = items.get(searchComboBoxMap.get(s));//获取相对应的item
            setChosenItemCard(serachItem);//左边chosenCard相应的进行设置
            //对当前商品的小框进行颜色改变，setChosenItemCard函数会将当前controller重新赋值
            //上一个被选中的颜色变灰
            lastItemController.setChosenColor();//搜索的物品变蓝
            System.out.println("llll");
        }

    }
}




































