package org.pashmash.kafka.orderconsumer.customdeserializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class OrderDeserializer implements Deserializer<Order> {

    @Override
    public Order deserialize(String s, byte[] data) {
        var objectMapper = new ObjectMapper();
        Order order = null;
        try {
            order = objectMapper.readValue(data,Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
