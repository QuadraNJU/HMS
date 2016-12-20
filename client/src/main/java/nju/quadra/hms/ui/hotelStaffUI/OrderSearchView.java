package nju.quadra.hms.ui.hotelStaffUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.vo.OrderDetailVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

class OrderSearchView extends Parent {

    @FXML
    private Pane pane;
    @FXML
    private ChoiceBox choiceOrderState;
    @FXML
    private DatePicker dateStart, dateEnd;
    @FXML
    private VBox vBox;

    private final HotelStaffController controller = new HotelStaffController(HttpClient.session.username);
    private ArrayList<OrderDetailVO> orders;

    public OrderSearchView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearch.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceOrderState.getItems().add("所有订单状态");
        choiceOrderState.getItems().addAll(OrderState.values());
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
        this.orders = controller.getOrders();
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
                // e.printStackTrace();
            }
        }
    }

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }

}
