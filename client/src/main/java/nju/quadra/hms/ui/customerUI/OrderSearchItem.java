package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.common.ui.OrderDetailView;
import nju.quadra.hms.vo.OrderDetailVO;

import java.io.IOException;
import java.util.Optional;

class OrderSearchItem extends Parent {

    private CustomerController controller;
    private OrderSearchView parent;
    private OrderDetailVO order;

    @FXML
    private
    Label labelTime;
    @FXML
    private
    Label labelContent;
    @FXML
    private
    Label labelPrice;
    @FXML
    private
    Label labelPersonNumber;
    @FXML
    private Label labelOrderState;
    @FXML
    Button btnDetail;
    @FXML
    private
    Button btnUndo;
    @FXML
    private Button btnComment;


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
    public void onCommentAction() {
        parent.loadCommentView(order);
    }

    @FXML
    public void onUndoAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认预订");
        alert.setHeaderText(null);
        alert.setContentText("若距离最晚订单执行时间不足 6 小时，您的信用值将会被扣除。\n是否确认撤销订单?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
            ResultMessage rs = controller.undoUnfinishedOrder(order.id);
            if (rs.result != ResultMessage.RESULT_SUCCESS) {
                Dialogs.showError("撤销订单失败: " + rs.message);
            } else {
                Dialogs.showInfo("撤销订单成功");
                parent.loadOrders();
            }
        }
    }
}
