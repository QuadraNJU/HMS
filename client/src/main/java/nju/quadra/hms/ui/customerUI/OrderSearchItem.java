package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.CreditRecordController;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.controller.HotelRoomController;
import nju.quadra.hms.controller.OrderController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class OrderSearchItem extends Parent {

    private OrderController orderController;

    private OrderSearchView parent;
    private OrderVO orderVO;

    @FXML
    Label labelTime, labelContent, labelPrice, labelPersonNumber, labelOrderState;
    @FXML
    Button btnDetail, btnUndo, btnComment;

    public OrderSearchItem(OrderSearchView parent, OrderController orderController, OrderVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.orderController = orderController;

        this.parent = parent;
        orderVO = vo;

        loadOrderInfo();
    }

    private void loadOrderInfo() {
        labelTime.setText(orderVO.startDate.toString() + " - " + orderVO.endDate.toString());
        labelPrice.setText("¥ " + orderVO.price);
        labelPersonNumber.setText("共" + orderVO.personCount + "人");
        labelOrderState.setText(orderVO.state.toString());
        HotelController h = new HotelController();
        HotelVO v = h.getDetail(orderVO.hotelId);
        HotelRoomController hr = new HotelRoomController();
        HotelRoomVO hrv = hr.getById(orderVO.roomId);
        labelContent.setText(v.name + "，" + hrv.name + "，" + orderVO.roomCount + "间");

        if(orderVO.state == OrderState.BOOKED) {
            btnUndo.setVisible(true);
            btnComment.setVisible(false);
        } else if(orderVO.state == OrderState.FINISHED) {
            btnUndo.setVisible(false);
            btnComment.setVisible(true);
        }
    }

    @FXML
    public void onDetailAction() throws IOException {
        parent.loadView(new OrderDetailView(orderVO));
    }

    @FXML
    public void onCommentAction() throws IOException {
    }

    @FXML
    public void onUndoAction() throws IOException {
        ResultMessage rs = orderController.undoUnfinished(orderVO);
        if(rs.result != ResultMessage.RESULT_SUCCESS) {
            Dialogs.showError(rs.message);
        }
    }
}
