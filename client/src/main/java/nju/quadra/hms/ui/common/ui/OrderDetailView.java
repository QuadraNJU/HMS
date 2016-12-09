package nju.quadra.hms.ui.common.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.vo.OrderDetailVO;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Created by RaUkonn on 2016/12/7.
 */
public class OrderDetailView extends Parent {

    @FXML
    private Label labelOrderNumber, labelState, labelHotel, labelDate, labelRoom, labelPersons, labelChildren, labelPrice, labelStars;

    public OrderDetailView(OrderDetailVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderdetail.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        labelOrderNumber.setText(vo.id + "");
        labelState.setText(vo.state.toString());
        labelHotel.setText(vo.hotel.name);
        labelDate.setText(vo.startDate.format(DateTimeFormatter.ofPattern("uuuu/MM/dd ")) + " - " + vo.endDate.format(DateTimeFormatter.ofPattern("uuuu/MM/dd ")));
        labelRoom.setText(vo.room.name + ", " + vo.roomCount + "间");
        labelPersons.setText(vo.persons.stream().collect(Collectors.joining(", ")));
        labelChildren.setText(vo.hasChildren ? "是": "否");
        labelPrice.setText("¥ " + vo.price);
        if(vo.state.equals(OrderState.RANKED)) {
            labelStars.setText(vo.printRank());
        } else {
            labelStars.setText("暂无评价");
        }
    }

    @FXML
    private void onBackAction() {
        this.getChildren().clear();
    }
}
