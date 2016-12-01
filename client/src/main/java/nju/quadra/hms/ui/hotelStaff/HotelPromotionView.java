package nju.quadra.hms.ui.hotelStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelPromotionVO;
import nju.quadra.hms.vo.HotelRoomVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by adn55 on 2016/12/1.
 */
public class HotelPromotionView extends Parent {

    private HotelStaffController controller = new HotelStaffController(HttpClient.session.username);

    public HotelPromotionView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotion.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadPromotion();
    }

    @FXML
    private Pane pane;
    @FXML
    private VBox vBox;

    private void loadPromotion() {
    }

    @FXML
    protected void onAddAction() throws IOException {
    }

    @FXML
    protected void onModifyAction() throws IOException {
    }

    @FXML
    protected void onDeleteAction() {
    }

}