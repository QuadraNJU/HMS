package nju.quadra.hms.ui.webMarketerUI;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.Pane;
import nju.quadra.hms.controller.WebMarketerController;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Rebecca on 2016/12/5.
 */
public class WebsitePromotionEditView extends Parent {

    private WebMarketerController controller;
    private WebsitePromotionVO websitePromotionVO;
    private SuccessHandler onSuccess;
    //private ArrayList<String> selectedCompany = new ArrayList<>();
    //private HashMap<String, ObservableValue<Boolean>> companySelection = new HashMap<>();

    @FXML
    TextField editName, editPromotion;
    @FXML
    ChoiceBox<WebsitePromotionType> choiceType;
    @FXML
    DatePicker dateStart, dateEnd;
    @FXML
    Button btnSave;
    //@FXML
    //Pane paneCompany, paneSelectCompany;
    //@FXML
    //Label labelCompany;
    //@FXML
    //ListView<String> listCompany;

    public WebsitePromotionEditView(WebsitePromotionVO vo, WebMarketerController controller, boolean readOnly, SuccessHandler successHandler) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotionedit.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        choiceType.getItems().addAll(WebsitePromotionType.values());
        choiceType.getSelectionModel().select(0);
        /*
        choiceType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(WebsitePromotionType.COMPANY_PROMOTION)) {
                paneCompany.setVisible(true);
            } else {
                paneCompany.setVisible(false);
            }
        });
        listCompany.setCellFactory(CheckBoxListCell.forListView(item -> companySelection.get(item)));
        */
        this.websitePromotionVO = vo;
        this.controller = controller;
        this.onSuccess = successHandler;
        if (vo != null) {
            editName.setText(vo.name);
            choiceType.setValue(vo.type);
            dateStart.setValue(vo.startTime);
            dateEnd.setValue(vo.endTime);
            editPromotion.setText(vo.promotion + "");
            // TODO
        }
        if (readOnly) {
            editName.setEditable(false);
            choiceType.setDisable(true);
            dateStart.setDisable(true);
            dateEnd.setDisable(true);
            editPromotion.setEditable(false);
            btnSave.setVisible(false);
        }
    }

    @FXML
    protected void onSaveAction() throws IOException {
        String name;
        WebsitePromotionType type;
        LocalDate startTime;
        LocalDate endTime;
        double promotion;
        try {
            name = editName.getText();
            type = choiceType.getValue();
            startTime = dateStart.getValue();
            endTime = dateEnd.getValue();
            promotion = Double.parseDouble(editPromotion.getText());
        } catch (Exception e) {
            Dialogs.showError("数值格式不正确，请重新填写");
            return;
        }

        if (websitePromotionVO == null) {
            // add
            //HashMap<Double, Double> memberLevel = new HashMap<0.0 , 0.0>();
            ResultMessage result = controller.addWebsitePromotion(new WebsitePromotionVO(0, name, type, startTime, endTime, promotion, 0, null));
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
            /*
            if (type.equals(WebsitePromotionType.LEVEL_PROMOTION)) {
                websitePromotionVO.memberLevel = ;
                websitePromotionVO.level = selectedCompany;
            }
            */
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
    protected void onOpenSelectorAction() {

    }

    @FXML
    protected void onCloseSelectorAction() {

    }

    @FXML
    protected void onCancelAction() {
        getChildren().clear();
    }

    interface SuccessHandler {
        void handle() throws IOException;
    }

}
