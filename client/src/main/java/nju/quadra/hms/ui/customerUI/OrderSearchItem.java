package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class OrderSearchItem extends Parent {

    private OrderSearchView parent;
    private OrderVO orderVO;

    @FXML
    Label labelTime, labelAction, labelPrice, labelPersonNumber, labelOrderState;
    @FXML
    Button btnDetail, btnOrder;

    public OrderSearchItem(OrderSearchView parent, OrderVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.parent = parent;
        orderVO = vo;
        }

    @FXML
    public void onDetailAction() throws IOException {
    }

    @FXML
    public void onCommentAction() throws IOException {
    }

    @FXML
    public void onUndoAction() throws IOException {
    }
}
