package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.CreditRecordVO;

import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<CreditRecordVO> record = controller.getCreditRecord();
        if (record != null) {
            for (CreditRecordVO vo : record) {
                vBox.getChildren().add(new CreditRecordItem(vo));
            }
        } else {
            Dialogs.showError(new ResultMessage(ResultMessage.RESULT_NET_ERROR).message);
        }
    }

}
