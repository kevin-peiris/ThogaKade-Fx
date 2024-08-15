package controller;

import db.ThogakadePOS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class NoticeFormController implements Initializable {
    private static NoticeFormController instance;

    @FXML
    private Label lblNotice;

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

    public static void setMessage(String message){
        if (instance != null) {
            instance.lblNotice.setText(message); // Set the label text using the static reference
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
    }
}
