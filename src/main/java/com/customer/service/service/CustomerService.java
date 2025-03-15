package com.customer.service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.service.model.Customer;
import com.customer.service.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    KafkaProducerService kafkaProducerService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
    	
    	customer  =	customerRepository.save(customer);
    	
    	  kafkaProducerService.sendCustomer(customer);
    	
        return customer;
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            Customer existingCustomer = customer.get();
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPhone(customerDetails.getPhone());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}