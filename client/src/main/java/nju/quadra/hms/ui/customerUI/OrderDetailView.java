package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.controller.HotelRoomController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Created by RaUkonn on 2016/12/7.
 */
public class OrderDetailView extends Parent {
    @FXML
    private Label labelOrderNumber, labelState, labelHotel, labelDate, labelRoom, labelPersons, labelChildren, labelPrice, labelStars, labelComment;
    private HotelController hotelController;
    private HotelRoomController hotelRoomController;

    public OrderDetailView(OrderVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderdetail.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        hotelController = new HotelController();
        hotelRoomController = new HotelRoomController();

        HotelVO hotelVO = hotelController.getDetail(vo.hotelId);
        HotelRoomVO hotelRoomVO = hotelRoomController.getById(vo.roomId);

        labelOrderNumber.setText(vo.id + "");
        labelState.setText(vo.state.toString());
        labelHotel.setText(hotelVO.name);
        labelDate.setText(vo.startDate.format(DateTimeFormatter.ofPattern("uuuu/MM/dd ")) + " - " + vo.endDate.format(DateTimeFormatter.ofPattern("uuuu/MM/dd ")));
        labelRoom.setText(hotelRoomVO.name + ", " + vo.roomCount + "间");
        labelPersons.setText(vo.printPersons());
        labelChildren.setText(vo.hasChildren? "是": "否");
        labelPrice.setText("¥ " + vo.price);
        if(vo.state.equals(OrderState.RANKED)) {
            labelStars.setText(vo.printRank());
            labelComment.setText(vo.comment);
        } else {
            labelStars.setText("暂无评分");
            labelComment.setText("暂无评价");
        }
    }

    @FXML
    private void onBackAction() {
        this.getChildren().clear();
    }
}
