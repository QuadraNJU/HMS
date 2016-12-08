package nju.quadra.hms.ui.webMarketerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.quadra.hms.controller.HotelStaffController;
import nju.quadra.hms.controller.WebMarketerController;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.hotelStaffUI.HotelPromotionEditView;
import nju.quadra.hms.ui.hotelStaffUI.HotelPromotionItem;
import nju.quadra.hms.vo.HotelPromotionVO;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Rebecca on 2016/12/5.
 */
public class WebsitePromotionView extends Parent {

    private WebMarketerController controller = new WebMarketerController();
    private ArrayList<WebsitePromotionVO> promotions;

    public WebsitePromotionView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("promotion.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        loadPromotion();
    }

    @FXML
    private Pane pane;
    @FXML
    private VBox vBox;

    public void loadPromotion() throws IOException {
        promotions = controller.getWebsitePromotion();
        if (promotions != null) {
            vBox.getChildren().clear();
            for (WebsitePromotionVO vo : promotions) {
                vBox.getChildren().add(new WebsitePromotionItem(this, vo, controller));
            }
        }
    }

    public void loadView(Node node) {
        pane.getChildren().add(node);
    }

    @FXML
    protected void onAddAction() throws IOException {
        loadView(new WebsitePromotionEditView(null, controller, false, this::loadPromotion));
    }

}