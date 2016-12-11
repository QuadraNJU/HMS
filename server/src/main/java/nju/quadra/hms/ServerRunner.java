package nju.quadra.hms;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nju.quadra.hms.net.HttpService;

/**
 * Created by adn55 on 2016/11/22.
 */
public class ServerRunner extends Application {

    public static void main(String[] args) {
        HttpService httpService = new HttpService(8081);
        try {
            httpService.start();
            launch(args);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        pane.setPadding(new Insets(50, 30, 50, 30));
        pane.getChildren().add(new Label("服务器端正在运行，关闭窗口停止"));
        primaryStage.setScene(new Scene(pane));
        primaryStage.setOnHidden(event -> System.exit(0));
        primaryStage.show();
    }

}
