package nju.quadra.hms.ui.hotelStaffUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/29.
 */
public class HotelInfoView extends Parent {

    private HotelStaffController controller = new HotelStaffController(HttpClient.session.username);

    public HotelInfoView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelinfo.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceStar.getItems().addAll(new String[]{"一星级", "二星级", "三星级", "四星级", "五星级"});
        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(vo -> vo.cityName.equals(newValue)).map(vo -> vo.areaName).toArray());
        });

        loadAreas();
        loadInfo();
    }

    private HotelVO hotelVO;
    private ArrayList<AreaVO> areas;

    @FXML
    private TextField editName, editAddress, editFacilities;
    @FXML
    private TextArea textDescription;
    @FXML
    private ChoiceBox choiceCity, choiceArea, choiceStar;
    @FXML
    private Button btnModify, btnCancel;

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

    private void loadInfo() {
        hotelVO = controller.getHotelInfo();
        editName.setText(hotelVO.name);
        editAddress.setText(hotelVO.address);
        textDescription.setText(hotelVO.description);
        editFacilities.setText(hotelVO.facilities);
        choiceStar.setValue(hotelVO.star);
        for (AreaVO areaVO : areas) {
            if (areaVO.id == hotelVO.areaId) {
                choiceCity.setValue(areaVO.cityName);
                choiceArea.setValue(areaVO.areaName);
                break;
            }
        }
    }

    @FXML
    protected void onModifyAction() {
        if (editName.isEditable()) {
            hotelVO.name = editName.getText();
            hotelVO.areaId = 0;
            for (AreaVO areaVO : areas) {
                if (areaVO.areaName.equals(choiceArea.getValue()) && areaVO.cityName.equals(choiceCity.getValue())) {
                    hotelVO.areaId = areaVO.id;
                    break;
                }
            }
            hotelVO.address = editAddress.getText();
            hotelVO.description = textDescription.getText();
            hotelVO.facilities = editFacilities.getText();
            hotelVO.star = (String) choiceStar.getValue();
            if (hotelVO.name.isEmpty() || hotelVO.address.isEmpty() || hotelVO.description.isEmpty() || hotelVO.facilities.isEmpty() || hotelVO.star.isEmpty()) {
                Dialogs.showError("酒店基本信息不完整，请重新输入");
                return;
            }
            ResultMessage result = controller.modifyHotelInfo(hotelVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("酒店基本信息修改成功");
                onCancelAction();
            } else {
                Dialogs.showError("酒店基本信息修改失败: " + result.message);
            }
        } else {
            editName.setEditable(true);
            editAddress.setEditable(true);
            editFacilities.setEditable(true);
            textDescription.setEditable(true);
            choiceCity.setDisable(false);
            choiceArea.setDisable(false);
            choiceStar.setDisable(false);
            btnModify.setText("保存");
            btnCancel.setVisible(true);
        }
    }

    @FXML
    protected void onCancelAction() {
        editName.setEditable(false);
        editAddress.setEditable(false);
        editFacilities.setEditable(false);
        textDescription.setEditable(false);
        choiceCity.setDisable(true);
        choiceArea.setDisable(true);
        choiceStar.setDisable(true);
        btnModify.setText("修改");
        btnCancel.setVisible(false);
        loadInfo();
    }

}
