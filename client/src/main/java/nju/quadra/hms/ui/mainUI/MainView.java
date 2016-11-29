package nju.quadra.hms.ui.mainUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.customerUI.CustomerNavigation;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/25.
 */
public class MainView extends Stage {

    private Scene scene;

    @FXML
    private Label labelUsername, labelUserType;
    @FXML
    private Pane navPane;

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
            switch (HttpClient.session.userType) {
                case CUSTOMER:
                    navPane.getChildren().add(new CustomerNavigation());
                    break;
            }
        }
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

}
