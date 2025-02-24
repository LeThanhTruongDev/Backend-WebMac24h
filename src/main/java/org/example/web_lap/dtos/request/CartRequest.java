package org.example.web_lap.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private Long cartId;

    private Long productDetailId;

    private Long quantity;

}
