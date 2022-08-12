package com.example.healthassistant.dto;

import com.example.healthassistant.model.IssuePersonal;

import java.util.List;

public class CategoryDTO {
    List<IssuePersonal> lists;
    List<IssuePersonal> listsPersonals;
    Long id;

    public List<IssuePersonal> getLists() {
        return lists;
    }

    public void setLists(List<IssuePersonal> lists) {
        this.lists = lists;
    }

    public List<IssuePersonal> getListsPersonals() {
        return listsPersonals;
    }

    public void setListsPersonals(List<IssuePersonal> listsPersonals) {
        this.listsPersonals = listsPersonals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
