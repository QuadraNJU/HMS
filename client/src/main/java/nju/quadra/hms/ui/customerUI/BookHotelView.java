package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.*;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by admin on 2016/12/5.
 */
public class BookHotelView extends Parent {
    private HotelRoomController hotelRoomController;
    private  HotelController hotelController;
    private OrderController orderController;
    private ArrayList<HotelRoomVO> rooms;
    private PriceVO price;
    private int hotelId;
    private HotelPromotionVO hotelPromotion;
    private WebsitePromotionVO websitePromotion;
    @FXML
    Pane pane;
    @FXML
    Label labelHotelName,labelOriginalPrice, labelHotelPromotion, labelWebPromotion, labelFinalPrice;
    @FXML
    DatePicker dateStart, dateEnd;
    @FXML
    ComboBox<HotelRoomVO> comboRoomType;
    @FXML
    TextField textRoomNumber, textPersons;
    @FXML
    RadioButton radioHasChildren, radioNoChildren;
    ToggleGroup toggleGroup;

    public BookHotelView(int hotelId) throws IOException {
        this.hotelId = hotelId;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookhotelview.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        hotelRoomController = new HotelRoomController();
        hotelController = new HotelController();
        orderController = new OrderController();
        rooms = hotelRoomController.getAll(hotelId);
        labelHotelName.setText(hotelController.getDetail(hotelId).name);
        comboRoomType.getItems().addAll(rooms);
        comboRoomType.setValue(rooms.get(0));
        toggleGroup = new ToggleGroup();
        radioHasChildren.setToggleGroup(toggleGroup);
        radioNoChildren.setToggleGroup(toggleGroup);
        radioHasChildren.setSelected(true);
        textRoomNumber.setText("0");


        loadPrice();
    }

    private void changePersons(String names) {textPersons.setText(names);}

    @FXML
    private void loadPrice() {
        HotelRoomVO selectedRoom = comboRoomType.getValue();
        int number;
        try {
            number = Integer.parseInt(textRoomNumber.getText());
            if(number < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Dialogs.showError("请输入正整数");
            return;
        }

        double originalPrice = number * selectedRoom.price;
        OrderVO order = new OrderVO(0, HttpClient.session.username, hotelId, dateStart.getValue(), dateEnd.getValue(), selectedRoom.id, number, 0, null, radioHasChildren.isSelected()? true: false, originalPrice, OrderState.BOOKED, 0, null);
        price = orderController.getPrice(order);
        DecimalFormat df = new DecimalFormat("#.00");
        labelOriginalPrice.setText("¥" + df.format(price.originalPrice));
        if(price.hotelPromotion != null)
            labelHotelPromotion.setText("¥" + df.format(price.hotelPromotion.promotion));
        else
            labelHotelPromotion.setText("无可用优惠");
        if(price.websitePromotion != null)
        labelWebPromotion.setText("¥" + df.format(price.websitePromotion.promotion));
        else
            labelWebPromotion.setText("无可用优惠");
        labelFinalPrice.setText("¥" + df.format(price.finalPrice));
    }

    @FXML
    private void onCancelAction() {
        this.getChildren().clear();
    }

    @FXML
    private void onSubmitAction() throws IOException{
        //todo
    }

    @FXML
    private void onChangePersons() throws IOException {
        this.getChildren().add(new NameListView(textPersons.getText(), this::changePersons));
    }
}
