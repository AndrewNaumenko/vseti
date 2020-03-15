package com.vseti.shop.customer.contoller;

import com.vseti.shop.customer.dto.CustomerDto;
import com.vseti.shop.customer.entity.Customer;
import com.vseti.shop.customer.mapper.CustomerMapper;
import com.vseti.shop.customer.service.CustomerService;
import com.vseti.shop.customer.service.DefaultCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(DefaultCustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(customerMapper.toDto(savedCustomer), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerDto> findCustomer(@PathVariable("email") String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    @GetMapping(value = "/login", params = {"email", "password"})
    public ResponseEntity<CustomerDto> loginCustomer(@RequestParam("email") String email, @RequestParam("password") String password){
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer.getPassword().equals(password)){
            CustomerDto customerDto = customerMapper.toDto(customer);
            customerDto.setPassword("");
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDto> customerDtoList = customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(customerMapper.toDto(updatedCustomer), HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity deleteCustomer(@PathVariable("email") String email) {
        customerService.deleteCustomerById(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
