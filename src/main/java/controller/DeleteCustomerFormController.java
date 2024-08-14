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

public class DeleteCustomerFormController implements Initializable {
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
    void btnDeleteCustomerOnAction(ActionEvent event) {
        if (searchCustomer!=null){
            thogakadePOS.deleteCustomer(searchCustomer); //Searched Customer object is sent to the ThogakadePOS to be deleted

            txtId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
            comboTitles.setValue("");
            dob.setValue(null);
            txtSearch.setText("");
            searchCustomer=null;
        }else {
            txtId.setText("Search For A Customer");
            txtName.setText("Search For A Customer");
            txtAddress.setText("Search For A Customer");
            txtPhoneNumber.setText("Search For A Customer");
            comboTitles.setValue("");
            dob.setValue(null);
            txtSearch.setText("");
        }

    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
        searchCustomer=thogakadePOS.searchCustomer(txtSearch.getText());

        if (searchCustomer!=null){
            txtId.setText(searchCustomer.getId());
            txtName.setText(searchCustomer.getName());
            txtAddress.setText(searchCustomer.getAddress());
            txtPhoneNumber.setText(searchCustomer.getNumber());
            comboTitles.setValue(searchCustomer.getTitle());
            dob.setValue(searchCustomer.getDob());

            txtSearch.setText("");
        }else{
            txtId.setText("Customer Not Found");
            txtName.setText("Customer Not Found");
            txtAddress.setText("Customer Not Found");
            txtPhoneNumber.setText("Customer Not Found");
            comboTitles.setValue(null);
            dob.setValue(null);
        }

    }

    @FXML
    public void searchCustomerOnAction(ActionEvent actionEvent) { //If text area of search customer is entered customer can be also found
        btnSearchCustomerOnAction(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles= FXCollections.observableArrayList();
        titles.add("Mr.");
        titles.add("Mrs.");
        comboTitles.setItems(titles);
    }
}
