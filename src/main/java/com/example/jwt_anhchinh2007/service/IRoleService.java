package com.example.jwt_anhchinh2007.service;

import com.example.jwt_anhchinh2007.model.Role;
import com.example.jwt_anhchinh2007.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
