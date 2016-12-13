package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/29.
 */
class CustomerInfoView extends Parent {

    private final CustomerController controller = new CustomerController();

    CustomerInfoView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("info.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadInfo();
    }

    private UserVO userVO;

    @FXML
    private TextField editName, editContact, editCredit;
    @FXML
    private Button btnModify, btnCancel;

    private void loadInfo() {
        // get user info
        userVO = controller.getUserInfo(HttpClient.session.username);
        if (userVO != null) {
            editName.setText(userVO.name);
            editContact.setText(userVO.contact);
        } else {
            Dialogs.showError(new ResultMessage(ResultMessage.RESULT_NET_ERROR).message);
        }
        // get credit
        ArrayList<CreditRecordVO> record = controller.getCreditRecord();
        editCredit.setText(new DecimalFormat("0.00").format(record.get(0).creditResult));
    }

    @FXML
    protected void onModifyAction() {
        if (editName.isEditable()) {
            // save
            userVO.name = editName.getText();
            userVO.contact = editContact.getText();
            ResultMessage result = controller.modifyUserInfo(userVO);
            switch (result.result) {
                case ResultMessage.RESULT_SUCCESS:
                    editName.setEditable(false);
                    editContact.setEditable(false);
                    btnModify.setText("修改");
                    btnCancel.setVisible(false);
                    Dialogs.showInfo("修改个人信息成功");
                    break;
                default:
                    Dialogs.showError("修改个人信息失败: " + result.message);
            }
        } else {
            editName.setEditable(true);
            editContact.setEditable(true);
            btnModify.setText("保存");
            btnCancel.setVisible(true);
            editName.requestFocus();
        }
    }

    @FXML
    protected void onCancelAction() {
        editName.setEditable(false);
        editContact.setEditable(false);
        btnModify.setText("修改");
        btnCancel.setVisible(false);
        // recover
        loadInfo();
    }

}
