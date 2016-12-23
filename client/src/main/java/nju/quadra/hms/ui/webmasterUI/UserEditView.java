package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import nju.quadra.hms.controller.WebmasterController;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.util.PassHash;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;

class UserEditView extends Parent {

    private final WebmasterController controller = new WebmasterController();
    private UserVO userVO;
    private SuccessHandler onSuccess;

    public UserEditView(UserVO vo, SuccessHandler onSuccess) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("useredit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceUserType.getItems().addAll(UserType.values());
        choiceMemberType.getItems().addAll(MemberType.values());
        choiceUserType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(UserType.CUSTOMER)) {
                labelMembertype.setVisible(true);
                choiceMemberType.setVisible(true);
            } else {
                labelMembertype.setVisible(false);
                choiceMemberType.setVisible(false);
                labelBirthday.setVisible(false);
                dateBirthday.setVisible(false);
                labelCompanyName.setVisible(false);
                textCompanyName.setVisible(false);
            }
        });
        choiceMemberType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            labelBirthday.setVisible(false);
            dateBirthday.setVisible(false);
            labelCompanyName.setVisible(false);
            textCompanyName.setVisible(false);
            switch (newValue) {
                case PERSONAL:
                    labelBirthday.setVisible(true);
                    dateBirthday.setVisible(true);
                    break;
                case COMPANY:
                    labelCompanyName.setVisible(true);
                    textCompanyName.setVisible(true);
            }
        });
        choiceUserType.getSelectionModel().select(0);
        choiceMemberType.getSelectionModel().select(0);

        this.onSuccess = onSuccess;
        this.userVO = vo;
        if (vo != null) {
            loadUserInfo(vo);
        } else {
            labelWarn.setVisible(false);
        }
    }

    @FXML
    private TextField textUsername, textPassword, textName, textContact, textCompanyName;
    @FXML
    private ChoiceBox<UserType> choiceUserType;
    @FXML
    private ChoiceBox<MemberType> choiceMemberType;
    @FXML
    private Label labelUserType, labelMembertype, labelBirthday, labelCompanyName, labelWarn;
    @FXML
    private DatePicker dateBirthday;

    @FXML
    protected void onSaveAction() {
        ResultMessage userResult;

        if (textUsername.isEditable()) {
            // add
            userVO = new UserVO(textUsername.getText(), PassHash.hash(textPassword.getText()), textName.getText(), textContact.getText(), choiceUserType.getValue());
            userResult = controller.addUser(userVO);
        } else {
            // modify
            if (!textPassword.getText().isEmpty()) {
                userVO.password = PassHash.hash(textPassword.getText());
            }
            userVO.name = textName.getText();
            userVO.contact = textContact.getText();
            userResult = controller.modifyUser(userVO);
        }

        if (userResult.result == ResultMessage.RESULT_SUCCESS) {
            if(textUsername.isEditable()) {
                Dialogs.showInfo("增加用户信息成功");
            } else {
                Dialogs.showInfo("修改用户信息成功");
            }
            onSuccess.handle();
            onCancelAction();
        } else {
            if(textUsername.isEditable()) {
                Dialogs.showInfo("增加用户信息失败: " + userResult.message);
            } else {
                Dialogs.showInfo("修改用户信息失败: " + userResult.message);
            }
        }

        if (userVO.type.equals(UserType.CUSTOMER)) {
            MemberVO memberVO;
            if (choiceMemberType.getValue().equals(MemberType.PERSONAL)) {
                memberVO = new MemberVO(userVO.username, choiceMemberType.getValue(), dateBirthday.getValue(), null);
            } else {
                memberVO = new MemberVO(userVO.username, choiceMemberType.getValue(), null, textCompanyName.getText());
            }
            ResultMessage memberResult = controller.modifyMemberInfo(memberVO);
            if (memberResult.result != ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("修改会员信息失败: " + memberResult.message);
            }
        }
    }

    @FXML
    private void onCancelAction() {
        this.getChildren().clear();
    }

    private void loadUserInfo(UserVO userVO) {
        textUsername.setText(userVO.username);
        textUsername.setEditable(false);
        textName.setText(userVO.name);
        textContact.setText(userVO.contact);
        choiceUserType.setValue(userVO.type);
        choiceUserType.setDisable(true);
        if (userVO.type.equals(UserType.CUSTOMER)) {
            MemberVO memberVO = controller.getMemberInfo(userVO.username);
            choiceMemberType.setValue(memberVO.memberType);
            dateBirthday.setValue(memberVO.birthday);
            textCompanyName.setText(memberVO.companyName);
        }
    }

    interface SuccessHandler {
        void handle();
    }

}