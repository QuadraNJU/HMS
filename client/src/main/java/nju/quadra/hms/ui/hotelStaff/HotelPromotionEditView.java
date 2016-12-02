package nju.quadra.hms.ui.hotelStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by adn55 on 2016/12/2.
 */
public class HotelPromotionEditView extends Parent {

    private HotelStaffController controller;
    private HotelPromotionVO hotelPromotionVO;
    private SuccessHandler onSuccess;

    @FXML
    TextField editName, editPromotion;
    @FXML
    ChoiceBox<HotelPromotionType> choiceType;
    @FXML
    DatePicker dateStart, dateEnd;
    @FXML
    Button btnSave;

    public HotelPromotionEditView(HotelPromotionVO vo, HotelStaffController controller, boolean readOnly, SuccessHandler successHandler) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotionedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceType.getItems().addAll(HotelPromotionType.values());
        choiceType.getSelectionModel().select(0);

        this.hotelPromotionVO = vo;
        this.controller = controller;
        this.onSuccess = successHandler;
        if (vo != null) {
            editName.setText(vo.name);
            choiceType.setValue(vo.type);
            dateStart.setValue(vo.startTime);
            dateEnd.setValue(vo.endTime);
            editPromotion.setText(vo.promotion + "");
        }
        if (readOnly) {
            editName.setEditable(false);
            choiceType.setDisable(true);
            dateStart.setDisable(true);
            dateEnd.setDisable(true);
            editPromotion.setEditable(false);
            btnSave.setVisible(false);
        }
    }

    @FXML
    protected void onSaveAction() throws IOException {
        String name;
        HotelPromotionType type;
        LocalDate startTime;
        LocalDate endTime;
        double promotion;
        try {
            name = editName.getText();
            type = choiceType.getValue();
            startTime = dateStart.getValue();
            endTime = dateEnd.getValue();
            promotion = Double.parseDouble(editPromotion.getText());
        } catch (Exception e) {
            Dialogs.showError("数值格式不正确，请重新填写");
            return;
        }

        if (hotelPromotionVO == null) {
            // add
            ResultMessage result = controller.addHotelPromotion(new HotelPromotionVO(0, 0, name, type, startTime, endTime, promotion, null));
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("增加促销策略成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("增加促销策略失败: " + result.message);
            }
        } else {
            // modify
            hotelPromotionVO.name = name;
            hotelPromotionVO.type = type;
            hotelPromotionVO.startTime = startTime;
            hotelPromotionVO.endTime = endTime;
            hotelPromotionVO.promotion = promotion;
            ResultMessage result = controller.modifyHotelPromotion(hotelPromotionVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("修改促销策略成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("修改促销策略失败: " + result.message);
            }
        }
    }

    @FXML
    protected void onCancelAction() {
        getChildren().clear();
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }

}
