package com.example.jwt_anhchinh2007.repo;

import com.example.jwt_anhchinh2007.model.Role;
import com.example.jwt_anhchinh2007.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
