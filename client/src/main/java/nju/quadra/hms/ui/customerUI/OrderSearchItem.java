package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.controller.HotelRoomController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.common.ui.OrderDetailView;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.OrderDetailVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class OrderSearchItem extends Parent {

    private CustomerController controller;
    private OrderSearchView parent;
    private OrderDetailVO order;

    @FXML
    Label labelTime, labelContent, labelPrice, labelPersonNumber, labelOrderState;
    @FXML
    Button btnDetail, btnUndo, btnComment;


    public OrderSearchItem(OrderSearchView parent, CustomerController controller, OrderDetailVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.controller = controller;
        this.parent = parent;
        order = vo;

        loadOrderInfo();
    }

    private void loadOrderInfo() {
        labelTime.setText(order.startDate.toString() + " - " + order.endDate.toString());
        labelPrice.setText("¥ " + order.price);
        labelPersonNumber.setText("共 " + order.persons.size() + " 人");
        labelOrderState.setText(order.state.toString());
        labelContent.setText(order.hotel.name + ", " + order.room.name + " " + order.roomCount + " 间");

        if (order.state == OrderState.BOOKED) {
            btnUndo.setVisible(true);
        } else if (order.state == OrderState.FINISHED) {
            btnComment.setVisible(true);
        }
    }

    @FXML
    public void onDetailAction() throws IOException {
        parent.loadView(new OrderDetailView(order));
    }

    @FXML
    public void onCommentAction() throws IOException {
        parent.loadCommentView(order);
    }

    @FXML
    public void onUndoAction() throws IOException {
        ResultMessage rs = controller.undoUnfinishedOrder(order.id);
        if(rs.result != ResultMessage.RESULT_SUCCESS) {
            Dialogs.showError("撤销订单失败: " + rs.message);
        } else {
            Dialogs.showInfo("撤销订单成功");
            parent.loadOrders();
        }
    }
}
