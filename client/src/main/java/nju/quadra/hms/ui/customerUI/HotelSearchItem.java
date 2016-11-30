package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelSearchItem extends Parent {

    @FXML
    Label labelName, labelStates;
    @FXML
    Button btnDetail, btnOrder;

    public HotelSearchItem(HotelVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelsearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        if(vo.name != null) labelName.setText(vo.name);
    }
}
