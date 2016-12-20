package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.WebmasterController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

class UserView extends Parent {

    private final WebmasterController controller = new WebmasterController();
    private ArrayList<UserVO> userList;

    @FXML
    private TableView<UserProperty> tableUserInfo;
    @FXML
    private Pane pane;

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
        userList = controller.getAllUsers();
        tableUserInfo.getItems().clear();
        for(UserVO vo: userList) {
            tableUserInfo.getItems().add(new UserProperty(vo));
        }
    }

    @FXML
    protected void onAddAction() throws IOException {
        pane.getChildren().add(new UserEditView(null, this::loadUserList));
    }

    @FXML
    protected void onModifyAction() throws IOException {
        UserProperty selected = tableUserInfo.getSelectionModel().getSelectedItem();
        if (selected != null) {
            pane.getChildren().add(new UserEditView(userList.stream().filter(vo -> vo.username.equals(selected.getUsername())).findFirst().get(), this::loadUserList));
        }
    }

    @FXML
    protected void onDeleteAction() {
        UserProperty selected = tableUserInfo.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("确认");
            alert.setHeaderText(null);
            alert.setContentText("确认删除该用户?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
                ResultMessage result = controller.deleteUser(selected.getUsername());
                if (result.result == ResultMessage.RESULT_SUCCESS) {
                    Dialogs.showInfo("删除用户成功");
                } else {
                    Dialogs.showError("删除用户失败: " + result.message);
                }
                loadUserList();
            }
        }
    }

    public class UserProperty {
        private final UserVO vo;

        public UserProperty(UserVO vo) {
            this.vo = vo;
        }

        public String getUsername() {
            return vo.username;
        }

        public String getName() {
            return vo.name;
        }

        public String getContact() {
            return vo.contact;
        }

        public String getType() {
            return vo.type.toString();
        }
    }
}
