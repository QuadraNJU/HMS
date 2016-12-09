package nju.quadra.hms.ui.webMarketerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.WebMarketerController;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rebecca on 2016/12/5.
 */
class WebsitePromotionEditView extends Parent {

    private WebMarketerController controller;
    private WebsitePromotionVO websitePromotionVO;
    private SuccessHandler onSuccess;
    private ArrayList<AreaVO> areas;

    @FXML
    private
    TextField editName;
    @FXML
    private
    TextField editPromotion;
    @FXML
    private
    TextField editLevelCredit;
    @FXML
    private TextField editLevelPromo;
    @FXML
    private
    ChoiceBox<WebsitePromotionType> choiceType;
    @FXML
    private
    DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private
    Button btnSave;
    @FXML
    private
    Pane paneAreaLevel;
    @FXML
    private Pane paneLevel;
    @FXML
    private
    RadioButton radioAllArea;
    @FXML
    private RadioButton radioSelectArea;
    @FXML
    private
    HBox hBox;
    @FXML
    private
    ChoiceBox choiceCity;
    @FXML
    private ChoiceBox choiceArea;
    @FXML
    private
    Label labelLevelNum;
    @FXML
    private
    TableView<UserLevel> tableLevel;

    public WebsitePromotionEditView(WebsitePromotionVO vo, WebMarketerController controller, boolean readOnly, SuccessHandler successHandler) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotionedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.websitePromotionVO = vo;
        this.controller = controller;
        this.onSuccess = successHandler;

        choiceType.getItems().addAll(WebsitePromotionType.values());
        choiceType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(WebsitePromotionType.LEVEL_PROMOTION)) {
                paneAreaLevel.setVisible(true);
            } else {
                paneAreaLevel.setVisible(false);
                paneLevel.setVisible(false);
            }
        });
        choiceType.getSelectionModel().select(0);

        radioAllArea.fire();
        choiceCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceArea.getItems().clear();
            choiceArea.getItems().addAll(areas.stream().filter(area -> area.cityName.equals(newValue)).map(area -> area.areaName).toArray());
        });
        loadAreas();

        tableLevel.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("credit"));
        tableLevel.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("promotion"));

        if (vo != null) {
            editName.setText(vo.name);
            choiceType.setValue(vo.type);
            dateStart.setValue(vo.startTime);
            dateEnd.setValue(vo.endTime);
            editPromotion.setText(vo.promotion + "");
            if (vo.type.equals(WebsitePromotionType.LEVEL_PROMOTION)) {
                if (vo.areaId <= 0) {
                    radioAllArea.fire();
                } else {
                    for (AreaVO area : areas) {
                        if (area.id == vo.areaId) {
                            choiceCity.setValue(area.cityName);
                            choiceArea.setValue(area.areaName);
                            break;
                        }
                    }
                    radioSelectArea.fire();
                }
                for (double credit : vo.memberLevel.keySet()) {
                    tableLevel.getItems().add(new UserLevel(credit, vo.memberLevel.get(credit)));
                }
                labelLevelNum.setText("共有 " + tableLevel.getItems().size() + " 个等级");
            }
        }
        if (readOnly) {
            editName.setEditable(false);
            choiceType.setDisable(true);
            dateStart.setDisable(true);
            dateEnd.setDisable(true);
            editPromotion.setEditable(false);
            btnSave.setVisible(false);
            radioSelectArea.setDisable(true);
            radioAllArea.setDisable(true);
            choiceCity.setDisable(true);
            choiceArea.setDisable(true);
            tableLevel.setDisable(true);
            editLevelCredit.setDisable(true);
            editLevelPromo.setDisable(true);
        }
    }

    private void loadAreas() {
        areas = controller.getAllArea();
        if (areas != null) {
            for (AreaVO vo : areas) {
                if (choiceCity.getItems().indexOf(vo.cityName) < 0) {
                    choiceCity.getItems().add(vo.cityName);
                }
            }
        }
    }

    @FXML
    protected void onSaveAction() throws IOException {
        String name = editName.getText();
        WebsitePromotionType type = choiceType.getValue();
        LocalDate startTime = dateStart.getValue();
        LocalDate endTime = dateEnd.getValue();
        double promotion;
        int areaId = -1;
        HashMap<Double, Double> level = null;

        try {
            promotion = Double.parseDouble(editPromotion.getText());
        } catch (Exception e) {
            Dialogs.showError("数值格式不正确，请重新填写");
            return;
        }

        if (type.equals(WebsitePromotionType.LEVEL_PROMOTION)) {
            if (radioSelectArea.isSelected()) {
                for (AreaVO area : areas) {
                    if (area.cityName.equals(choiceCity.getValue()) && area.areaName.equals(choiceArea.getValue())) {
                        areaId = area.id;
                        break;
                    }
                }
            }
            level = new HashMap<>();
            for (UserLevel lv : tableLevel.getItems()) {
                level.put(lv.credit, lv.promotion);
            }
        }

        if (websitePromotionVO == null) {
            // add
            ResultMessage result = controller.addWebsitePromotion(new WebsitePromotionVO(0, name, type, startTime, endTime, promotion, areaId, level));
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("增加促销策略成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("增加促销策略失败: " + result.message);
            }
        } else {
            // modify
            websitePromotionVO.name = name;
            websitePromotionVO.type = type;
            websitePromotionVO.startTime = startTime;
            websitePromotionVO.endTime = endTime;
            websitePromotionVO.promotion = promotion;
            if (type.equals(WebsitePromotionType.LEVEL_PROMOTION)) {
                websitePromotionVO.areaId = areaId;
                websitePromotionVO.memberLevel = level;
            }
            ResultMessage result = controller.modifyWebsitePromotion(websitePromotionVO);
            if (result.result == ResultMessage.RESULT_SUCCESS) {
                Dialogs.showInfo("修改促销策略成功");
                onSuccess.handle();
                onCancelAction();
            } else {
                Dialogs.showInfo("修改促销策略失败: " + result.message);
            }
        }
    }

    @FXML
    protected void onRadioAction() {
        if (radioSelectArea.isSelected()) {
            hBox.setVisible(true);
        } else {
            hBox.setDisable(false);
        }
    }

    @FXML
    protected void onEditLevelAction() {
        paneLevel.setVisible(true);
    }

    @FXML
    protected void onAddLevelAction() {
        try {
            double credit = Double.parseDouble(editLevelCredit.getText());
            double promo = Double.parseDouble(editLevelPromo.getText());
            if (credit < 0 || promo < 0 || promo > 1) {
                Dialogs.showError("数值范围不正确，请重新填写");
                return;
            }
            tableLevel.getItems().add(new UserLevel(credit, promo));
        } catch (Exception e) {
            Dialogs.showError("数值格式不正确，请重新填写");
        }
        labelLevelNum.setText("共有 " + tableLevel.getItems().size() + " 个等级");
    }

    @FXML
    protected void onDeleteLevelAction() {
        tableLevel.getItems().remove(tableLevel.getSelectionModel().getSelectedIndex());
        labelLevelNum.setText("共有 " + tableLevel.getItems().size() + " 个等级");
    }

    @FXML
    protected void onCloseLevelAction() {
        paneLevel.setVisible(false);
    }

    @FXML
    private void onCancelAction() {
        getChildren().clear();
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }

    public class UserLevel {
        private double credit;
        private double promotion;

        public UserLevel(double credit, double promotion) {
            this.credit = credit;
            this.promotion = promotion;
        }

        public double getCredit() {
            return credit;
        }

        public double getPromotion() {
            return promotion;
        }
    }

}
