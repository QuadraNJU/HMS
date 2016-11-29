package nju.quadra.hms.ui.loginUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.controller.AuthController;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.mainUI.MainView;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/24.
 */
public class LoginView extends Stage {

    private Scene scene;
    private AuthController controller = new AuthController();

    public LoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        scene = new Scene(root);
        this.setScene(scene);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
    }

    @FXML
    TextField textUsername, textPassword;

    /**
     * 处理登录按钮事件
     */
    @FXML
    protected void onLoginAction() throws IOException {
        String username = textUsername.getText().trim();
        String password = textPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Dialogs.showError("请输入用户名和密码！");
            return;
        }

        ResultMessage result = controller.login(username, password);
        if (result.result == ResultMessage.RESULT_SUCCESS) {
            MainView main = new MainView();
            main.show();
            this.close();
        } else {
            Dialogs.showError("登录失败：" + result.message);
        }
    }

    /**
     * 处理新用户注册链接事件
     */
    @FXML
    protected void onRegisterAction() throws Exception {
        RegisterView view = new RegisterView();
        view.show();
    }

    /**
     * 处理网络设置链接事件
     */
    @FXML
    protected void onNetworkSettingsAction() throws Exception {
        NetworkSettingsView view = new NetworkSettingsView();
        view.show();
    }

}
