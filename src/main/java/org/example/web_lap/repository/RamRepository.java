package org.example.web_lap.repository;

import org.example.web_lap.dtos.fillter.RamParam;
import org.example.web_lap.entities.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<Ram, Long> {

    @Query("select t from Ram t where :#{#param.name} is null or lower(t.name) like concat('%', lower(:#{#param.name}) ,'%') ")
    Page<Ram> filter(RamParam param, Pageable pageable);

    Ram findByName(String name);
}
