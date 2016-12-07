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
import java.time.LocalDate;
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
    private OrderVO order;
    private int hotelId;
    private HotelPromotionVO hotelPromotion;
    private WebsitePromotionVO websitePromotion;
    private HotelRoomVO selectedRoom;
    private int number;
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
    SuccessHandler onSuccess = null;

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

        order = new OrderVO(0, HttpClient.session.username, hotelId, dateStart.getValue(), dateEnd.getValue(), 0, 0, 0, null, false, 0, OrderState.BOOKED, 0, null);

        loadPrice();
    }

    public BookHotelView(int hotelId, SuccessHandler onSuccess) throws IOException {
        this(hotelId);
        this.onSuccess = onSuccess;
    }

    private void changePersons(String names) {
        textPersons.setText(names);
    }

    @FXML
    private void loadPrice() {
        selectedRoom = comboRoomType.getValue();
        try {
            number = Integer.parseInt(textRoomNumber.getText());
            if(number < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Dialogs.showError("请输入正整数");
            return;
        }

        double originalPrice = number * selectedRoom.price;
        order.roomId = selectedRoom.id;
        order.roomCount = number;
        order.price = originalPrice;
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
    private void onSubmitAction() throws IOException {
        if(order != null) {
            if(dateStart.getValue() != null && dateEnd.getValue() != null && Integer.parseInt(textRoomNumber.getText()) > 0 && textPersons.getText() != null) {
                if(dateStart.getValue().compareTo(dateEnd.getValue()) > -1) {
                    Dialogs.showError("订单开始的时间应该早于订单结束的时间，请重新输入");
                    return;
                }
                order.startDate = dateStart.getValue();
                order.endDate = dateEnd.getValue();
                order.roomId = selectedRoom.id;
                order.roomCount = number;
                if(!roomAvailable()) {
                    Dialogs.showError("选择的房间数量不足，请重新选择房间类型或修改房间数量");
                    return;
                }
                updatePersonsOfOrderVO();
                order.hasChildren = (radioHasChildren.isSelected() ? true : false);
                order.price = price.finalPrice;
                orderController.add(order);
                Dialogs.showInfo("添加订单成功");
                if(onSuccess != null) onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showError("输入信息不完全，请重新输入");
                return;
            }
        } else {
            Dialogs.showError("订单创建失败，请检查输入的合法性");
            return;
        }

    }

    private boolean roomAvailable() {
        LocalDate thisStart = dateStart.getValue();
        LocalDate thisEnd = dateEnd.getValue();
        ArrayList<OrderVO> selectedSameRoomArray = orderController.getByHotel(hotelId);
        selectedSameRoomArray.removeIf(vo -> vo.roomId != selectedRoom.id);
        int remainRoom = selectedRoom.total;
        for(OrderVO vo: selectedSameRoomArray) {
//            LocalDate tempStart = vo.startDate;
//            LocalDate tempEnd = vo.endDate;
//            int selectedSameRoomNumber = vo.roomCount;
//            if(tempEnd.compareTo(thisStart) > 0 || thisEnd.compareTo(tempStart) > 0) {remainRoom -= selectedSameRoomNumber;}
            remainRoom -= vo.roomCount;
        }
        if(remainRoom >= order.roomCount) return true;
        else return false;
    }

    private void updatePersonsOfOrderVO() {
        String[] ss = textPersons.getText().split("、");
        order.personCount = ss.length;
        ArrayList<String> arrPersons = new ArrayList<>();
        for(String s: ss) {
            arrPersons.add(s);
        }
        order.persons = arrPersons;
    }

    @FXML
    private void onChangePersons() throws IOException {
        this.getChildren().add(new NameListView(textPersons.getText(), this::changePersons));
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }
}
