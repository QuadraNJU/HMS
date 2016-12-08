package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.CreditRecordVO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Created by adn55 on 2016/11/30.
 */
public class CreditRecordItem extends Parent {

    @FXML
    Label labelTime, labelAction, labelDiff, labelResult;

    public CreditRecordItem(CreditRecordVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("credititem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        if (vo.timestamp != null) {
            labelTime.setText(vo.timestamp.format(DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss")));
        }
        switch (vo.action) {
            case ORDER_CANCELLED:
            case ORDER_DELAYED:
            case ORDER_FINISHED:
            case ORDER_UNDO:
                labelAction.setText(vo.action.toString() + " （订单号：" + vo.orderId + "）");
            default:
                labelAction.setText(vo.action.toString());
        }
        if (vo.diff >= 0) {
            labelDiff.setText("+" + vo.diff);
        } else {
            labelDiff.setText(vo.diff + "");
        }
        labelResult.setText(vo.creditResult + "");
    }
}
