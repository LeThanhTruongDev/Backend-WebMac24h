package org.example.web_lap.services;

import org.example.web_lap.dtos.request.ProductDetailRequest;
import org.example.web_lap.entities.ProductDetail;
import org.example.web_lap.repository.*;

import org.example.web_lap.dtos.filter.ProductDetailParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private RamService ramService;

    @Autowired
    private MemoryRepository memoryRepository;

    @Autowired
    private DisplaySizeRepository displaySizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductDetail createProductDetail(ProductDetailRequest request) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setName(request.getName());
        productDetail.setQuantity(request.getQuantity());
        productDetail.setPrice(request.getPrice());
        productDetail.setStatus(request.getStatus());
        productDetail.setCode(request.getCode());
        productDetail.setImageUrl(request.getImageUrl());
        productDetail.setRam(ramService.findById(request.getRamId()));
        productDetail.setMemory(memoryRepository.findById(request.getMemoryId()).orElseThrow(() -> new RuntimeException("Memory not found")));
        productDetail.setDisplaySize(displaySizeRepository.findById(request.getDisplaySizeId()).orElseThrow(() -> new RuntimeException("Display Size not found")));
        productDetail.setColor(colorRepository.findById(request.getColorId()).orElseThrow(() -> new RuntimeException("Color not found")));
        productDetail.setProduct(productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found")));
        return productDetailRepository.save(productDetail);
    }

    public ProductDetail findById(Long id) {
        return productDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Product detail not found"));
    }

    @Transactional
    public void deleteProductDetail(Long id) {
        ProductDetail productDetail = findById(id);
        productDetail.setIsDeleted(true);
        productDetailRepository.save(productDetail);
    }

    @Transactional
    public ProductDetail updateProductDetail(Long id, ProductDetailRequest request) {
        ProductDetail productDetail = findById(id);
        productDetail.setName(request.getName());
        productDetail.setQuantity(request.getQuantity());
        productDetail.setPrice(request.getPrice());
        productDetail.setStatus(request.getStatus());
        productDetail.setCode(request.getCode());
        productDetail.setImageUrl(request.getImageUrl());
        productDetail.setRam(ramService.findById(request.getRamId()));
        productDetail.setMemory(memoryRepository.findById(request.getMemoryId()).orElseThrow(() -> new RuntimeException("Memory not found")));
        productDetail.setDisplaySize(displaySizeRepository.findById(request.getDisplaySizeId()).orElseThrow(() -> new RuntimeException("Display Size not found")));
        productDetail.setColor(colorRepository.findById(request.getColorId()).orElseThrow(() -> new RuntimeException("Color not found")));
        productDetail.setProduct(productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found")));
        return productDetailRepository.save(productDetail);
    }

    public Page<ProductDetail> filter(ProductDetailParam param, Pageable pageable) {
        return productDetailRepository.filter(param, pageable);
    }
}
