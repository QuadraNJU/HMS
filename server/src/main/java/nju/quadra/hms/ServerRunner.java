package nju.quadra.hms;

import javafx.application.Application;
import javafx.stage.Stage;
import nju.quadra.hms.ui.serverUI.ServerMainView;

public class ServerRunner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new ServerMainView();
        primaryStage.show();
    }

}
