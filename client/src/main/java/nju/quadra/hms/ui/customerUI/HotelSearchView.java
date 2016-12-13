package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelSearchVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelSearchView extends Parent {

    private final CustomerController controller = new CustomerController();
    private ArrayList<AreaVO> areas;
    private ArrayList<HotelSearchVO> hotelList = new ArrayList<>();

    public HotelSearchView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hotelsearch.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(vo -> vo.cityName.equals(newValue)).map(vo -> vo.areaName).toArray());
            choiceArea.getSelectionModel().select(0);
        });

        choiceRank1.getItems().addAll(0, 1, 2, 3, 4, 5);
        choiceRank2.getItems().addAll(0, 1, 2, 3, 4, 5);
        choiceSort.getItems().addAll(HotelSort.values());
        resetOptions();

        loadAreas();
    }

    private void loadAreas() {
        areas = controller.getAllArea();
        for (AreaVO vo : areas) {
            if (choiceCity.getItems().indexOf(vo.cityName) < 0) {
                choiceCity.getItems().add(vo.cityName);
            }
        }
    }

    private void resetOptions() {
        editKeyword.clear();
        CheckBox[] checkStars = {checkStar1, checkStar2, checkStar3, checkStar4, checkStar5};
        for (CheckBox checkBox : checkStars) {
            checkBox.setSelected(true);
        }
        choiceRank1.setValue(0);
        choiceRank2.setValue(5);
        choiceSort.setValue(HotelSort.DEFAULT);
        checkOrderedOnly.setSelected(false);
    }

    private void filterAndShow() throws IOException {
        vBox.getChildren().clear();
        if (hotelList != null) {
            ArrayList<HotelSearchVO> filteredList = new ArrayList<>();
            for (HotelSearchVO vo : hotelList) {
                if (!editKeyword.getText().trim().isEmpty() && !vo.name.contains(editKeyword.getText().trim()))
                    continue;
                int star = vo.getStar();
                CheckBox[] checkStars = {checkStar1, checkStar2, checkStar3, checkStar4, checkStar5};
                if (star > 0 && !checkStars[star-1].isSelected())
                    continue;
                double rank = vo.getAverageRank();
                if (rank < choiceRank1.getValue() || rank > choiceRank2.getValue())
                    continue;
                if (checkOrderedOnly.isSelected() && vo.orders.size() == 0)
                    continue;
                filteredList.add(vo);
            }
            switch (choiceSort.getValue()) {
                case PRICE_ASC:
                    filteredList.sort((vo1, vo2) -> (vo1.getMinPrice() <= vo2.getMinPrice()) ? -1 : 1);
                    break;
                case PRICE_DESC:
                    filteredList.sort((vo1, vo2) -> (vo1.getMaxPrice() >= vo2.getMaxPrice()) ? -1 : 1);
                    break;
                case STAR_ASC:
                    filteredList.sort(Comparator.comparingInt(HotelSearchVO::getStar));
                    break;
                case STAR_DESC:
                    filteredList.sort((vo1, vo2) -> vo2.getStar() - vo1.getStar());
                    break;
                case RANK_DESC:
                    filteredList.sort((vo1, vo2) -> (vo1.getAverageRank() >= vo2.getAverageRank()) ? -1 : 1);
                    break;
            }
            for (HotelSearchVO vo : filteredList) {
                vBox.getChildren().add(new HotelSearchItem(this, vo));
            }
        }
    }

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }

    @FXML
    private VBox vBox;
    @FXML
    private Pane pane, paneOption;
    @FXML
    private ChoiceBox choiceCity, choiceArea;
    @FXML
    private TextField editKeyword;
    @FXML
    private CheckBox checkStar1, checkStar2, checkStar3, checkStar4, checkStar5, checkOrderedOnly;
    @FXML
    private ChoiceBox<Integer> choiceRank1, choiceRank2;
    @FXML
    private ChoiceBox<HotelSort> choiceSort;

    @FXML
    private void onSearchAction() throws Exception {
        AreaVO area = areas.stream().filter(vo -> vo.areaName.equals(choiceArea.getValue()) && vo.cityName.equals(choiceCity.getValue())).findFirst().get();
        hotelList = controller.searchHotel(area.id, HttpClient.session.username);
        vBox.getChildren().clear();
        resetOptions();
        filterAndShow();
    }

    @FXML
    private void onOptionAction() {
        paneOption.setVisible(true);
    }

    @FXML
    private void onFilterAction() throws IOException {
        filterAndShow();
        paneOption.setVisible(false);
    }

    enum HotelSort {
        DEFAULT("默认排序"),
        PRICE_ASC("价格由低到高"),
        PRICE_DESC("价格由高到低"),
        STAR_ASC("星级由低到高"),
        STAR_DESC("星级由高到低"),
        RANK_DESC("评分由高到低");

        private final String displayName;

        HotelSort(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return this.displayName;
        }
    }

}
