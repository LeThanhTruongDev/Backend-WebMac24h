package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.filter.ProductDetailParam;
import org.example.web_lap.dtos.request.ProductDetailRequest;
import org.example.web_lap.entities.ProductDetail;

import org.example.web_lap.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping
    public ProductDetail createProductDetail(@RequestBody @Valid ProductDetailRequest request) {
        return productDetailService.createProductDetail(request);
    }

    @GetMapping("/{id}")
    public ProductDetail getProductDetail(@PathVariable Long id) {
        return productDetailService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductDetail(@PathVariable Long id) {
        productDetailService.deleteProductDetail(id);
    }

    @PutMapping("/{id}")
    public ProductDetail updateProductDetail(@PathVariable Long id, @RequestBody @Valid ProductDetailRequest request) {
        return productDetailService.updateProductDetail(id, request);
    }

    @GetMapping
    public Page<ProductDetail> filter(
            @ModelAttribute ProductDetailParam param,
            Pageable pageable
    ) {
        return productDetailService.filter(param, pageable);
    }

}
