package com.example.jwt_anhchinh2007.repo;

import com.example.jwt_anhchinh2007.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String name); //tim kiem trong Db co ton tai username khong
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
