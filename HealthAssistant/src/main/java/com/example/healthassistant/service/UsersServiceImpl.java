package com.example.healthassistant.service;

import com.example.healthassistant.model.Users;
import com.example.healthassistant.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Optional<Users> findById(Long id) {
        return usersRepository.findById(id);
    }
}
