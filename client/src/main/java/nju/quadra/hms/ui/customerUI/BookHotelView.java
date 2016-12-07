package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelSearchVO;
import nju.quadra.hms.vo.OrderVO;
import nju.quadra.hms.vo.PriceVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/12/5.
 */
public class BookHotelView extends Parent {

    private CustomerController controller = new CustomerController();
    private HotelSearchVO hotelSearchVO;
    private SuccessHandler onSuccess;

    @FXML
    private Label labelTitle;
    @FXML
    private DatePicker dateStart, dateEnd;
    @FXML
    private ChoiceBox<HotelRoomVO> choiceRoomType;
    @FXML
    private TextField textRoomNumber, textPersons, textName;
    @FXML
    private RadioButton radioHasChildren, radioNoChildren;
    @FXML
    private Pane panePerson;
    @FXML
    private ListView<String> listName;

    public BookHotelView(HotelSearchVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookhotelview.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        listName.itemsProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.stream().collect(Collectors.joining(", ")));
            textPersons.setText(newValue.stream().collect(Collectors.joining(", ")));
        });

        this.hotelSearchVO = vo;
        if (vo != null) {
            labelTitle.setText("预订 " + vo.name);
            dateStart.setValue(LocalDate.now());
            dateEnd.setValue(LocalDate.now().plusDays(1));
            choiceRoomType.getItems().addAll(vo.rooms);
            textRoomNumber.setText("1");
            radioNoChildren.setSelected(true);
        }
    }

    private void changePersons(String names) {
        textPersons.setText(names);
    }

    @FXML
    private void onCancelAction() {
        this.getChildren().clear();
    }

    @FXML
    private void onSubmitAction() throws IOException {
        int roomCount = 0;
        try {
            roomCount = Integer.parseInt(textRoomNumber.getText());
        } catch (NumberFormatException e) {
            Dialogs.showError("房间数量请输入正整数");
            return;
        }
        boolean hasChildren = false;
        if (radioHasChildren.isSelected()) {
            hasChildren = true;
        }
        OrderVO order = new OrderVO(0, HttpClient.session.username, hotelSearchVO.id,
                dateStart.getValue(), dateEnd.getValue(),
                choiceRoomType.getValue().id, roomCount,
                listName.getItems().size(), new ArrayList<>(listName.getItems()),
                hasChildren, 0.0, OrderState.BOOKED, 0, "");
        PriceVO priceVO = controller.getOrderPrice(order);
        if (priceVO.result.result != ResultMessage.RESULT_SUCCESS) {
            Dialogs.showError(priceVO.result.message);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("确认预订");
            alert.setHeaderText(null);
            String content = "订单原价：¥" + priceVO.originalPrice + "\n";
            if (priceVO.hotelPromotion != null) {
                content += "酒店促销策略：" + priceVO.hotelPromotion.name + " ( -¥" + priceVO.originalPrice * (1.0 - priceVO.hotelPromotion.promotion) + " )\n";
            }
            if (priceVO.websitePromotion != null) {
                content += "网站促销策略：" + priceVO.websitePromotion.name + " ( -¥" + priceVO.originalPrice * (1.0 - priceVO.websitePromotion.promotion) + " )\n";
            }
            content += "订单总价：¥" + priceVO.finalPrice + "\n\n是否确认预订？";
            alert.setContentText(content);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
                order.price = priceVO.finalPrice;
                ResultMessage result = controller.addOrder(order);
                if (result.result == ResultMessage.RESULT_SUCCESS) {
                    Dialogs.showInfo("预订成功");
                    if (onSuccess != null) {
                        onSuccess.handle();
                    }
                    onCancelAction();
                } else {
                    Dialogs.showError("预订失败: " + result.message);
                }
            }
        }
    }

    @FXML
    private void onChangePersons() throws IOException {
        panePerson.setVisible(true);
    }

    @FXML
    private void onAddNameAction() {
        String name = textName.getText();
        if (name != null && !name.isEmpty()) {
            listName.getItems().add(name);
        }
    }

    @FXML
    private void onDeleteNameAction() {
        if (listName.getSelectionModel().getSelectedIndex() >= 0) {
            listName.getItems().remove(listName.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    private void onSubmitNameAction() {
        panePerson.setVisible(false);
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }
}
