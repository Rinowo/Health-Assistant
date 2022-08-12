package com.example.healthassistant.service;

import com.example.healthassistant.model.IssuePersonal;

import java.util.List;
import java.util.Optional;

public interface IssuePersonalService {
    List<IssuePersonal> searchByName(String name);

    List<IssuePersonal> findAll();

    List<IssuePersonal> findAllByCategoryId(Long id);
}
