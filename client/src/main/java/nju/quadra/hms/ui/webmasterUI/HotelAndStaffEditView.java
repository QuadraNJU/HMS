package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/29.
 */
public class HotelAndStaffEditView extends Parent {

    private HotelController hotelController = new HotelController();
    private HotelVO hotelVO;
    private ArrayList<AreaVO> areas;
    private SuccessHandler onSuccess;

    public HotelAndStaffEditView(int hotelId, SuccessHandler onSuccess) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelandstaffedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());
        this.onSuccess = onSuccess;

        choiceStar.getItems().addAll(new String[]{"一星级", "二星级", "三星级", "四星级", "五星级"});
        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(vo -> vo.cityName.equals(newValue)).map(vo -> vo.areaName).toArray());
        });

        loadAreas();
        loadInfo(hotelId);
    }



    @FXML
    private TextField textName, textAddress, textFacilities, textStaff;
    @FXML
    private TextArea areaDescription;
    @FXML
    private ChoiceBox choiceCity, choiceArea, choiceStar;
    @FXML
    private Button btnSave, btnCancel;

    private void loadAreas() {
        areas = hotelController.getAllArea();
        if (areas != null) {
            for (AreaVO vo : areas) {
                if (choiceCity.getItems().indexOf(vo.cityName) < 0) {
                    choiceCity.getItems().add(vo.cityName);
                }
            }
        }
    }

    private void loadInfo(int hotelId) {
        if(hotelId != -1) {
            hotelVO = hotelController.getDetail(hotelId);
            textName.setText(hotelVO.name);
            textAddress.setText(hotelVO.address);
            areaDescription.setText(hotelVO.description);
            textFacilities.setText(hotelVO.facilities);
            choiceStar.setValue(hotelVO.star);
            for (AreaVO areaVO : areas) {
                if (areaVO.id == hotelVO.areaId) {
                    choiceCity.setValue(areaVO.cityName);
                    choiceArea.setValue(areaVO.areaName);
                    break;
                }
            }
            textStaff.setText(hotelVO.staff);
        } else {
            textName.setText(null);
            textAddress.setText(null);
            areaDescription.setText(null);
            textFacilities.setText(null);
            choiceStar.getSelectionModel().select(0);
            choiceCity.setValue(areas.get(0).cityName);
            choiceArea.setValue(areas.get(0).areaName);
            textStaff.setText(null);
        }
    }

    @FXML
    protected void onSaveAction() throws IOException{
        int areaid = 0;
        for (AreaVO areaVO : areas) {
            if (areaVO.areaName.equals(choiceArea.getValue())) {
                areaid = areaVO.id;
                break;
            }
        }
        if(hotelVO != null) {
            hotelVO.name = textName.getText();
            hotelVO.areaId = areaid;
            hotelVO.address = textAddress.getText();
            hotelVO.description = areaDescription.getText();
            hotelVO.facilities = textFacilities.getText();
            hotelVO.star = (String) choiceStar.getValue();
            if (hotelVO.name.isEmpty() || hotelVO.address.isEmpty() || hotelVO.description.isEmpty() || hotelVO.facilities.isEmpty() || hotelVO.star.isEmpty()) {
                Dialogs.showError("酒店基本信息不完整，请重新输入");
                return;
            }
            ResultMessage result = hotelController.modify(hotelVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("酒店基本信息修改成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showError("酒店基本信息修改失败: " + result.message);
            }
        } else {
            hotelVO = new HotelVO(0, textName.getText(), areaid, textAddress.getText(), areaDescription.getText(), textFacilities.getText(), (String) choiceStar.getValue(), null);
            ResultMessage result = hotelController.add(hotelVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("酒店基本信息增加成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showError("酒店基本信息增加失败: " + result.message);
            }
        }

    }

    @FXML
    protected void onCancelAction()  {
        this.getChildren().clear();
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }
}
