package org.example.web_lap.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.web_lap.entities.Cart;
import org.example.web_lap.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String token;

    private String refreshToken;

    private User user;
    @JsonIgnoreProperties({"user", "cartItems"})
    private Cart cart;
}
