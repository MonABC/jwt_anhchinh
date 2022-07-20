package com.example.jwt_anhchinh2007.service;

import com.example.jwt_anhchinh2007.model.Users;

import java.util.Optional;

public interface IUsersService {
    Optional<Users> findByUsername(String name); //tim kiem trong Db co ton tai username khong
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Users save(Users users);
}
