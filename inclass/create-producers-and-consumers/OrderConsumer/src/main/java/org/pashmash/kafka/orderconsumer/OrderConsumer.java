package org.pashmash.kafka.orderconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.setProperty("group.id", "OrderGroup");

        KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("OrderTopic")); // why singletonList()? because we can subscribe to multiple topics at once .
        ConsumerRecords<String, Integer> orders = consumer.poll(Duration.ofSeconds(20));// this is a blocking call, it will wait for 20 seconds for new records to arrive

        for (ConsumerRecord<String, Integer> order : orders) {
            System.out.println("Product Name " + order.key());
            System.out.println("Quantity " + order.value());
        }
        consumer.close();
    }
}
