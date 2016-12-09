package nju.quadra.hms.ui.webMarketerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.WebMarketerController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Created by Rebecca on 2016/12/5.
 */
class WebsitePromotionItem extends Parent {

    private WebMarketerController controller;
    private WebsitePromotionVO vo;
    private WebsitePromotionView parent;

    @FXML
    private Label labelDate, labelName;

    public WebsitePromotionItem(WebsitePromotionView parent, WebsitePromotionVO vo, WebMarketerController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotionitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.parent = parent;
        this.vo = vo;
        this.controller = controller;
        if (vo != null) {
            labelDate.setText(vo.startTime.format(DateTimeFormatter.ofPattern("uuuu/MM/dd")) + " - " + vo.endTime.format(DateTimeFormatter.ofPattern("uuuu/MM/dd")));
            labelName.setText(vo.name);
        }
    }

    @FXML
    protected void onDetailAction() throws IOException {
        parent.loadView(new WebsitePromotionEditView(vo, controller, true, parent::loadPromotion));
    }

    @FXML
    protected void onModifyAction() throws IOException {
        parent.loadView(new WebsitePromotionEditView(vo, controller, false, parent::loadPromotion));
    }

    @FXML
    protected void onDeleteAction() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText(null);
        alert.setContentText("是否确认删除此促销策略?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
            ResultMessage result = controller.deleteWebsitePromotion(this.vo.id);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("删除促销策略成功");
            } else {
                Dialogs.showError("删除促销策略失败: " + result.message);
            }
            parent.loadPromotion();
        }
    }

}
