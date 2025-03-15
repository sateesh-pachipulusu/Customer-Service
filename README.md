# Customer-Service

#To start Zookeeper
/home/sateesh/kafka_2.13-3.9.0/bin/zookeeper-server-start.sh /home/sateesh/kafka_2.13-3.9.0/config/zookeeper.properties

#To start kafka
/home/sateesh/kafka_2.13-3.9.0/bin/kafka-server-start.sh /home/sateesh/kafka_2.13-3.9.0/config/server.properties


# To  get the topic list
./kafka-topics.sh --bootstrap-server localhost:9092 --list


# To get the list of group id in kafka
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list


# To check messages in topic
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic customer-topic --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer




#To connect H2 database
http://localhost:9090/h2-console/

#Swagger UI
http://localhost:9090/swagger-ui/index.html
