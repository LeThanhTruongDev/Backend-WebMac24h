package org.example.web_lap.repository;

import org.example.web_lap.dtos.fillter.RoleParam;
import org.example.web_lap.dtos.fillter.UserParam;
import org.example.web_lap.entities.Roles;
import org.example.web_lap.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("select t from User t " +
            "where :#{#param.username} is null or lower(t.username) like concat('%', lower(:#{#param.username}) ,'%') " +
            "and :#{#param.fullName} is null or lower(t.fullName) like concat('%', lower(:#{#param.fullName}) ,'%')" +
            "and :#{#param.phoneNumber} is null or lower(t.phoneNumber) like concat('%', lower(:#{#param.phoneNumber}) ,'%')" +
            "and :#{#param.roleId} is null or t.role.id = :#{#param.roleId} " +
            "group by t.id , t.address ,t.createdAt , t.fullName , t.phoneNumber , t.isDeleted , t.password ,t.role , t.updatedAt , t.username " +
            "order by t.id desc ")
    Page<User> filter(UserParam param, Pageable pageable);



    boolean existsByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

}
