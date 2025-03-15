package com.customer.service.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.customer.service.model.Customer;

@Service
public class KafkaProducerService {
	
	  private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Value("${kafka.topic.customer-topic}")
    private String topicName;

    public void sendCustomer(Customer customer) {
    	logger.info("customer created and pushed message"+customer.getName());
        kafkaTemplate.send(topicName, customer);
    }
}