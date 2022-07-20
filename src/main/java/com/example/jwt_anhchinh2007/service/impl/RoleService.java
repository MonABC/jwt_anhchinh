package com.example.jwt_anhchinh2007.service.impl;

import com.example.jwt_anhchinh2007.model.Role;
import com.example.jwt_anhchinh2007.model.RoleName;
import com.example.jwt_anhchinh2007.repo.IRoleRepo;
import com.example.jwt_anhchinh2007.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepo roleRepo;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepo.findByName(name);
    }
}
