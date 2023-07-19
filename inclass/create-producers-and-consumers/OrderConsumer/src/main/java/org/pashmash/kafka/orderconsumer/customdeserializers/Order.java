package org.pashmash.kafka.orderconsumer.customdeserializers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String customerName;
    private String product;
    private int quantity;
}
