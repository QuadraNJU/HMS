package nju.quadra.hms.ui.hotelStaff;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.HotelRoomVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by adn55 on 2016/12/1.
 */
public class HotelRoomView extends Parent {

    private HotelStaffController controller = new HotelStaffController(HttpClient.session.username);
    private ArrayList<HotelRoomVO> rooms;

    public HotelRoomView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("total"));

        loadRooms();
    }

    @FXML
    Pane pane;
    @FXML
    private TableView<HotelRoomProperty> tableView;

    private void loadRooms() {
        rooms = controller.getHotelRooms();
        tableView.getItems().clear();
        for (HotelRoomVO vo : rooms) {
            tableView.getItems().add(new HotelRoomProperty(vo));
        }
    }

    @FXML
    protected void onAddAction() throws IOException {
        pane.getChildren().add(new HotelRoomEditView(null, controller, this::loadRooms));
    }

    @FXML
    protected void onModifyAction() throws IOException {
        HotelRoomProperty selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            for (HotelRoomVO vo : rooms) {
                if (vo.id == selected.id) {
                    pane.getChildren().add(new HotelRoomEditView(vo, controller, this::loadRooms));
                    break;
                }
            }
        }
    }

    @FXML
    protected void onDeleteAction() {
        HotelRoomProperty selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("确认");
            alert.setHeaderText(null);
            alert.setContentText("是否确认删除此客房信息?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
                ResultMessage result = controller.deleteHotelRoom(selected.id);
                if (result.result == ResultMessage.RESULT_SUCCESS) {
                    Dialogs.showInfo("删除客房信息成功");
                } else {
                    Dialogs.showError("删除客房信息失败: " + result.message);
                }
                loadRooms();
            }
        }
    }

    public class HotelRoomProperty {
        private int id;
        private SimpleStringProperty name;
        private SimpleStringProperty price;
        private SimpleStringProperty total;

        public HotelRoomProperty(HotelRoomVO vo) {
            this.id = vo.id;
            this.name = new SimpleStringProperty(vo.name);
            this.price = new SimpleStringProperty(vo.price + "");
            this.total = new SimpleStringProperty(vo.total + "");
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public String getPrice() {
            return price.get();
        }

        public SimpleStringProperty priceProperty() {
            return price;
        }

        public String getTotal() {
            return total.get();
        }

        public SimpleStringProperty totalProperty() {
            return total;
        }
    }

}