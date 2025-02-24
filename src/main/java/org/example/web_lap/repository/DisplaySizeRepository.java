package org.example.web_lap.repository;
import org.example.web_lap.dtos.fillter.DisplaySizeParam;
import org.example.web_lap.entities.DisplaySize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplaySizeRepository extends JpaRepository<DisplaySize, Long> {

    @Query("select t from DisplaySize t where :#{#param.name} is null or lower(t.name) like concat('%', lower(:#{#param.name}) ,'%') ")
    Page<DisplaySize> filter(DisplaySizeParam param, Pageable pageable);
    DisplaySize findByName(String name);
}
