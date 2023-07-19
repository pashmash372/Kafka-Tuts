package org.pashmash.kafka.orderproducer.customserializers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.pashmash.kafka.orderproducer.OrderCallback;

import java.util.Properties;
import java.util.concurrent.Future;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.pashmash.kafka.orderproducer.customserializers.OrderSerializer");

        KafkaProducer<String, Order> producer = new KafkaProducer(props);
        Order order = Order.builder()
                .customerName("Amrita")
                .product("MacBook")
                .quantity(1)
                .build();
        ProducerRecord<String, Order> record = new ProducerRecord("OrderCSTopic", order.getCustomerName(), order);

        try {
            producer.send(record);
            System.out.println("Message sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }


    }
}
