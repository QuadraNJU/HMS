package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.WebmasterController;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelAndStaffView extends Parent {

    private WebmasterController controller = new WebmasterController();
    private ArrayList<AreaVO> areas;

    public HotelAndStaffView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstafflist.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(vo -> vo.cityName.equals(newValue)).map(vo -> vo.areaName).toArray());
        });

        loadAreas();
    }

    @FXML
    private Pane pane;
    @FXML
    private VBox vBox;
    @FXML
    private ChoiceBox choiceCity, choiceArea;

    private void loadAreas() {
        areas = controller.getAllArea();
        if (areas != null) {
            for (AreaVO vo : areas) {
                if (choiceCity.getItems().indexOf(vo.cityName) < 0) {
                    choiceCity.getItems().add(vo.cityName);
                }
            }
        }
    }

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }

    @FXML
    protected void onSearchAction() throws IOException {
        for (AreaVO areaVO : areas) {
            if (areaVO.areaName.equals(choiceArea.getValue()) && areaVO.cityName.equals(choiceCity.getValue())) {
                ArrayList<HotelVO> hotelList = controller.getHotelsByArea(areaVO.id);
                vBox.getChildren().clear();
                if (hotelList != null) {
                    for (HotelVO vo : hotelList) {
                        vBox.getChildren().add(new HotelAndStaffItem(this, vo, controller));
                    }
                }
                break;
            }
        }
    }

    @FXML
    protected void onAddAction() throws IOException {
        loadView(new HotelAndStaffEditView(null, controller, false, this::onSearchAction));
    }

}
