package nju.quadra.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import nju.quadra.hms.ui.loginUI.LoginView;
import nju.quadra.hms.util.ClientConfigUtil;

/**
 * Created by adn55 on 2016/11/24.
 */
public class ClientRunner extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // try to read client config file
        ClientConfigUtil.loadFromFile();
        // open Login view
        LoginView loginView = new LoginView();
        loginView.show();
    }
}
