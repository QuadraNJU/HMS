package nju.quadra.hms.ui.loginUI;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.controller.AuthController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.mainUI.MainView;
import nju.quadra.hms.util.ClientConfig;
import nju.quadra.hms.util.PassHash;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/24.
 */
public class LoginView extends Stage {

    private final AuthController controller = new AuthController();
    @FXML
    private TextField textUsername, textPassword;
    @FXML
    private CheckBox checkRemember;
    @FXML
    private Pane paneLoading;

    public LoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
    }

    public LoginView(String username) throws Exception {
        this();
        textUsername.setText(username);
    }

    public void showAndTryAutoLogin() throws IOException {
        this.show();
        tryAutoLogin();
    }

    private void tryAutoLogin() throws IOException {
        String username = ClientConfig.getConfig().getUsername();
        String password = ClientConfig.getConfig().getPassword();
        if (!username.isEmpty() && !password.isEmpty()) {
            doLogin(username, password);
        }
    }

    private void doLogin(String username, String password) throws IOException {
        paneLoading.setVisible(true);
        final ResultMessage[] result = new ResultMessage[1];
        Task loginTask = new Task() {
            @Override
            protected Object call() throws Exception {
                result[0] = controller.login(username, password);
                return null;
            }
        };
        loginTask.setOnSucceeded(event -> {
            if (result[0].result == ResultMessage.RESULT_SUCCESS) {
                if (checkRemember.isSelected()) {
                    ClientConfig.getConfig().setUsername(username);
                    ClientConfig.getConfig().setPassword(password);
                } else {
                    ClientConfig.getConfig().setUsername("");
                    ClientConfig.getConfig().setPassword("");
                }
                try {
                    MainView main = new MainView();
                    main.show();
                    this.close();
                } catch (IOException e) {
                    Dialogs.showError("登录失败: 发生系统内部错误");
                }
            } else {
                Dialogs.showError("登录失败: " + result[0].message);
            }
            paneLoading.setVisible(false);
        });
        new Thread(loginTask).start();
    }

    /**
     * 提供窗口拖放支持
     */
    private double offsetX, offsetY;

    @FXML
    protected void onDragStart(MouseEvent event) {
        this.offsetX = event.getSceneX();
        this.offsetY = event.getSceneY();
    }

    @FXML
    protected void onDrag(MouseEvent event) {
        this.setX(event.getScreenX() - this.offsetX);
        this.setY(event.getScreenY() - this.offsetY);
    }


    /**
     * 处理登录按钮事件
     */
    @FXML
    private void onLoginAction() throws IOException {
        String username = textUsername.getText().trim();
        String password = textPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Dialogs.showError("请输入用户名和密码！");
            return;
        }
        doLogin(username, PassHash.hash(password));
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

    @FXML
    protected void onEnterTypedAction(KeyEvent e) throws Exception {
        if(e.getCode().equals(KeyCode.ENTER))
            onLoginAction();
    }

    @FXML
    protected void onExitAction() {
        System.exit(0);
    }

}
