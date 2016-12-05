package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import nju.quadra.hms.vo.OrderVO;

import java.io.IOException;

/**
 * Created by admin on 2016/12/5.
 */
public class CommentItemView extends Parent {

    @FXML
    Label labelTime, labelStar, labelComment;

    public CommentItemView(OrderVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("commentitem.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        labelTime.setText(vo.endDate.toString());
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < vo.rank; i++) sb.append('★');
        labelStar.setText(sb.toString());
        labelComment.setText(vo.comment);
    }
}
