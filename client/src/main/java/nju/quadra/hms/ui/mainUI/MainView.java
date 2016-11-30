package nju.quadra.hms.ui.mainUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.ClientRunner;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.customerUI.CustomerNavigation;
import nju.quadra.hms.ui.hotelStaff.HotelStaffNavigation;
import nju.quadra.hms.ui.loginUI.LoginView;
import nju.quadra.hms.ui.webmasterUI.WebmasterNavigation;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/25.
 */
public class MainView extends Stage {

    private Scene scene;

    @FXML
    private Label labelUsername, labelUserType;
    @FXML
    private Pane navPane, contentPane;
    @FXML
    private Button btnLotout;

    public MainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        scene = new Scene(root);
        this.setScene(scene);
        this.setResizable(false);
        this.initStyle(StageStyle.UNDECORATED);

        if (HttpClient.session != null) {
            labelUsername.setText(HttpClient.session.username);
            labelUserType.setText(HttpClient.session.userType.toString());
            navPane.getChildren().clear();
            switch (HttpClient.session.userType) {
                case CUSTOMER:
                    navPane.getChildren().add(new CustomerNavigation(this));
                    break;
                case WEBSITE_MASTER:
                    navPane.getChildren().add(new WebmasterNavigation(this));
                case HOTEL_STAFF:
                    navPane.getChildren().add(new HotelStaffNavigation(this));
            }
        }
    }

    public void loadView(Node node) {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(node);
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

    @FXML
    protected void onLogoutAction() throws Exception {
        Dialogs.showInfo("登出成功！");
        String username = HttpClient.session.username;
        LoginView loginView = new LoginView(username);
        loginView.show();
        HttpClient.session = null;
        this.close();
    }

}
