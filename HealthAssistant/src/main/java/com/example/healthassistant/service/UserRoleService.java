package com.example.healthassistant.service;

import com.example.healthassistant.model.UserRole;

import java.util.Optional;

public interface UserRoleService {

    Optional<UserRole> findUserRoleByUserId(Long id);

    void deleteById(Long id);
}
