package org.example.web_lap.repository;

import org.example.web_lap.dtos.fillter.CategoryParam;
import org.example.web_lap.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReposirory extends JpaRepository<Category, Long> {


    @Query("select t from Category t " +
            "where (:#{#param.name} is null or lower(t.name) like concat('%', lower(:#{#param.name}), '%')) and " +
            "(:#{#param.supplier} is null or t.supplier = :#{#param.supplier})")
    Page<Category> filter( CategoryParam param, Pageable pageable);



}
