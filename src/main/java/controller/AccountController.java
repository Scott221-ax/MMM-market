package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.adPane;
import model.alertMessage;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    private final adPane ad = new adPane();
    @FXML
    private Button btAccount;
    @FXML
    private Button btCart;
    @FXML
    private Button btCheckOut;
    @FXML
    private ImageView chosenCardImgView;
    @FXML
    private Label chosenCardName;
    @FXML
    private Label chosenCardPrice;
    @FXML
    private VBox chosenItemCard;
    @FXML
    private HBox hboxTips;
    @FXML
    private HBox hboxtest;
    @FXML
    private SearchableComboBox<?> searchComboBox;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private VBox vboxRight;
    private alertMessage alert;
    private Scene scene;

    @FXML
    public void btCartClick(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main/cart.fxml"));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene secondScene1 = new Scene(root, 1800, 1000);
        new Thread(() -> {
            Platform.runLater(() -> primaryStage.setScene(secondScene1));
        }).start();


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void searchKeyPressed(KeyEvent keyEvent) {
    }

    public void btAccountClick(MouseEvent mouseEvent) {
    }
}
