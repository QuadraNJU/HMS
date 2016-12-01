package nju.quadra.hms.ui.hotelStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelPromotionItem extends Parent {

    private HotelPromotionVO vo;

    @FXML
    private Label labelDate, labelName;

    public HotelPromotionItem(HotelPromotionVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotionitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.vo = vo;
        if (vo != null) {
            labelDate.setText(vo.startTime.format(DateTimeFormatter.ofPattern("uuuu/MM/dd")) + " - " + vo.endTime.format(DateTimeFormatter.ofPattern("uuuu/MM/dd")));
            labelName.setText(vo.name);
        }
    }

}
