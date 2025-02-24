package org.example.web_lap.controllers;

import org.example.web_lap.dtos.fillter.ProductParam;
import org.example.web_lap.dtos.request.ProductRequest;
import org.example.web_lap.entities.Product;
import org.example.web_lap.entities.ProductDetail;
import org.example.web_lap.repository.ProductDetailRepository;
import org.example.web_lap.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
   private ProductDetailRepository productDetailRepository;

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping ("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping ("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @GetMapping
    public Page<Product> filter(ProductParam param , Pageable pageable) {
        return productService.filter(param, pageable);
    }

    @GetMapping ("/detail/{id}")
    public List<ProductDetail> getAllDetailById(@PathVariable Long id) {
        return productDetailRepository.findAllByProductId(id);
    }

}
