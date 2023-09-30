package com.example.managerblog.repositories;

import com.example.managerblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoles_NameIn(List<String> roles);


    // Method query
    List<User> findByRoles_Id(Long role_id);

    // JPQL query
    @Query("""
        SELECT user FROM User user 
        JOIN user.roles role 
        WHERE role.id = :roleId
    """)
    List<User> findByRoles_IdJPQL(@Param("roleId") Long role_id);

    // Native query
    @Query(value = "SELECT u.* FROM user u JOIN user_role ur ON u.id = ur.user_id JOIN role r ON r.id = ur.role_id WHERE r.id = :roleId", nativeQuery = true)
    List<User> findByRoles_IdNQ(@Param("roleId") Long role_id);

}
