package com.customer.service.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.service.model.Customer;
import com.customer.service.repository.CustomerRepository;

@Service
public class  CustomerService{
	  private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    KafkaProducerService kafkaProducerService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
    	logger.info("customer Id  "+id);
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