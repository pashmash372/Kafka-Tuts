package org.pashmash.kafka.orderproducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
        ProducerRecord<String, Integer> record = new ProducerRecord<String, Integer>("OrderTopic", "MacBook", 1);

        try {
            // send() is asynchronous, so we need to wait for the result
            // producer.send(record);

            // now this is synchronous send with get() method call on the future object returned by send()

//            Future<RecordMetadata> future = producer.send(record);
//            RecordMetadata recordMetadata = future.get();
//            System.out.println("Record sent to partition " + recordMetadata.partition() + " with offset " + recordMetadata.offset());
//            System.out.println("Message sent successfully");


            Future<RecordMetadata> future = producer.send(record, new OrderCallback());
            RecordMetadata recordMetadata = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }


    }
}
