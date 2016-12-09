package nju.quadra.hms.ui.hotelStaffUI;

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
class HotelRoomView extends Parent {

    private final HotelStaffController controller = new HotelStaffController(HttpClient.session.username);

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
    private
    Pane pane;
    @FXML
    private TableView<HotelRoomProperty> tableView;

    private void loadRooms() {
        ArrayList<HotelRoomVO> rooms = controller.getHotelRooms();
        if (rooms != null) {
            tableView.getItems().clear();
            for (HotelRoomVO vo : rooms) {
                tableView.getItems().add(new HotelRoomProperty(vo));
            }
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
            pane.getChildren().add(new HotelRoomEditView(selected.vo, controller, this::loadRooms));
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
                ResultMessage result = controller.deleteHotelRoom(selected.vo.id);
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
        public final HotelRoomVO vo;

        public HotelRoomProperty(HotelRoomVO vo) {
            this.vo = vo;
        }

        public String getName() {
            return vo.name;
        }

        public String getPrice() {
            return vo.price + "";
        }

        public String getTotal() {
            return vo.total + "";
        }
    }

}