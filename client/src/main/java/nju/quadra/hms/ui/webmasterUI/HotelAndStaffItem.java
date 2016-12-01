package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelAndStaffItem extends Parent {

    @FXML
    Label labelName;

    public HotelAndStaffItem(HotelVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstaffitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());
        labelName.setText(vo.name);
    }


    @FXML
    public void onHotelDetailAction() {
        //TODO
    }
}
