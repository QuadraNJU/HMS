package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.net.PassHash;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.time.LocalDate;

/**
 * Created by adn55 on 2016/12/1.
 */
public class UserEditView extends Parent {

    private UserController userController;
    private CustomerController customerController;
    private UserVO userVO;
    private MemberVO memberVO;
    private SuccessHandler onSuccess;

    public UserEditView(UserVO vo, UserController userController, SuccessHandler onSuccess) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("useredit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.userController = userController;
        this.customerController = new CustomerController();
        this.onSuccess = onSuccess;
        this.userVO = vo;
        if (vo != null) {
            loadUserInfo(vo);
        } else {
            loadUserInfo(null);
        }
    }

    @FXML
    private TextField textUsername, textPassword, textName, textContact, textCompanyName;
    @FXML
    private ComboBox<String> comboUsertype, comboMembertype;
    @FXML
    private Label labelMembertype, labelBirthday, labelCompanyName, labelWarn;
    @FXML
    private DatePicker dateBirthday;

    @FXML
    protected void onSaveAction() {
        ResultMessage userMessage;
        ResultMessage memberMessage;
        if(textName.getText().isEmpty() || textContact.getText().isEmpty()) {
            Dialogs.showError("基础信息未填写完全，请重新填写");
            return;
        }

//        MemberVO memberVO = null;
        if(userVO != null) {
            if(textPassword.getText() != null) userVO.password = PassHash.hash(textPassword.getText());
            userVO.name = textName.getText();
            userVO.contact = textContact.getText();
            userVO.type = UserType.getByShowname(comboUsertype.getValue());
            userMessage = userController.modify(userVO);
        } else {
            UserType type = UserType.getByShowname(comboUsertype.getValue());
            userVO = new UserVO(textUsername.getText(), PassHash.hash(textPassword.getText()), textName.getText(), textContact.getText(), type);
            userMessage = userController.add(userVO);
        }

        if (comboMembertype.getValue().equals(MemberType.PERSONAL.toString())) {
            memberVO = new MemberVO(userVO.username, MemberType.PERSONAL, LocalDate.from(dateBirthday.getValue()), null);
        } else if (comboMembertype.getValue().equals(MemberType.COMPANY.toString())) {
            memberVO = new MemberVO(userVO.username, MemberType.COMPANY, null, textCompanyName.getText());
        } else {
            memberVO = new MemberVO(userVO.username, MemberType.NONE, null, null);
        }

        memberMessage = customerController.enroll(memberVO);

        if(userMessage.result == ResultMessage.RESULT_SUCCESS && memberMessage.result == ResultMessage.RESULT_SUCCESS) {
            if(textUsername.isEditable()) {
                Dialogs.showInfo("增加用户信息成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("修改用户信息成功");
                onSuccess.handle();
                onCancelAction();
            }
        } else {
            if(textUsername.isEditable()) {
                Dialogs.showInfo("增加用户信息失败: " + userMessage.message + memberMessage.message);
            } else {
                Dialogs.showInfo("修改用户信息失败: " + userMessage.message + memberMessage.message);
            }
        }
    }

    @FXML
    protected void onCancelAction() {
        this.getChildren().clear();
    }

    private void loadUserInfo(UserVO userVO) {
        if(userVO != null) {
            textUsername.setText(userVO.username);
            textUsername.setEditable(false);
            textPassword.setText(null);
            textName.setText(userVO.name);
            textContact.setText(userVO.contact);
            comboUsertype.setValue(userVO.type.toString());
            reloadUsertypePartInfo();
        } else {
            labelWarn.setText("请输入密码");
            textUsername.setText(null);
            textPassword.setText(null);
            textName.setText(null);
            textContact.setText(null);
            comboUsertype.setValue(UserType.CUSTOMER.toString());
            labelCompanyName.setVisible(false);
            textCompanyName.setVisible(false);
            textCompanyName.setText(null);
            labelBirthday.setVisible(false);
            dateBirthday.setVisible(false);
            dateBirthday.setValue(null);
        }
    }


    @FXML
    private void reloadUsertypePartInfo() {
        if (comboUsertype.getSelectionModel().getSelectedItem().equals(UserType.CUSTOMER.toString())) {
            labelMembertype.setVisible(true);
            comboMembertype.setVisible(true);
            try{
                memberVO = customerController.getMemberInfo(userVO.username);
            } catch (NullPointerException e) {
                memberVO = null;
            }
            try{
                comboMembertype.setValue(memberVO.memberType.toString());
            } catch (NullPointerException e) {
                comboMembertype.getSelectionModel().select(0);
            }
            reloadMembertypePartInfo();
        } else {
            labelMembertype.setVisible(false);
            comboMembertype.setVisible(false);
            comboMembertype.getSelectionModel().select(0);
            labelCompanyName.setVisible(false);
            textCompanyName.setVisible(false);
            textCompanyName.setText(null);
            labelBirthday.setVisible(false);
            dateBirthday.setVisible(false);
            dateBirthday.setValue(null);
        }
    }

    @FXML
    private void reloadMembertypePartInfo() {
        if (comboMembertype.getSelectionModel().getSelectedItem().equals(MemberType.PERSONAL.toString())) {
            labelCompanyName.setVisible(false);
            textCompanyName.setVisible(false);
            textCompanyName.setText(null);
            labelBirthday.setVisible(true);
            dateBirthday.setVisible(true);
            if(memberVO == null || memberVO.birthday == null)
                dateBirthday.setValue(null);
            else
                dateBirthday.setValue(LocalDate.from(memberVO.birthday));
        } else if (comboMembertype.getSelectionModel().getSelectedItem().equals(MemberType.COMPANY.toString())) {
            labelCompanyName.setVisible(true);
            textCompanyName.setVisible(true);
            if(memberVO == null || memberVO.companyName == null)
                textCompanyName.setText(null);
            else
                textCompanyName.setText(memberVO.companyName);
            labelBirthday.setVisible(false);
            dateBirthday.setVisible(false);
            dateBirthday.setValue(null);
        } else if (comboMembertype.getSelectionModel().getSelectedItem().equals(MemberType.NONE.toString())) {
            labelCompanyName.setVisible(false);
            textCompanyName.setVisible(false);
            textCompanyName.setText(null);
            labelBirthday.setVisible(false);
            dateBirthday.setVisible(false);
            dateBirthday.setValue(null);
        }
    }

    interface SuccessHandler {
        void handle();
    }

}