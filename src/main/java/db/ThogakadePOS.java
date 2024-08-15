package db;

import controller.NoticeFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class ThogakadePOS {
    private static ThogakadePOS instance;
    private List<Customer> customerList;

    public ThogakadePOS(){
        customerList=new ArrayList<>();
    }

    public ThogakadePOS getInstance(){
        return null==instance?instance=new ThogakadePOS():instance;
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public void printCustomer(){
        System.out.println(customerList);
    }

    public Customer searchCustomer(String search) {
        for (Customer customer:customerList){
            if (customer.getId().equals(search) | customer.getName().equals(search) | customer.getAddress().equals(search)){
                return customer;
            }
        }
        return null;
    }

    public void updateCustomer(Customer updatedCustomer){
        Customer customer;
        for (int i = 0; i<customerList.size(); i++) {
            customer=customerList.get(i);
            if (customer.getId().equals(updatedCustomer.getId())) {
                customerList.set(i,updatedCustomer);
            }
        }
    }

    public void deleteCustomer(Customer customer){
        customerList.remove(customer);
    }

    public ObservableList<Customer> viewCustomers() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerList.forEach(obj->{
            customerObservableList.add(obj);
        });

        return customerObservableList;
    }

    public void noticeViewer(String message){
        Stage stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/notice_form.fxml"))));
            stage.show();
            NoticeFormController.setMessage(message);

            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Delay the programme
            pause.setOnFinished(event -> {
                stage.close(); // Close the stage after the delay
            });
            pause.play();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
