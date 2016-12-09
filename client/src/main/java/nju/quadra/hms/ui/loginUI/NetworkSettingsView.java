package nju.quadra.hms.ui.loginUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.util.ClientConfigUtil;

/**
 * Created by adn55 on 2016/11/25.
 */
class NetworkSettingsView extends Stage {

    private Scene scene;

    public NetworkSettingsView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("networksettings.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("网络设置");
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);

        textServer.setText(ClientConfigUtil.getConfig().getServerHost());
    }

    @FXML
    private
    TextField textServer;

    /**
     * 处理保存按钮事件
     */
    @FXML
    protected void onSaveAction() {
        String server = textServer.getText().trim();
        ClientConfigUtil.getConfig().setServerHost(server);
        ClientConfigUtil.saveToFile();
        this.close();
    }

    /**
     * 处理取消按钮事件
     */
    @FXML
    protected void onCancelAction() {
        this.close();
    }

}
