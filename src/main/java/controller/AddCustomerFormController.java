package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.ThogakadePOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {
    private static int customerCount=1;

    @FXML
    private DatePicker dob;

    @FXML
    private JFXComboBox<String> comboTitles;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        Customer customer = new Customer(txtId.getText(), comboTitles.getValue(), txtName.getText(), txtAddress.getText(), txtPhoneNumber.getText(), dob.getValue());
        System.out.println(customer);

        new ThogakadePOS().getInstance().addCustomer(customer);

        customerCount++;
        generateCusId();
        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        comboTitles.setValue("");
        dob.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //Adds the values to the tile ComboBox
        ObservableList<String> titles= FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Mrs.");
        comboTitles.setItems(titles);

        generateCusId();
    }

    private void generateCusId(){ //Generates the customer Id
        String id=String.format("C%03d",customerCount);
        txtId.setText(id);
    }
}
