package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.HotelController;
import nju.quadra.hms.controller.HotelRoomController;
import nju.quadra.hms.controller.OrderController;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/12/5.
 */
public class HotelDetailView extends Parent {
    @FXML
    Label labelHotelName, labelAddress, labelDescription, labelFacilities, labelRooms, labelComment;
    @FXML
    ScrollPane scrollComment, scrollHistory;
    @FXML
    Pane pane;

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
        historyOrders = orderController.getByHotel(hotelId);

        loadBasicInfo();
        loadComments();
    }

    public void loadBasicInfo() {
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

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }

    public void loadComments() throws IOException{
        double avgRank = 0;
        int usableOrderCnt = 0;
        if(historyOrders != null && !historyOrders.isEmpty()) {
            for (OrderVO vo : historyOrders) {
                if (vo.rank != 0 && vo.comment != null) {
                    scrollComment.setContent(new CommentItemView(vo));
                    avgRank += vo.rank;
                    usableOrderCnt++;
                }
            }
            if (usableOrderCnt != 0) avgRank /= usableOrderCnt;

            DecimalFormat df = new DecimalFormat("#.0");
            labelComment.setText("评价（" + df.format(avgRank) + " /5.0");
        }
    }

    @FXML
    public void onCancelAction() {
        this.getChildren().clear();
    }

    @FXML
    public void onOrderAction() throws IOException{
        this.loadView(new BookHotelView(hotelVO.id));
    }
}
