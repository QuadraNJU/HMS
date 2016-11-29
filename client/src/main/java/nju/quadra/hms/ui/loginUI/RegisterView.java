package nju.quadra.hms.ui.loginUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.controller.AuthController;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.net.PassHash;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 2016/11/24.
 */
public class RegisterView extends Stage {

    private Scene scene;
    private AuthController controller = new AuthController();

    public RegisterView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        scene = new Scene(root);
        this.setScene(scene);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    @FXML
    TextField textUsername, textPassword, textPassword2, textContact, textName;

    /**
     * 处理注册按钮事件
     */
    @FXML
    protected void onRegisterAction() {
        String username = textUsername.getText(),
                password = textPassword.getText(),
                password2 = textPassword2.getText(),
                contact = textContact.getText(),
                name = textName.getText();
        if (username.isEmpty() || password.isEmpty() || contact.isEmpty() || name.isEmpty()) {
            Dialogs.showError("请输入完整的用户信息");
            return;
        }
        if (password.length() < 6) {
            Dialogs.showError("密码长度太短，请重新输入");
            textPassword.clear();
            textPassword2.clear();
            return;
        }
        if (!password.equals(password2)) {
            Dialogs.showError("两次输入的密码不相同，请重新输入");
            return;
        }

        String encryptedPassword = PassHash.hash(password);
        ResultMessage result = controller.register(new UserVO(username, encryptedPassword, name, contact));
        if (result.result == ResultMessage.RESULT_SUCCESS) {
            Dialogs.showInfo("注册成功");
            this.close();
        } else {
            Dialogs.showError("注册失败：" + result.message);
        }
    }

    /**
     * 处理注册界面返回事件
     */
    @FXML
    protected void onExitAction() throws Exception {
        this.close();
    }

}
