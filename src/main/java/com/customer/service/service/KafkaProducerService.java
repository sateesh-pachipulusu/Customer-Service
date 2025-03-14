package com.customer.service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.customer.service.model.Customer;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Value("${kafka.topic.customer-topic}")
    private String topicName;

    public void sendCustomer(Customer customer) {
        kafkaTemplate.send(topicName, customer);
    }
}