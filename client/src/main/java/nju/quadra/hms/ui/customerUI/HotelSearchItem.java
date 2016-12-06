package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.HotelSearchVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelSearchItem extends Parent {

    private HotelSearchView parent;
    private HotelSearchVO vo;

    @FXML
    Label labelName, labelStates;
    @FXML
    Button btnDetail, btnOrder;

    public HotelSearchItem(HotelSearchView parent, HotelSearchVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelsearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.parent = parent;
        this.vo = vo;
        if (vo != null) {
            labelName.setText(vo.name);
        }
    }

    @FXML
    public void onDetailAction() throws IOException {
        parent.loadView(new HotelDetailView(parent, vo));
    }

    @FXML
    public void onOrderAction() throws IOException {
        parent.loadView(new BookHotelView(vo.id));
    }
}
