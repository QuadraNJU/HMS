package nju.quadra.hms.ui.common;

import javafx.scene.control.Alert;

public class Dialogs {

    private static void showDialog(Alert.AlertType type, String title, String header, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showError(String message) {
        showDialog(Alert.AlertType.ERROR, "错误", null, message);
    }

    public static void showInfo(String message) {
        showDialog(Alert.AlertType.INFORMATION, "提示", null, message);
    }

}
