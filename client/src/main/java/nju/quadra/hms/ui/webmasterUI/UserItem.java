package nju.quadra.hms.ui.webmasterUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class UserItem extends Parent {

    @FXML
    Label labelUsername, labelName, labelContact, labelUsertype;

    public UserItem(UserVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("useritem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        labelUsername.setText(vo.username);
        labelName.setText(vo.name);
        labelContact.setText(vo.contact);
        labelUsertype.setText(vo.type.toString());

    }
}
