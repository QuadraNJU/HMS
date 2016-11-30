package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
public class ModifyHotelInfoView extends Parent {

    private HotelController controller = new HotelController();

    @FXML
    private TextField fieldHotelName, fieldFacilities, fieldHotelstaff;
    private ComboBox comboProvince, comboCity, comboArea, comboStar;
    private TextArea areaIntro;


    public ModifyHotelInfoView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyhotelinfo.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());
    }

    public void onAgreeAction() {
        //TODO
    }

    public void onChooseUserAction() {
        //TODO
    }

    public void onCancelAction() {
        //TODO
    }
}
