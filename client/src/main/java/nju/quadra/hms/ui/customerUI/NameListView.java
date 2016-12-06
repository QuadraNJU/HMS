package nju.quadra.hms.ui.customerUI;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import nju.quadra.hms.ui.common.Dialogs;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/12/6.
 */
public class NameListView extends Parent {
    @FXML
    private ListView<String> listName;
    @FXML
    private TextField textName;
    private ArrayList<String> arrayNames = new ArrayList<>();
    private SuccessHandler onSuccess;

    public NameListView(String list, SuccessHandler successHandler) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("namelist.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        onSuccess = successHandler;
        if(list != null && !list.equals("")){
            String[] names = list.split("、");
            for(String s: names) arrayNames.add(s);
        }
    }

    @FXML
    private void loadNameToListView() {
        listName.getItems().clear();
        listName.getItems().setAll(arrayNames);
    }

    @FXML
    private void onAddNameAction() {
        String name = textName.getText();
        if(name != null && name.length() != 0) {
            if (name.contains("、") || name.contains("|")) {
                Dialogs.showInfo("请输入英文或者汉字，不要输入'、'，'|'等符号！");
            } else if (name.length() > 8) {
                Dialogs.showInfo("输入的长度不能大于8个汉字或英文字母，请重新输入");
            }

            arrayNames.add(name);
            textName.clear();
            loadNameToListView();
        }
    }

    @FXML
    private void onDeleteNameAction() {
        if(listName.getSelectionModel().getSelectedItem() != null) {
            String selected = listName.getSelectionModel().getSelectedItem();
            arrayNames.remove(selected);
            listName.getItems().remove(selected);
        } else {
            Dialogs.showInfo("请选择需要删除的姓名");
        }
    }

    @FXML
    private void onSubmitAction() throws IOException {
        StringBuilder sb = new StringBuilder();
        for(String s: arrayNames) sb.append(s + '、');
        sb.deleteCharAt(sb.length() - 1);
        onSuccess.handle(sb.toString());
        this.getChildren().clear();
        onSuccess.handle(sb.toString());
    }

    interface SuccessHandler {
        void handle(String s) throws IOException;
    }
}
