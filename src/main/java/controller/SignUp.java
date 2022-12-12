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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SignUp {

    private final alertMessage alert = new alertMessage();
    private final SQL mySql;
    @FXML
    private Button btback;
    @FXML
    private Button btlogIn;
    @FXML
    private PasswordField passwordText;
    @FXML
    private PasswordField repeatPasswordText;
    @FXML
    private TextField usernameTextField;

    {
        try {
            mySql = new SQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backClick(MouseEvent event) throws IOException {
        FXMLLoader signInPaneLoader = new FXMLLoader(getClass().getResource("/main/logIn.fxml"));
        Parent paneSignIn = signInPaneLoader.load();
        Scene sceneSignIn = new Scene(paneSignIn, 450, 600);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneSignIn);
        primaryStage.show();

    }

    @FXML
    void signUpClick(MouseEvent event) throws SQLException {
        //先要检查当前用户名是否已注册
        String username = usernameTextField.getText();
        String password = passwordText.getText();
        String repeatPassword = repeatPasswordText.getText();
        if (username.isEmpty()) {
            alert.Error("用户名不能为空！");
        } else if (!ifNameValid(username)) { // 查询当前用户名是否已被注册
            alert.Error("该用户名已被注册！");
        } else if (password.isEmpty()) {
            alert.Error("密码不能为空！");
        } else if (!password.equals(repeatPassword)) {
            alert.Error("密码不一致！");
        } else {
            String ifRepeatNameQuery = "select name from tb_user where name = " + "'" + username + "'";
            List list = mySql.query(ifRepeatNameQuery);
            String result = null;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Map map = (Map) it.next();
                result = (String) map.get("password");
            }
            if (result != null) {
                alert.Error("该用户名已存在！");
            } else {//当前用户名有效，插入数据库中
                //TODO
                //获取当前时间，要插入数据库中
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                Date now = new Date();
                String time = sdf.format(now);
                boolean ifSuccessInsert = mySql.insertUserInfo(username, password);
                if (ifSuccessInsert) {
                    alert.Info("注册成功，请返回登录");
                } else {
                    alert.Error("注册失败");
                }

            }
        }
    }

    private boolean ifNameValid(String name) throws SQLException {
        String ifNameUsedQuery = "select name from tb_user where name = " + "'" + name + "' ;";
        List list = mySql.query(ifNameUsedQuery);
        return list.isEmpty();
    }
}
