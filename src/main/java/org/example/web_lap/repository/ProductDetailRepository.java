package org.example.web_lap.repository;

import org.example.web_lap.entities.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    @Query("select pd from ProductDetail pd where pd.product.id = :productId and pd.isDeleted = false")
    List<ProductDetail> findAllByProductId(@Param("productId") Long productId);

    @Query("select pd from ProductDetail pd " +
            "where (:#{#param.name} is null or lower(pd.name) like concat('%', lower(:#{#param.name}), '%')) " +
            "and (:#{#param.status} is null or pd.status = :#{#param.status}) " +
            "and (:#{#param.price} is null or pd.price = :#{#param.price}) " +
            "and (:#{#param.ramId} is null or pd.ram.id = :#{#param.ramId}) " +
            "and (:#{#param.memoryId} is null or pd.memory.id = :#{#param.memoryId}) " +
            "and (:#{#param.displaySizeId} is null or pd.displaySize.id = :#{#param.displaySizeId}) " +
            "and (:#{#param.productId} is null or pd.product.id = :#{#param.productId}) " +
            "and (:#{#param.colorId} is null or pd.color.id = :#{#param.colorId})")
    Page<ProductDetail> filter(org.example.web_lap.dtos.filter.ProductDetailParam param, Pageable pageable);
}
