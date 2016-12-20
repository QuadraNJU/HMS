package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nju.quadra.hms.controller.WebmasterController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.util.ArrayList;

class HotelAndStaffEditView extends Parent {

    private WebmasterController controller;
    private ArrayList<AreaVO> areas;
    private HotelVO hotelVO;
    private SuccessHandler onSuccess;

    public HotelAndStaffEditView(HotelVO vo, WebmasterController controller, boolean readOnly, SuccessHandler successHandler) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstaffedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceStar.getItems().addAll(new String[]{"一星级", "二星级", "三星级", "四星级", "五星级"});
        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(areaVO -> areaVO.cityName.equals(newValue)).map(areaVO -> areaVO.areaName).toArray());
        });

        this.hotelVO = vo;
        this.controller = controller;
        this.onSuccess = successHandler;
        loadAreas();
        if (vo != null) {
            editName.setText(hotelVO.name);
            editAddress.setText(hotelVO.address);
            textDescription.setText(hotelVO.description);
            editFacilities.setText(hotelVO.facilities);
            choiceStar.setValue(hotelVO.star);
            editStaff.setText(hotelVO.staff);
            for (AreaVO areaVO : areas) {
                if (areaVO.id == hotelVO.areaId) {
                    choiceCity.setValue(areaVO.cityName);
                    choiceArea.setValue(areaVO.areaName);
                    break;
                }
            }
        }
        if (readOnly) {
            editName.setEditable(false);
            editAddress.setEditable(false);
            editFacilities.setEditable(false);
            editStaff.setEditable(false);
            textDescription.setEditable(false);
            choiceCity.setDisable(true);
            choiceArea.setDisable(true);
            choiceStar.setDisable(true);
            btnSave.setVisible(false);
        }
    }

    @FXML
    private TextField editName, editAddress, editFacilities, editStaff;
    @FXML
    private TextArea textDescription;
    @FXML
    private ChoiceBox choiceCity, choiceArea, choiceStar;
    @FXML
    private Button btnSave;

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
    protected void onSaveAction() throws IOException {
        String name = editName.getText();
        int areaId = 0;
        for (AreaVO areaVO : areas) {
            if (areaVO.areaName.equals(choiceArea.getValue()) && areaVO.cityName.equals(choiceCity.getValue())) {
                areaId = areaVO.id;
                break;
            }
        }
        String address = editAddress.getText();
        String description = textDescription.getText();
        String facilities = editFacilities.getText();
        String star = (String) choiceStar.getValue();
        String staff = editStaff.getText();

        if (hotelVO == null) {
            ResultMessage result = controller.addHotel(new HotelVO(0, name, areaId, address, description, facilities, star, staff));
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("增加酒店成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("增加酒店失败: " + result.message);
            }
        } else {
            // modify
            hotelVO.name = name;
            hotelVO.areaId = areaId;
            hotelVO.address = address;
            hotelVO.description = description;
            hotelVO.facilities = facilities;
            hotelVO.star = star;
            hotelVO.staff = staff;

            ResultMessage result = controller.modifyHotel(hotelVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("酒店信息修改成功");
                onCancelAction();
            } else {
                Dialogs.showError("酒店信息修改失败: " + result.message);
            }
        }
    }

    @FXML
    private void onCancelAction() {
        getChildren().clear();
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }

}
