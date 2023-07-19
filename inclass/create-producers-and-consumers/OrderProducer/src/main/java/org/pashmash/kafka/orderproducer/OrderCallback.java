package org.pashmash.kafka.orderproducer;

import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements org.apache.kafka.clients.producer.Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("Record sent to partition " + recordMetadata.partition() + " with offset " + recordMetadata.offset());
        System.out.println("Message sent successfully");
        if(e!=null)
            e.printStackTrace();
    }
}
