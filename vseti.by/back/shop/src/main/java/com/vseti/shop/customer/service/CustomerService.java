package com.vseti.shop.customer.service;

import com.vseti.shop.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer getCustomerByEmail(String email);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer update);

    void deleteCustomerById(String email);
}