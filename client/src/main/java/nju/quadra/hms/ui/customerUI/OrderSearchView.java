package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.OrderController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/12/7.
 */
public class OrderSearchView extends Parent {
    @FXML
    Pane pane;
    @FXML
    ComboBox<OrderState> comboOrderState;
    @FXML
    DatePicker dateStart, dateEnd;
    @FXML
    VBox vBox;
    OrderController orderController;

    public OrderSearchView() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearch.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        orderController = new OrderController();
        comboOrderState.getItems().addAll(OrderState.BOOKED,
                OrderState.UNFINISHED,
                OrderState.FINISHED,
                OrderState.RANKED,
                OrderState.DELAYED,
                OrderState.UNDO
                );
        comboOrderState.getSelectionModel().select(0);
        dateStart.setValue(LocalDate.now());
        dateEnd.setValue(LocalDate.now());

        onSearchAction();
    }

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }


    @FXML
    private void onSearchAction() throws IOException {
        ArrayList<OrderVO> orders = orderController.getByCustomer(HttpClient.session.username);
        orders.removeIf(vo -> vo.state != comboOrderState.getValue());
        LocalDate tempStart = dateStart.getValue();
        LocalDate tempEnd = dateEnd.getValue();

        if(tempStart.compareTo(tempEnd) > 0) {
            Dialogs.showError("开始时间应不晚于结束时间，请重新输入");
            return;
        }

        vBox.getChildren().clear();
        orders.removeIf(vo -> !(vo.startDate.compareTo(tempStart) > 0 && tempEnd.compareTo(vo.endDate) > 0));
        orders.sort((vo1, vo2) -> vo1.startDate.compareTo(vo2.startDate) > 0? 1: -1);
        for(OrderVO vo: orders) {
            vBox.getChildren().add(new OrderSearchItem(this, orderController, vo));
        }
    }
}
