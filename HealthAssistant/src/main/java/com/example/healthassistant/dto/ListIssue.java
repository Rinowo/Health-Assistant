package com.example.healthassistant.dto;

import com.example.healthassistant.model.IssuePersonal;

import java.util.Optional;

public class ListIssue {
    private Optional<IssuePersonal> data;

    public Optional<IssuePersonal> getData() {
        return data;
    }

    public void setData(Optional<IssuePersonal> data) {
        this.data = data;
    }
}
