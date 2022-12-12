package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.SQL;
import model.alertMessage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LogIn {

    private final alertMessage alert = new alertMessage();
    private final SQL mysql;
    @FXML
    private Button btlogIn;
    @FXML
    private Button btsignUp;
    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField usernameTextField;

    {
        try {
            mysql = new SQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logInClick(MouseEvent event) throws SQLException, IOException {
        String username = usernameTextField.getText();
        String password = passwordText.getText();
        if (username.isEmpty()) {
            alert.Error("用户名不能为空！");
        } else if (password.isEmpty()) {
            alert.Error("请输入密码！");
        } else {//若用户名与密码都输入，则检查账号有效性
            String passQuery = "select password from tb_user where name = " + "'" + username + "'";
            List list = mysql.query(passQuery);
            String pass = null;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Map map = (Map) it.next();
                pass = (String) map.get("password");
                System.out.println(pass);
            }
            if (pass == null)
                alert.Error("该用户不存在！");
            else if (password.equals(pass)) {
                alert.Info("登录成功！");
                mainshow(event);
            } else {
                alert.Error("密码错误！");
            }
        }
    }

    void mainshow(MouseEvent event) throws IOException {
        FXMLLoader signInPaneLoader = new FXMLLoader(getClass().getResource("/main/home.fxml"));
        Parent paneSignIn = signInPaneLoader.load();
        Scene sceneSignIn = new Scene(paneSignIn, 1800, 1000);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneSignIn);
        primaryStage.show();

    }

    @FXML
    void signUpClick(MouseEvent event) throws IOException {
        FXMLLoader signUpPaneLoader = new FXMLLoader();
        signUpPaneLoader.setLocation(getClass().getResource("/main/signUp.fxml"));
        Parent paneSignIn = signUpPaneLoader.load();
        Scene sceneSignIn = new Scene(paneSignIn, 450, 600);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneSignIn);
        primaryStage.show();

    }

}
