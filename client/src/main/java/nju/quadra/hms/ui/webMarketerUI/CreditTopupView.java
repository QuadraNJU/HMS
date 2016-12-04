package nju.quadra.hms.ui.webMarketerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import nju.quadra.hms.controller.WebMarketerController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;

import java.io.IOException;

/**
 * Created by adn55 on 2016/12/4.
 */
public class CreditTopupView extends Parent {

    private WebMarketerController controller = new WebMarketerController();

    public CreditTopupView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("credittopup.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());
    }

    @FXML
    TextField editUsername, editAmount;

    @FXML
    protected void onSubmitAction() {
        String username;
        int amount;
        try {
            username = editUsername.getText();
            amount = Integer.parseInt(editAmount.getText());
        } catch (Exception e) {
            e.printStackTrace();
            Dialogs.showError("数值格式不正确，请重新填写");
            return;
        }
        ResultMessage result = controller.creditTopup(username, amount);
        if (result.result == ResultMessage.RESULT_SUCCESS) {
            Dialogs.showInfo("充值成功");
            editUsername.clear();
            editAmount.clear();
        } else {
            Dialogs.showError("充值失败: " + result.message);
        }
    }

}
