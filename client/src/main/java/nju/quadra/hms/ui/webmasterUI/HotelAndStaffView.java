package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelAndStaffView extends Parent {

    private ArrayList<HotelVO> hotelList;
    private ArrayList<AreaVO> areas;
    private HotelController controller = new HotelController();
    @FXML
    private TableView<HotelProperty> tableHotelInfo;
    @FXML
    Pane pane;
    @FXML
    ChoiceBox choiceCity, choiceArea;

    public HotelAndStaffView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstafflist.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        tableHotelInfo.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableHotelInfo.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));

        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(vo -> vo.cityName.equals(newValue)).map(vo -> vo.areaName).toArray());
        });

        loadAreas();
        loadHotelAndStaffList();
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

    @FXML
    private void loadHotelAndStaffList() throws IOException {
        tableHotelInfo.getItems().clear();
        int areaid = -1;
        for (AreaVO areaVO : areas) {
            if (areaVO.areaName.equals(choiceArea.getValue())) {
                areaid = areaVO.id;
                break;
            }
        }
        if(areaid != -1) {
            hotelList = controller.getAll();

            tableHotelInfo.getItems().clear();
            for (HotelVO vo : hotelList) {
                if(vo.areaId == areaid)
                    tableHotelInfo.getItems().add(new HotelProperty(vo));
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

    public class HotelProperty {
        private HotelVO vo;

        public HotelProperty(HotelVO vo) {
            this.vo = vo;
        }

        public int getId() {
            return vo.id;
        }

        public  String getName() {
            return vo.name;
        }
    }
}