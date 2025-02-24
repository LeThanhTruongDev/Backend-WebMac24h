package org.example.web_lap.repository;

import org.example.web_lap.dtos.fillter.ProductParam;
import org.example.web_lap.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select t from Product t " +
            "where :#{#param.name} is null or lower(t.name) like concat('%', lower(:#{#param.name}) ,'%') " +
            "and :#{#param.code} is null or lower(t.code) like concat('%', lower(:#{#param.code}) ,'%')" +
            "and :#{#param.categoryId} is null or t.category.id = :#{#param.categoryId} ")
    Page<Product> filter(ProductParam param, Pageable pageable);

    boolean existsByCode(String code);
}
