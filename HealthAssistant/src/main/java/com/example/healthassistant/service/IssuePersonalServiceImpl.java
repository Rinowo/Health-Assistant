package com.example.healthassistant.service;

import com.example.healthassistant.model.IssuePersonal;
import com.example.healthassistant.repository.IssuePersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssuePersonalServiceImpl implements IssuePersonalService{
    @Autowired
    IssuePersonalRepository issuePersonalRepository;
    @Override
    public List<IssuePersonal> searchByName(String name) {
        return issuePersonalRepository.search(name);
    }

    @Override
    public List<IssuePersonal> findAll() {
        return issuePersonalRepository.findAll();
    }

    @Override
    public List<IssuePersonal> findAllByCategoryId(Long id) {
        return issuePersonalRepository.findAllByCategoryId(id);
    }
}
