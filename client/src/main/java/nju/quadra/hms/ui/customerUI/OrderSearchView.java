package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.OrderDetailVO;
import nju.quadra.hms.vo.OrderRankVO;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by RaUkonn on 2016/12/7.
 */
public class OrderSearchView extends Parent {

    @FXML
    private Pane pane;
    @FXML
    private ChoiceBox choiceOrderState;
    @FXML
    private DatePicker dateStart, dateEnd;
    @FXML
    private VBox vBox;

    /*Comment View Components*/
    @FXML
    private Pane paneComment;
    @FXML
    private ChoiceBox<String> choiceStar;
    @FXML
    private TextArea areaComment;
    /*End Comment View Components*/

    private CustomerController controller = new CustomerController();
    private ArrayList<OrderDetailVO> orders;
    private OrderDetailVO selectedOrder;

    public OrderSearchView() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearch.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceOrderState.getItems().add("所有订单状态");
        choiceOrderState.getItems().addAll(OrderState.values());
        choiceStar.getItems().addAll("★", "★★", "★★★", "★★★★", "★★★★★");
        dateStart.setValue(LocalDate.now());
        dateEnd.setValue(LocalDate.now());

        loadOrders();
        choiceOrderState.getSelectionModel().select(0);
        if (orders != null && orders.size() > 0) {
            dateStart.setValue(orders.stream().min(Comparator.comparing(vo -> vo.startDate)).get().startDate);
            dateEnd.setValue(orders.stream().max(Comparator.comparing(vo -> vo.endDate)).get().endDate);
        }
        showOrders();

        choiceOrderState.getSelectionModel().selectedItemProperty().addListener(observable -> showOrders());
        dateStart.valueProperty().addListener(observable -> showOrders());
        dateEnd.valueProperty().addListener(observable -> showOrders());
    }

    public void loadOrders() {
        this.orders = controller.getOrders(HttpClient.session.username);
        showOrders();
    }

    private void showOrders() {
        vBox.getChildren().clear();
        Stream<OrderDetailVO> stream = orders.stream();
        if (choiceOrderState.getSelectionModel().getSelectedIndex() > 0) {
            stream = stream.filter(vo -> vo.state.equals(choiceOrderState.getSelectionModel().getSelectedItem()));
        }
        if (dateStart.getValue() != null) {
            stream = stream.filter(vo -> vo.endDate.compareTo(dateStart.getValue()) >= 0);
        }
        if (dateEnd.getValue() != null) {
            stream = stream.filter(vo -> vo.startDate.compareTo(dateEnd.getValue()) <= 0);
        }
        for (Object vo : stream.toArray()) {
            try {
                vBox.getChildren().add(new OrderSearchItem(this, controller, (OrderDetailVO) vo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }

    protected void loadCommentView(OrderDetailVO vo) {
        selectedOrder = vo;
        choiceStar.getSelectionModel().select(4);
        areaComment.clear();
        paneComment.setVisible(true);
    }

    @FXML
    private void onCancelCommentAction() {
        paneComment.setVisible(false);
    }

    @FXML
    private void onSubmitCommentAction() {
        if (areaComment.getText().isEmpty()) {
            Dialogs.showError("评价栏为空，请输入您的评价");
            return;
        }
        int star = choiceStar.getSelectionModel().getSelectedIndex() + 1;
        String comment = areaComment.getText();

        ResultMessage rs = controller.rankOrder(new OrderRankVO(selectedOrder.id, LocalDate.now(), star, comment));
        if (rs.result == ResultMessage.RESULT_SUCCESS) {
            Dialogs.showInfo("评价订单成功");
            loadOrders();
            onCancelCommentAction();
        } else {
            Dialogs.showError("评价订单失败: " + rs.message);
        }
    }

}
