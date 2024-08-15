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
import java.util.ResourceBundle;

public class SearchCustomerFormController implements Initializable {
    private Customer searchCustomer;
    private ThogakadePOS thogakadePOS=new ThogakadePOS().getInstance();

    @FXML
    private JFXComboBox<String> comboTitles;

    @FXML
    private DatePicker dob;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        searchCustomer=thogakadePOS.searchCustomer(txtSearch.getText()); // Assigns a Customer object if found

        if (searchCustomer!=null){
            txtId.setText(searchCustomer.getId());
            txtName.setText(searchCustomer.getName());
            txtAddress.setText(searchCustomer.getAddress());
            txtPhoneNumber.setText(searchCustomer.getNumber());
            comboTitles.setValue(searchCustomer.getTitle());
            dob.setValue(searchCustomer.getDob());

            txtSearch.setText("");

            thogakadePOS.noticeViewer("Customer Found"); //Notice is Displayed
        }else{
            txtId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
            comboTitles.setValue(null);
            dob.setValue(null);

            thogakadePOS.noticeViewer("Customer Not Found"); //Notice is Displayed
            txtSearch.requestFocus();
            txtSearch.selectAll();
        }

    }

    @FXML
    public void searchCustomerOnAction(ActionEvent actionEvent) { //If text area of search customer is entered customer can be also found
        btnSearchCustomerOnAction(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles= FXCollections.observableArrayList(); // Initializes the values of the combo box
        titles.add("Mr.");
        titles.add("Mrs.");
        comboTitles.setItems(titles);
    }
}
