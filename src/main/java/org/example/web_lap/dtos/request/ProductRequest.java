package org.example.web_lap.dtos.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.web_lap.entities.ProductDetail;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank (message = "Ten khong duoc de trong ")
    private String name;

    private String imageUrl;

    @NotNull
    private Long categoryId;


    private List<ProductDetailRequest> productDetailRequests;

}
