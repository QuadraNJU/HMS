package nju.quadra.hms.ui.webMarketerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import nju.quadra.hms.ui.mainUI.MainView;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/29.
 */
public class WebMarketerNavigation extends Parent {

    private MainView mainView;

    public WebMarketerNavigation(MainView main) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("navigation.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());
        this.mainView = main;
    }

    @FXML
    protected void onLabelClicked(MouseEvent t) throws IOException {
        Node source = (Node) t.getSource();
        // ignore when an active label was clicked
        if (source.getStyleClass().indexOf("nav-active") >= 0) {
            return;
        }
        // remove all active style first
        Parent vBox = (Parent) this.getChildren().get(0);
        for (Node anchorPane : vBox.getChildrenUnmodifiable()) {
            ((Parent) anchorPane).getChildrenUnmodifiable().get(0).getStyleClass().clear();
        }
        // add active style
        source.getStyleClass().add("nav-active");
        // process logic
        switch (source.getId()) {
            case "order":
                mainView.loadView(new OrderSearchView());
                break;
            case "promotion":
                mainView.loadView(new WebsitePromotionView());
                break;
            case "topup":
                mainView.loadView(new CreditTopupView());
                break;
        }
    }

}
