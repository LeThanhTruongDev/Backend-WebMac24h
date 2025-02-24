package org.example.web_lap.services;

import org.example.web_lap.dtos.request.CartRequest;
import org.example.web_lap.dtos.response.CartResponse;
import org.example.web_lap.entities.Cart;
import org.example.web_lap.entities.CartItem;
import org.example.web_lap.entities.ProductDetail;
import org.example.web_lap.repository.CartItemRepository;
import org.example.web_lap.repository.CartRepository;
import org.example.web_lap.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Khong tim thay gio hang"));
    }

    public CartResponse addCart(CartRequest request) {
        ProductDetail productDetail = productDetailRepository.findById(request.getProductDetailId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));

        List<CartItem> list = cart.getCartItems();
        CartItem existingCartItem = null;


        for (CartItem cartItem : list) {
            if (cartItem.getProductDetail().getId().equals(request.getProductDetailId())) {
                existingCartItem = cartItem;
                break;
            }
        }

        if (existingCartItem != null) {

            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(existingCartItem);
        } else {

            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProductDetail(productDetail);
            newCartItem.setQuantity(request.getQuantity());
            newCartItem = cartItemRepository.save(newCartItem);
            list.add(newCartItem);
        }

        cart.setCartItems(list);
        Cart cart1 = cartRepository.save(cart);
        return CartResponse.builder()
                .id(productDetail.getId())
                .price(productDetail.getPrice())
                .quantity(request.getQuantity())
                .image(productDetail.getProduct().getImageUrl())
                .name(productDetail.getName())
                .build();
    }


    @Transactional
    public void removeCartById(Long cartId, Long productDetailId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));

        CartItem cartItemToRemove = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProductDetail().getId().equals(productDetailId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng"));

        cart.getCartItems().remove(cartItemToRemove);
        cartItemRepository.delete(cartItemToRemove);
    }


}
