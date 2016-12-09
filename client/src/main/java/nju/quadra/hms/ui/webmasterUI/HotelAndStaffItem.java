package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.WebmasterController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by adn55 on 2016/11/30.
 */
class HotelAndStaffItem extends Parent {

    private WebmasterController controller;
    private HotelVO vo;
    private HotelAndStaffView parent;

    public HotelAndStaffItem(HotelAndStaffView parent, HotelVO vo, WebmasterController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstaffitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.parent = parent;
        this.vo = vo;
        this.controller = controller;
        if (vo != null) {
            labelName.setText(vo.name);
        }
    }

    @FXML
    private Label labelName;

    @FXML
    protected void onDetailAction() throws IOException {
        parent.loadView(new HotelAndStaffEditView(vo, controller, true, parent::onSearchAction));
    }

    @FXML
    protected void onModifyAction() throws IOException {
        parent.loadView(new HotelAndStaffEditView(vo, controller, false, parent::onSearchAction));
    }

    @FXML
    protected void onDeleteAction() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText(null);
        alert.setContentText("是否确认删除此酒店?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
            ResultMessage result = controller.deleteHotel(this.vo.id);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("删除酒店成功");
            } else {
                Dialogs.showError("删除酒店策略失败: " + result.message);
            }
            parent.onSearchAction();
        }
    }

}
