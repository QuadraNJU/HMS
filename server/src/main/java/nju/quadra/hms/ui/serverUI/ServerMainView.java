package nju.quadra.hms.ui.serverUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import nju.quadra.hms.data.mysql.MySQLManager;
import nju.quadra.hms.net.HttpService;
import nju.quadra.hms.util.Logger;
import nju.quadra.hms.util.ServerConfig;

import java.io.IOException;

/**
 * Created by adn55 on 2016/12/20.
 */
public class ServerMainView extends Stage {

    private HttpService httpService;

    @FXML
    private TextField editHost, editDb, editUser, editPass, editPort;
    @FXML
    private ToggleButton btnStartStop;
    @FXML
    private TextArea textLogs;

    public ServerMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("servermain.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("HMS Server");

        this.setOnCloseRequest(event -> {
            System.exit(0);
        });

        Logger.init(textLogs.textProperty());
        ServerConfig config = ServerConfig.getConfig();
        editHost.setText(config.getDbHost());
        editDb.setText(config.getDbName());
        editUser.setText(config.getDbUser());
        editPass.setText(config.getDbPass());
        editPort.setText(config.getPort() + "");
        btnStartStop.setOnAction(event -> {
            if (btnStartStop.isSelected()) {
                // connect db
                config.setDbHost(editHost.getText());
                config.setDbName(editDb.getText());
                config.setDbUser(editUser.getText());
                config.setDbPass(editPass.getText());
                // start
                try {
                    MySQLManager.getConnection();
                    config.setPort(Integer.parseInt(editPort.getText()));
                    httpService = new HttpService(config.getPort());
                    httpService.start();
                    Logger.log("I", "HTTP service started on port " + config.getPort());
                } catch (Exception e) {
                    Logger.log(e);
                    btnStartStop.setSelected(false);
                }
            } else {
                // stop
                httpService.stop();
                Logger.log("I", "HTTP service stopped");
            }
        });
    }

}
