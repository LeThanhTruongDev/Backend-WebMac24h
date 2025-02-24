package org.example.web_lap.repository;

import org.example.web_lap.dtos.fillter.RoleParam;
import org.example.web_lap.entities.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    @Query("select t from Roles t where :#{#param.name} is null or lower(t.name) like concat('%', lower(:#{#param.name}) ,'%') ")
    Page<Roles> filter(RoleParam param , Pageable pageable);

    Roles findByName(String name);
}
