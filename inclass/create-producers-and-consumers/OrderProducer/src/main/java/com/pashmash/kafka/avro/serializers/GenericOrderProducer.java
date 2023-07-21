package com.pashmash.kafka.avro.serializers;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static org.apache.avro.Schema.Parser;

public class GenericOrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        props.setProperty("schema.registry.url", "http://localhost:8081");

        KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(props);

        Parser parser = new Parser();
        Schema schema = parser.parse("{\n" +
                "\"namespace\": \"com.pashmash.kafka.avro\",\n" +
                "\"type\": \"record\",\n" +
                "\"name\": \"Order\",\n" +
                "\"fields\": [\n" +
                "{\"name\": \"customerName\", \"type\": \"string\"},\n" +
                "{\"name\": \"product\", \"type\": \"string\"},\n" +
                "{\"name\": \"quantity\", \"type\": \"int\"}\n" +
                "]\n" +
                "}");

        var order = new GenericData.Record(schema);
        order.put("customerName", "Akhilesh");
        order.put("product", "Mac Book Pro");
        order.put("quantity", 100);

        ProducerRecord<String, GenericRecord> record = new ProducerRecord<>("OrderAvroGRTopic", order.get("customerName").toString(), order);
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
