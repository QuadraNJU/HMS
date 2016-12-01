package nju.quadra.hms.ui.hotelStaff;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.vo.HotelRoomVO;

import java.io.IOException;
import java.util.ArrayList;

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
    private TableView<HotelRoomProperty> tableView;

    private void loadRooms() {
        rooms = controller.getHotelRooms();
        tableView.getItems().clear();
        for (HotelRoomVO vo : rooms) {
            tableView.getItems().add(new HotelRoomProperty(vo));
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