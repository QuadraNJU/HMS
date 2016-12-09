package nju.quadra.hms.ui.webMarketerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.WebMarketerController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.common.ui.OrderDetailView;
import nju.quadra.hms.vo.OrderDetailVO;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by adn55 on 2016/11/30.
 */
class OrderSearchItem extends Parent {

    private WebMarketerController controller;
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
    private Button btnUndo;


    public OrderSearchItem(OrderSearchView parent, WebMarketerController controller, OrderDetailVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.controller = controller;
        this.parent = parent;
        this.order = vo;

        loadOrderInfo();
    }

    private void loadOrderInfo() {
        labelTime.setText(order.startDate.toString() + " - " + order.endDate.toString());
        labelPrice.setText("¥ " + order.price);
        labelPersonNumber.setText("客户: " + order.username);
        labelOrderState.setText(order.state.toString());
        labelContent.setText(order.room.name + " " + order.roomCount + " 间");

        if (order.state.equals(OrderState.DELAYED)) {
            btnUndo.setVisible(true);
        }
    }

    @FXML
    public void onDetailAction() throws IOException {
        parent.loadView(new OrderDetailView(order));
    }

    @FXML
    public void onUndoAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText(null);
        alert.setContentText("是否确认撤销? 请选择要恢复的信用值:");
        ButtonType returnAll = new ButtonType("撤销并恢复全部信用值");
        ButtonType returnHalf = new ButtonType("撤销并恢复一半信用值");
        alert.getButtonTypes().setAll(returnAll, returnHalf, ButtonType.CANCEL);
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && !confirm.get().equals(ButtonType.CANCEL)) {
            ResultMessage rs = controller.undoDelayedOrder(order.id, confirm.get().equals(returnAll));
            if (rs.result != ResultMessage.RESULT_SUCCESS) {
                Dialogs.showError("撤销订单失败: " + rs.message);
            } else {
                Dialogs.showInfo("撤销订单成功");
                parent.loadOrders();
            }
        }
    }

}
