package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.vo.HotelSearchVO;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelSearchItem extends Parent {

    private HotelSearchView parent;
    private HotelSearchVO vo;

    @FXML
    Label labelName, labelInfo;
    @FXML
    Button btnDetail, btnOrder;

    public HotelSearchItem(HotelSearchView parent, HotelSearchVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelsearchitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.parent = parent;
        this.vo = vo;
        if (vo != null) {
            labelName.setText(vo.name);
            String info = vo.star + " ";
            double rank = Math.round(vo.getAverageRank());
            for (int i = 1; i <= 5; i++) {
                if (i <= rank) {
                    info += "★";
                } else {
                    info += "☆";
                }
            }
            info += " " + vo.getAverageRank() + "/5.0 ";
            if (vo.orders.size() > 0) {
                long normalCount = vo.orders.stream().filter(order -> !order.state.equals(OrderState.DELAYED) && !order.state.equals(OrderState.UNDO)).count();
                long delayedCount = vo.orders.stream().filter(order -> order.state.equals(OrderState.DELAYED)).count();
                long undoCount = vo.orders.stream().filter(order -> order.state.equals(OrderState.UNDO)).count();
                info += "曾预定过（"
                        + ((normalCount > 0) ? "正常 " + normalCount + " " : "")
                        + ((delayedCount > 0) ? "异常 " + delayedCount + " " : "")
                        + ((undoCount > 0) ? "撤销 " + undoCount + " " : "")
                        + "）";
            }
            labelInfo.setText(info);
        }
    }

    @FXML
    public void onDetailAction() throws IOException {
        parent.loadView(new HotelDetailView(parent, vo));
    }

    @FXML
    public void onOrderAction() throws IOException {
        parent.loadView(new BookHotelView(vo.id));
    }
}
