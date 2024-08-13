package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    void btnAddCustomerFormOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/add_customer_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteCustomerFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchCustomerFormOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewCustomerFormOnAction(ActionEvent event) {

    }

    public void btnUpdateCustomerFormOnAction(ActionEvent actionEvent) {

    }
}
