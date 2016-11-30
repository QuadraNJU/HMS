package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelAndStaffView extends Parent {

    private HotelController controller = new HotelController();

    public HotelAndStaffView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstafflist.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadHotelAndStaffList();
    }

    @FXML
    private VBox vBox;

    private void loadHotelAndStaffList() throws IOException {
        ArrayList<HotelVO> hoteList = controller.getAll();
        if (hoteList != null && hoteList.size() > 0) {
            for (HotelVO vo : hoteList) {
                vBox.getChildren().add(new HotelAndStaffItem(vo));
            }
        }
    }

    @FXML
    public void onNewHotelAndStaffAction() throws Exception {
        //TODO
    }

    @FXML
    public void onModifyHotelAndStaffAction() throws Exception {
        //TODO
    }

    @FXML
    public void onDeleteHotelAndStaffAction() throws Exception {
        //TODO
    }

}
