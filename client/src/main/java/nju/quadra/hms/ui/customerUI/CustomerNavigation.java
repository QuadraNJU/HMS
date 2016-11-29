package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/29.
 */
public class CustomerNavigation extends Parent {

    public CustomerNavigation() throws IOException {
        FXMLLoader navLoader = new FXMLLoader(getClass().getResource("navigation.fxml"));
        navLoader.setController(this);
        this.getChildren().add(navLoader.load());
    }

    @FXML
    protected void onLabelClicked(MouseEvent t) {
        Node source = (Node) t.getSource();
        // remove all active style first
        Parent vBox = (Parent) this.getChildren().get(0);
        for (Node anchorPane : vBox.getChildrenUnmodifiable()) {
            ((Parent) anchorPane).getChildrenUnmodifiable().get(0).getStyleClass().clear();
        }
        // add active style
        source.getStyleClass().add("nav-active");
        // process logic
        System.out.println(source.getId());
    }

}
