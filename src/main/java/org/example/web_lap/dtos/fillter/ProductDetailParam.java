package org.example.web_lap.dtos.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailParam {
    private String name;
    private String status;
    private Double price;
    private Long ramId;
    private Long memoryId;
    private Long displaySizeId;
    private Long productId;
    private Long colorId;
}
