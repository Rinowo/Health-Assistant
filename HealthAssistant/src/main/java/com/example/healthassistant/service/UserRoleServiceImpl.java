package com.example.healthassistant.service;

import com.example.healthassistant.model.UserRole;
import com.example.healthassistant.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Optional<UserRole> findUserRoleByUserId(Long id) {
        return userRoleRepository.findUserRoleByUserId(id);
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }
}
