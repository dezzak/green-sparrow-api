package uk.co.dezzanet.greensparrow.greensparrow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private Integer priceInPence;
}
