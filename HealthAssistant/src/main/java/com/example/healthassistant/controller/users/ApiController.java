package com.example.healthassistant.controller.users;

import com.example.healthassistant.dto.CategoryDTO;
import com.example.healthassistant.dto.ListIssue;
import com.example.healthassistant.model.IssuePersonal;
import com.example.healthassistant.service.IssuePersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private IssuePersonalServiceImpl issuePersonalService;

    @RequestMapping(value = "/category/{id}",method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> category(Model model,
                           @PathVariable("id") Long id){
        List<IssuePersonal> lists = issuePersonalService.findAllByCategoryId(id);
//        List<IssuePersonal> listsPersonals = issuePersonalService.findAll();

        CategoryDTO cate = new CategoryDTO();
        cate.setId(id);
        cate.setLists(lists);
//        cate.setListsPersonals(listsPersonals);

        return new ResponseEntity<CategoryDTO>(cate, HttpStatus.OK);
    }

//    @RequestMapping(value = "/lsclas/{id}", method = RequestMethod.GET)
//    public ResponseEntity<ListIssue> listStudentone(@PathVariable("id") long id) {
//        List<IssuePersonal> list = issuePersonalService.findByCategoryId(id);
//        ListIssue ls = new ListIssue();
//        ls.setData(list);
//        return new ResponseEntity<>(ls, HttpStatus.OK);
//    }
}
