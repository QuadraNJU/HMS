package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelSearchItem extends Parent {

    @FXML
    HotelSearchView parent;
    @FXML
    Label labelName, labelStates;
    @FXML
    Button btnDetail, btnOrder;
    int hotelId;

    public HotelSearchItem(HotelSearchView parent, HotelVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelsearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());
        this.parent = parent;
        hotelId = vo.id;
        if(vo.name != null) labelName.setText(vo.name);
    }

    @FXML
    public void onDetailAction() throws IOException{
        parent.loadView(new HotelDetailView(hotelId));
    }
}
