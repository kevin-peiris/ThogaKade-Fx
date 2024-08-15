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
    private static int customerCount=1; //Used to count the customers to generate the customerId
    private ThogakadePOS thogakadePOS=new ThogakadePOS().getInstance();

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
        if (txtName.getText().equals("") || txtAddress.getText().equals("")  || txtPhoneNumber.getText().equals("") || dob.getValue()==null || comboTitles.getValue()==null){
            thogakadePOS.noticeViewer("Empty Field or Fields");
        } else if (txtPhoneNumber.getText().charAt(0)!='0' || txtPhoneNumber.getText().length()!=10) {
            thogakadePOS.noticeViewer("Invalid Phone Number");
            txtPhoneNumber.requestFocus();
            txtPhoneNumber.selectAll();
        } else{

            Customer customer = new Customer(txtId.getText(), comboTitles.getValue(), txtName.getText(), txtAddress.getText(), txtPhoneNumber.getText(), dob.getValue());
            System.out.println(customer);

            thogakadePOS.addCustomer(customer); //Customer object is sent back to the ArrayList

            customerCount++;
            generateCusId(); //CustomerId is generated
            txtName.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
            comboTitles.setValue("");
            dob.setValue(null);

            thogakadePOS.noticeViewer("Customer Added Successfully"); //Notice is displayed
            txtName.requestFocus();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles= FXCollections.observableArrayList(); // Initializes the values of the combo box
        titles.add("Mr.");
        titles.add("Mrs.");
        comboTitles.setItems(titles);

        generateCusId();
        txtName.requestFocus();
    }

    private void generateCusId(){ //Generates the customerId
        String id=String.format("C%03d",customerCount);
        txtId.setText(id);
    }
}
