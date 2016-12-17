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
class HotelAndStaffView extends Parent {

    private final WebmasterController controller = new WebmasterController();
    private ArrayList<AreaVO> areas;

    public HotelAndStaffView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstafflist.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(vo -> vo.cityName.equals(newValue)).map(vo -> vo.areaName).toArray());
            choiceArea.getSelectionModel().select(0);
        });
        choiceCity.getSelectionModel().select(0);

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
    void onSearchAction() throws IOException {
        AreaVO area = areas.stream().filter(vo -> vo.areaName.equals(choiceArea.getValue()) && vo.cityName.equals(choiceCity.getValue())).findFirst().get();
        ArrayList<HotelVO> hotelList = controller.getHotelsByArea(area.id);
        vBox.getChildren().clear();
        for (HotelVO vo : hotelList) {
            vBox.getChildren().add(new HotelAndStaffItem(this, vo, controller));
        }
    }

    @FXML
    protected void onAddAction() throws IOException {
        loadView(new HotelAndStaffEditView(null, controller, false, this::onSearchAction));
    }

}
