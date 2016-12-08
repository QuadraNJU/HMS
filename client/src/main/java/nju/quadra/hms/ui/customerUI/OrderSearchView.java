package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.controller.OrderController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.OrderRankVO;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/12/7.
 */
public class OrderSearchView extends Parent {
    @FXML
    private Pane pane;
    @FXML
    private ComboBox<OrderState> comboOrderState;
    @FXML
    private DatePicker dateStart, dateEnd;
    @FXML
    private VBox vBox;

    /*Comment View Components*/
    @FXML
    private Pane paneComment;
    @FXML
    private Label labelOrderNumber, labelOrderState, labelHotel, labelDate, labelPrice;
    @FXML
    private ChoiceBox<String> choiceStar;
    @FXML
    private TextArea areaComment;
    /*End Comment View Components*/


    private int selectedOrderId;
    private OrderController orderController;
    private HotelController hotelController;

    public OrderSearchView() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ordersearch.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        selectedOrderId = -1;
        orderController = new OrderController();
        hotelController = new HotelController();
        comboOrderState.getItems().addAll(
                OrderState.BOOKED,
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

    protected void loadView(Node node) {
        pane.getChildren().add(node);
    }

    protected void loadCommentView(OrderVO vo) {
        selectedOrderId = vo.id;
        paneComment.setVisible(true);

        labelOrderNumber.setText("" + vo.id);
        labelOrderState.setText(vo.state.toString());
        labelHotel.setText(hotelController.getDetail(vo.hotelId).name);
        labelDate.setText(vo.startDate.toString());
        labelPrice.setText("" + vo.price);

        choiceStar.getItems().addAll(
                "★",
                "★★",
                "★★★",
                "★★★★",
                "★★★★★");
        choiceStar.setValue("★");
        areaComment.setPromptText("请输入评价");

        return;
    }

    @FXML
    private void onCancelCommentAction() throws Exception {
        paneComment.setVisible(false);

        labelOrderNumber.setText(null);
        labelOrderState.setText(null);
        labelHotel.setText(null);
        labelDate.setText(null);
        labelPrice.setText(null);
        choiceStar.getItems().clear();

        onSearchAction();

        return;
    }

    @FXML
    private void onSubmitCommentAction() throws Exception {
        if(areaComment.getText() == null) {
            Dialogs.showError("评价栏为空，请输入您的评价");
            return;
        }
        int star = choiceStar.getValue().length();
        String comment = areaComment.getText();

        ResultMessage rs = orderController.addRank(new OrderRankVO(selectedOrderId, LocalDate.now(), star, comment));
        if(rs.result == ResultMessage.RESULT_SUCCESS) {
            onCancelCommentAction();
        } else {
            Dialogs.showError(rs.message);
        }
    }


    @FXML
    protected void onSearchAction() throws IOException {
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
