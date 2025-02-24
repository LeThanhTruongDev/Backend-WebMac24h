package org.example.web_lap.services;

import org.example.web_lap.dtos.fillter.ProductParam;
import org.example.web_lap.dtos.request.ProductDetailRequest;
import org.example.web_lap.dtos.request.ProductRequest;
import org.example.web_lap.entities.*;
import org.example.web_lap.repository.CategoryReposirory;
import org.example.web_lap.repository.ProductDetailRepository;
import org.example.web_lap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Service
public class ProductService {
    @Autowired

    private ProductRepository productRepository;

    @Autowired
    private CategoryReposirory categoryReposirory;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private RamService ramService;

    @Autowired
    private DisplaySizeService displaySizeService;

    @Autowired
    private MemoryService memoryService;

    @Autowired
    private ColorService colorService;

    @Transactional
    public Product createProduct(ProductRequest request) {
        try {

//          b1
            Product product = new Product();
            product.setName(request.getName());
            product.setCode(generateCodeString());
            product.setImageUrl(request.getImageUrl());
            product.setCategory(getCategoryById(request.getCategoryId()));
            product = productRepository.save(product);

//          b2 luu xong rpoduct detail
            List<ProductDetail> productDetails = new ArrayList<>();
            for (ProductDetailRequest dt : request.getProductDetailRequests()) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProduct(product);
                productDetail.setName(dt.getName());
                productDetail.setQuantity(dt.getQuantity());
                productDetail.setPrice(dt.getPrice());
                productDetail.setStatus(dt.getStatus());
                productDetail.setCode(dt.getCode());
                productDetail.setDisplaySize(displaySizeService.findById(dt.getDisplaySizeId()));
                productDetail.setMemory(memoryService.findById(dt.getMemoryId()));
                productDetail.setRam(ramService.findById(dt.getRamId()));
                productDetail.setColor(colorService.findById(dt.getColorId()));
                productDetail = productDetailRepository.save(productDetail);
                productDetails.add(productDetail);
            }
            product.setProductDetails(productDetails);
            return product;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private Category getCategoryById(Long Id) {
        return categoryReposirory.findById(Id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = findById(id);
        product.setIsDeleted(true);
        productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequest request) {
        Product product = findById(id);
        product.setName(request.getName());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(getCategoryById(request.getCategoryId()));
        return productRepository.save(product);
    }

    public Page<Product> filter(ProductParam param, Pageable pageable) {
        return productRepository.filter(param, pageable);
    }

    public String generateCodeString() {
        String code;
        boolean isDuplicate;

        do {
            code = generateRandomCode(8);
            isDuplicate = productRepository.existsByCode(code);
        } while (isDuplicate);

        return code;
    }

    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }

        return code.toString();
    }


}
