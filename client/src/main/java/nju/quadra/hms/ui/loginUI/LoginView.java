package nju.quadra.hms.ui.loginUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;

import java.net.URL;

/**
 * Created by adn55 on 2016/11/24.
 */
public class LoginView extends Stage {

    private UserController controller = new UserController();

    public LoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        this.setScene(new Scene(root));
        this.setResizable(false);
    }

    @FXML
    TextField textUsername, textPassword;

    @FXML
    protected void onLoginAction() {
        String username = textUsername.getText().trim();
        String password = textPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Dialogs.showError("请输入用户名和密码！");
            return;
        }

        ResultMessage result = controller.login(username, password);
        if (result.result == ResultMessage.RESULT_SUCCESS) {
            Dialogs.showInfo("登录成功");
        } else {
            Dialogs.showError("登录失败：" + result.message);
        }
    }

}
