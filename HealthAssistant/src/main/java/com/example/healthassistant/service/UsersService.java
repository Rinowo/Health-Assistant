package com.example.healthassistant.service;

import com.example.healthassistant.model.Users;

import java.util.Optional;

public interface UsersService {

    Optional<Users> findById(Long id);
}
