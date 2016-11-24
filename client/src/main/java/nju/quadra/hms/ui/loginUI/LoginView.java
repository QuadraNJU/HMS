package nju.quadra.hms.ui.loginUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.ui.common.Dialogs;

import java.net.URL;

/**
 * Created by adn55 on 2016/11/24.
 */
public class LoginView extends Stage {

    public LoginView() throws Exception {
        URL test = getClass().getResource(".");
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
        Dialogs.showError(new UserController().login(textUsername.getText(), textPassword.getText()).message);
    }

}
