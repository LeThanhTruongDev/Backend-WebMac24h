package org.example.web_lap.repository;
import org.example.web_lap.dtos.fillter.MemoryParam;
import org.example.web_lap.entities.Memory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {

    @Query("select t from Memory t where :#{#param.name} is null or lower(t.name) like concat('%', lower(:#{#param.name}) ,'%') ")
    Page<Memory> filter(MemoryParam param, Pageable pageable);
    Memory findByName(String name);
}
