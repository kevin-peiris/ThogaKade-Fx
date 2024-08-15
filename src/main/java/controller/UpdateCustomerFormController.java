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
import java.util.ResourceBundle;

public class UpdateCustomerFormController implements Initializable {
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

        if (searchCustomer!=null){  //Checks if a customer is found
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

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        if (searchCustomer!=null){
            searchCustomer.setName(txtName.getText());
            searchCustomer.setAddress(txtAddress.getText());
            searchCustomer.setNumber(txtPhoneNumber.getText());
            searchCustomer.setDob(dob.getValue());
            searchCustomer.setTitle(comboTitles.getValue());

            thogakadePOS.updateCustomer(searchCustomer); //Updated Customer object is sent to the ThogakadePOS

            txtId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
            comboTitles.setValue("");
            dob.setValue(null);
            txtSearch.setText("");
            searchCustomer=null;

            thogakadePOS.noticeViewer("Customer Updated Successfully"); //Notice is Displayed
            txtSearch.requestFocus();
            txtSearch.selectAll();
        }else {
            txtId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
            comboTitles.setValue("");
            dob.setValue(null);

            thogakadePOS.noticeViewer("First Search for a Customer"); //Notice is Displayed
            txtSearch.requestFocus();
            txtSearch.selectAll();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles= FXCollections.observableArrayList(); // Initializes the values of the combo box
        titles.add("Mr.");
        titles.add("Mrs.");
        comboTitles.setItems(titles);
    }
}
