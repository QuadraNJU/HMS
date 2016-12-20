package nju.quadra.hms.ui.hotelStaffUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelRoomVO;

import java.io.IOException;
import java.text.DecimalFormat;

class HotelRoomEditView extends Parent {

    private HotelStaffController controller;
    private HotelRoomVO hotelRoomVO;
    private SuccessHandler onSuccess;

    public HotelRoomEditView(HotelRoomVO vo, HotelStaffController controller, SuccessHandler onSuccess) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("roomedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.controller = controller;
        this.onSuccess = onSuccess;
        this.hotelRoomVO = vo;
        if (vo != null) {
            editName.setText(vo.name);
            editPrice.setText(new DecimalFormat("0.00").format(vo.price));
            editTotal.setText(vo.total + "");
        }
    }

    @FXML
    private TextField editName, editPrice, editTotal;

    @FXML
    protected void onSaveAction() {
        double price;
        int total;
        try {
            price = Double.parseDouble(editPrice.getText());
            total = Integer.parseInt(editTotal.getText());
        } catch (Exception e) {
            // e.printStackTrace();
            Dialogs.showError("数值格式不正确，请重新填写");
            return;
        }
        String name = editName.getText();

        if (this.hotelRoomVO == null) {
            // add
            ResultMessage result = controller.addHotelRoom(new HotelRoomVO(0, 0, name, total, price));
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("增加客房信息成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("增加客房信息失败: " + result.message);
            }
        } else {
            // modify
            hotelRoomVO.name = name;
            hotelRoomVO.price = price;
            hotelRoomVO.total = total;
            ResultMessage result = controller.modifyHotelRoom(hotelRoomVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("修改客房信息成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("修改客房信息失败: " + result.message);
            }
        }
    }

    @FXML
    private void onCancelAction() {
        this.getChildren().clear();
    }

    interface SuccessHandler {
        void handle();
    }

}