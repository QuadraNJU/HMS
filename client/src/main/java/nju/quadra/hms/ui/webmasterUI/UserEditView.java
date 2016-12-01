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
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/12/1.
 */
public class UserEditView extends Parent {

    private UserController userController;
    private CustomerController customerController;
    private UserVO userVO;
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
        }
    }

    @FXML
    private TextField textUsername, textPassword, textName, textContact, textCompanyName;
    @FXML
    private ComboBox<String> comboUsertype, comboMembertype;
    @FXML
    private Label labelMembertype, labelBirthday, labelCompanyName;
    @FXML
    private DatePicker dateBirthday;

    @FXML
    protected void onSaveAction() {
    }

    @FXML
    protected void onCancelAction() {
        this.getChildren().clear();
    }

    private void loadUserInfo(UserVO userVO) {
        textUsername.setText(userVO.username);
        textPassword.setText(userVO.password);
        textName.setText(userVO.name);
        textContact.setText(userVO.contact);
        comboUsertype.setValue(userVO.type.toString());
        reloadUsertypePartInfo();
    }

    @FXML
    private void reloadUsertypePartInfo() {
        if (comboUsertype.getSelectionModel().getSelectedItem().equals(UserType.CUSTOMER.toString())) {
            labelMembertype.setVisible(true);
            comboMembertype.setVisible(true);
            reloadMembertypePartInfo();
            MemberVO memberVO = customerController.getMemberInfo(userVO.username);
            comboMembertype.setValue(memberVO.memberType.toString());
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
        } else if (comboMembertype.getSelectionModel().getSelectedItem().equals(MemberType.COMPANY.toString())) {
            labelCompanyName.setVisible(true);
            textCompanyName.setVisible(true);
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