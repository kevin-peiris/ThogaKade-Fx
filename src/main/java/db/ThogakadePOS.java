package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

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
}
