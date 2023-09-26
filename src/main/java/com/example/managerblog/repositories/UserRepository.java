package com.example.managerblog.repositories;

import com.example.managerblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoles_NameIn(List<String> roles);
}
