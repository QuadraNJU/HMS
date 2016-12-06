package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelSearchVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelSearchView extends Parent {

    private CustomerController controller = new CustomerController();
    private ArrayList<AreaVO> areas;
    private ArrayList<HotelSearchVO> hotelList;

    public HotelSearchView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelsearch.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(vo -> vo.cityName.equals(newValue)).map(vo -> vo.areaName).toArray());
        });

        loadAreas();
    }

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
    private VBox vBox;
    @FXML
    private Pane pane;
    @FXML
    private ChoiceBox choiceCity, choiceArea;

    @FXML
    private void onSearchAction() throws IOException {
        for (AreaVO areaVO : areas) {
            if (areaVO.areaName.equals(choiceArea.getValue()) && areaVO.cityName.equals(choiceCity.getValue())) {
                hotelList = controller.searchHotel(areaVO.id, HttpClient.session.username);
                vBox.getChildren().clear();
                if (hotelList != null) {
                    for (HotelSearchVO vo : hotelList) {
                        vBox.getChildren().add(new HotelSearchItem(this, vo));
                    }
                }
                break;
            }
        }
    }

}
