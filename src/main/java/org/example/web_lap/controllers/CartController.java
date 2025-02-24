package org.example.web_lap.controllers;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.example.web_lap.dtos.request.CartRequest;
import org.example.web_lap.dtos.response.CartResponse;
import org.example.web_lap.entities.Cart;
import org.example.web_lap.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping
    public CartResponse addCart(@RequestBody @Valid CartRequest request) {
        return cartService.addCart(request);
    }

    @DeleteMapping("/{cartId}/{productDetailId}")
    public void deleteCart(@PathVariable Long cartId, @PathVariable Long productDetailId) {
        cartService.removeCartById(cartId , productDetailId);
    }

}
