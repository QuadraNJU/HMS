package nju.quadra.hms.ui.hotelStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelPromotionItem extends Parent {

    private HotelStaffController controller;
    private HotelPromotionVO vo;
    private HotelPromotionView parent;

    @FXML
    private Label labelDate, labelName;

    public HotelPromotionItem(HotelPromotionView parent, HotelPromotionVO vo, HotelStaffController controller) throws IOException {
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
        parent.loadView(new HotelPromotionEditView(vo, controller, true));
    }

    @FXML
    protected void onModifyAction() throws IOException {
        parent.loadView(new HotelPromotionEditView(vo, controller, false));
    }

    @FXML
    protected void onDeleteAction() {

    }

}
