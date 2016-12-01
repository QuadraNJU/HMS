package nju.quadra.hms.ui.webmasterUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by adn55 on 2016/11/30.
 */
public class UserView extends Parent {

    private UserController controller = new UserController();
    @FXML
    private TableView<UserProperty> tableUserInfo;
    @FXML
    Pane pane;
    private ArrayList<UserVO> userList;

    public UserView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userlist.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        tableUserInfo.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("username"));
        tableUserInfo.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableUserInfo.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableUserInfo.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("type"));


        loadUserList();
    }

    private void loadUserList() {
        userList = controller.getAll();
        tableUserInfo.getItems().clear();
        for(UserVO vo: userList) {
            tableUserInfo.getItems().add(new UserProperty(vo));
        }

    }

    @FXML
    protected void onAddUserAction() throws IOException {
        pane.getChildren().add(new UserEditView(null, controller, this::loadUserList));
    }

    @FXML
    protected void onModifyUserAction() throws IOException {
        UserProperty selected = tableUserInfo.getSelectionModel().getSelectedItem();
        if (selected != null) {
            for (UserVO vo : userList) {
                if (vo.username.equals(selected.getUsername())) {
                    pane.getChildren().add(new UserEditView(vo, controller, this::loadUserList));
                    break;
                }
            }
        }
    }

    @FXML
    protected void onDeleteUserAction() throws IOException {
        UserProperty selected = tableUserInfo.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("确认");
            alert.setHeaderText(null);
            alert.setContentText("是否删除该用户信息?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
                ResultMessage result = controller.delete(selected.getUsername());
                if (result.result == ResultMessage.RESULT_SUCCESS) {
                    Dialogs.showInfo("删除客户信息成功");
                } else {
                    Dialogs.showError("删除客户信息失败: " + result.message);
                }
                loadUserList();
            }
        }    }

    public class UserProperty {
        private SimpleStringProperty username;
        private SimpleStringProperty name;
        private SimpleStringProperty contact;
        private SimpleStringProperty type;

        public UserProperty(UserVO vo) {
            this.username = new SimpleStringProperty(vo.username);
            this.name = new SimpleStringProperty(vo.name);
            this.contact = new SimpleStringProperty(vo.contact);
            this.type = new SimpleStringProperty(vo.type.toString());
        }

        public String getUsername() {
            return username.get();
        }

        public SimpleStringProperty usernameProperty() {
            return username;
        }

        public void setUsername(String username) {
            this.username.set(username);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getContact() {
            return contact.get();
        }

        public SimpleStringProperty contactProperty() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact.set(contact);
        }

        public String getType() {
            return type.get();
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public void setType(String type) {
            this.type.set(type);
        }
    }
}
