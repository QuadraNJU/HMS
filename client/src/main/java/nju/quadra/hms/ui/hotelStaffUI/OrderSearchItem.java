package nju.quadra.hms.ui.hotelStaffUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.common.ui.OrderDetailView;
import nju.quadra.hms.vo.OrderDetailVO;

import java.io.IOException;
import java.util.Optional;

class OrderSearchItem extends Parent {

    private HotelStaffController controller;
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
    Button btnCheckin;
    @FXML
    private Button btnCheckout;


    public OrderSearchItem(OrderSearchView parent, HotelStaffController controller, OrderDetailVO vo) throws IOException {
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
        labelPersonNumber.setText("客户: " + order.username);
        labelOrderState.setText(order.state.toString());
        labelContent.setText(order.room.name + " " + order.roomCount + " 间");

        if (order.state == OrderState.BOOKED || order.state.equals(OrderState.DELAYED)) {
            btnCheckin.setVisible(true);
        } else if (order.state == OrderState.UNFINISHED) {
            btnCheckout.setVisible(true);
        }
    }

    @FXML
    public void onDetailAction() throws IOException {
        parent.loadView(new OrderDetailView(order));
    }

    @FXML
    public void onCheckinAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText(null);
        alert.setContentText("是否确认办理入住?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
            ResultMessage rs = controller.checkinOrder(order.id);
            if (rs.result != ResultMessage.RESULT_SUCCESS) {
                Dialogs.showError("办理入住失败: " + rs.message);
            } else {
                Dialogs.showInfo("办理入住成功");
                parent.loadOrders();
            }
        }
    }

    @FXML
    public void onCheckoutAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText(null);
        alert.setContentText("是否确认办理退房?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
            ResultMessage rs = controller.checkoutOrder(order.id);
            if (rs.result != ResultMessage.RESULT_SUCCESS) {
                Dialogs.showError("办理退房失败: " + rs.message);
            } else {
                Dialogs.showInfo("办理退房成功");
                parent.loadOrders();
            }
        }
    }

}
