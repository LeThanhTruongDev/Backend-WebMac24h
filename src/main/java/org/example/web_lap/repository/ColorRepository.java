package org.example.web_lap.repository;

import org.example.web_lap.dtos.fillter.ColorParam;
import org.example.web_lap.dtos.fillter.RamParam;
import org.example.web_lap.entities.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    @Query("select t from Color t where :#{#param.name} is null or lower(t.name) like concat('%', lower(:#{#param.name}) ,'%') ")
    Page<Color> filter(ColorParam param, Pageable pageable);
}
