package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelSearchView extends Parent {
    private HotelController controller = new HotelController();

    public HotelSearchView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelsearch.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadHotelList();
    }

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }


    @FXML
    private VBox vBox;
    @FXML
    private Pane pane;

    private void loadHotelList() throws IOException {
        ArrayList<HotelVO> hotelList = controller.getAll();
        if (hotelList != null && hotelList.size() > 0) {
            for (HotelVO vo : hotelList) {
                vBox.getChildren().add(new HotelSearchItem(this, vo));
            }
        }
    }
}
