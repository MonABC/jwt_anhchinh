package com.example.jwt_anhchinh2007.service.impl;

import com.example.jwt_anhchinh2007.model.Users;
import com.example.jwt_anhchinh2007.repo.IUsersRepo;
import com.example.jwt_anhchinh2007.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService implements IUsersService {
    @Autowired
    private IUsersRepo usersRepo;

    @Override
    public Optional<Users> findByUsername(String name) {
        return usersRepo.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return usersRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usersRepo.existsByEmail(email);
    }

    @Override
    public Users save(Users users) {
        return usersRepo.save(users);
    }
}
