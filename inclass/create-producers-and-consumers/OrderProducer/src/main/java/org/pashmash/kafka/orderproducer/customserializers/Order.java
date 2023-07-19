package org.pashmash.kafka.orderproducer.customserializers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private String customerName;
    private String product;
    private int quantity;
}
