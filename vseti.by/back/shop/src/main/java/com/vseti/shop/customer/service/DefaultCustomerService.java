package com.vseti.shop.customer.service;

import com.vseti.shop.customer.entity.Customer;
import com.vseti.shop.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Optional<Customer> foundedCustomer = this.customerRepository.findById(customer.getEmail());
        return foundedCustomer.orElseGet(() -> this.customerRepository.save(customer));
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> foundedCustomer = this.customerRepository.findById(email);
        if(foundedCustomer.isPresent()){
            return foundedCustomer.get();
        } else {
            throw new RuntimeException("smth"); //todo
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer update) {
        getCustomerByEmail(update.getEmail());
        return this.customerRepository.save(update);
    }

    @Override
    public void deleteCustomerById(String email) {
        this.customerRepository.deleteById(email);
    }
}