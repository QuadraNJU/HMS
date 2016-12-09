package nju.quadra.hms.ui.customerUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.MemberVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/29.
 */
class MemberEnrollView extends Parent {

    private CustomerController controller = new CustomerController();

    public MemberEnrollView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("member.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadMemberInfo();
    }

    @FXML
    private RadioButton radioPersonal, radioCompany;
    @FXML
    private Label labelBirthday, labelCompanyName;
    @FXML
    private DatePicker dateBirthday;
    @FXML
    private TextField editCompanyName;
    @FXML
    private Button btnSubmit;

    private void showPersonalControls() {
        labelCompanyName.setVisible(false);
        editCompanyName.setVisible(false);
        labelBirthday.setVisible(true);
        dateBirthday.setVisible(true);
    }

    private void showCompanyControls() {
        labelBirthday.setVisible(false);
        dateBirthday.setVisible(false);
        labelCompanyName.setVisible(true);
        editCompanyName.setVisible(true);
    }

    private void loadMemberInfo() {
        MemberVO memberVO = controller.getMemberInfo(HttpClient.session.username);
        if (memberVO.memberType != MemberType.NONE) {
            // disable controls
            radioPersonal.setDisable(true);
            radioCompany.setDisable(true);
            dateBirthday.setDisable(true);
            editCompanyName.setEditable(false);
            btnSubmit.setVisible(false);

            switch (memberVO.memberType) {
                case PERSONAL:
                    radioPersonal.setSelected(true);
                    dateBirthday.setValue(memberVO.birthday);
                    showPersonalControls();
                    break;
                case COMPANY:
                    radioCompany.setSelected(true);
                    editCompanyName.setText(memberVO.companyName);
                    showCompanyControls();
                    break;
            }
        }
    }

    @FXML
    protected void onRadioAction(ActionEvent event) {
        switch (((RadioButton) event.getSource()).getId()) {
            case "radioPersonal":
                showPersonalControls();
                break;
            case "radioCompany":
                showCompanyControls();
                break;
        }
    }

    @FXML
    protected void onSubmitAction() {
        MemberVO memberVO;
        if (radioPersonal.isSelected()) {
            memberVO = new MemberVO(HttpClient.session.username, MemberType.PERSONAL, dateBirthday.getValue(), null);
        } else {
            memberVO = new MemberVO(HttpClient.session.username, MemberType.COMPANY, null, editCompanyName.getText());
        }
        ResultMessage result = controller.enroll(memberVO);
        switch (result.result) {
            case ResultMessage.RESULT_SUCCESS:
                Dialogs.showInfo("会员登记成功");
                loadMemberInfo();
                break;
            default:
                Dialogs.showError("会员登记失败: " + result.message);
        }
    }

}
