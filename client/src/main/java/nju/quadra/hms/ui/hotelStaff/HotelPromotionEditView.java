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
import nju.quadra.hms.vo.HotelPromotionVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/12/2.
 */
public class HotelPromotionEditView extends Parent {

    private HotelStaffController controller;
    private HotelPromotionVO hotelPromotionVO;

    @FXML
    TextField editName, editPromotion;
    @FXML
    ChoiceBox choiceType;
    @FXML
    DatePicker dateStart, dateEnd;
    @FXML
    Button btnSave;

    public HotelPromotionEditView(HotelPromotionVO vo, HotelStaffController controller, boolean readOnly) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotionedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceType.getItems().addAll(HotelPromotionType.values());

        this.hotelPromotionVO = vo;
        this.controller = controller;
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
    protected void onSaveAction() {}

    @FXML
    protected void onCancelAction() {
        getChildren().clear();
    }

}
