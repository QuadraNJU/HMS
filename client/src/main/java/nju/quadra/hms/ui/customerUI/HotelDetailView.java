package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.controller.HotelRoomController;
import nju.quadra.hms.controller.OrderController;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/12/5.
 */
public class HotelDetailView extends Parent {
    @FXML
    Label labelHotelName, labelAddress, labelDescription, labelFacilities, labelRooms, labelComment;
    @FXML
    ScrollPane scrollComment, scrollHistory;

    private HotelController hotelController;
    private HotelRoomController hotelRoomController;
    private OrderController orderController;
    private ArrayList<HotelRoomVO> rooms;
    private ArrayList<OrderVO> historyOrders;
    private HotelVO hotelVO;

    public HotelDetailView(int hotelId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hoteldetail.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        hotelController = new HotelController();
        orderController = new OrderController();
        hotelRoomController = new HotelRoomController();
        hotelVO = hotelController.getDetail(hotelId);
        rooms = hotelRoomController.getAll(hotelId);

        labelHotelName.setText(hotelVO.name);
        labelAddress.setText(hotelVO.address);
        labelDescription.setText(hotelVO.description);
        labelFacilities.setText(hotelVO.facilities);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < rooms.size(); i++) {
            sb.append(rooms.get(i).name + "(" + rooms.get(i).price + "元/晚)");
            if(i != rooms.size()-1) sb.append("，");

        }
        labelRooms.setText(sb.toString());
    }

    @FXML
    public void onCancelAction() {
        this.getChildren().clear();
    }

    @FXML
    public void onOrderAction() {
        //TODO
    }
}
