package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.vo.CreditRecordVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
class CreditRecordView extends Parent {

    private final CustomerController controller = new CustomerController();

    public CreditRecordView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("credit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadCreditRecord();
    }

    @FXML
    private VBox vBox;

    private void loadCreditRecord() throws IOException {
        ArrayList<CreditRecordVO> record = controller.getCreditRecord(HttpClient.session.username);
        if (record != null && record.size() > 0) {
            for (CreditRecordVO vo : record) {
                vBox.getChildren().add(new CreditRecordItem(vo));
            }
        }
    }

}
