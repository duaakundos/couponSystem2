package com.example.couponSystem2.services;

import com.example.couponSystem2.entities.Company;
import com.example.couponSystem2.entities.Coupon;
import com.example.couponSystem2.entities.Customer;
import com.example.couponSystem2.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public Customer addCustomer(Customer customer) {
        Customer customerFromDB = customerRepository.save(customer);
        return customerFromDB;
    }
    public Customer getOneCustomer(int customerID) {
        return customerRepository.findById(customerID).get();
    }

    public boolean isCustomerExists(String email, String password){
        return customerRepository.existsCustomerByEmailAndPassword(email, password);
    }

    public void updateCustomer(Customer customer){
        customerRepository.updateCustomer(customer.getEmail(), customer.getPassword(), customer.getId());
    }

    public void deleteCustomer(int customerId){
        customerRepository.deleteById(customerId);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public int getCustomerID(String email, String password){
        return customerRepository.getCustomerID(email, password);
    }

}
