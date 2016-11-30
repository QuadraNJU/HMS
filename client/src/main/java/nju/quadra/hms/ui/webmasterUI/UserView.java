package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.controller.UserController;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.customerUI.CreditRecordItem;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
public class UserView extends Parent {

    private UserController controller = new UserController();

    public UserView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userlist.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadUserList();
    }

    @FXML
    private VBox vBox;

    private void loadUserList() throws IOException {
        ArrayList<UserVO> userList = controller.getAll();
        if (userList != null && userList.size() > 0) {
            for (UserVO vo : userList) {
                vBox.getChildren().add(new UserItem(vo));
            }
        }
    }

    @FXML
    public void onNewUserAction() throws Exception {
        //TODO
    }

    @FXML
    public void onModifyUserAction() throws Exception {
        //TODO
    }

    @FXML
    public void onDeleteUserAction() throws Exception {
        //TODO
    }
}
