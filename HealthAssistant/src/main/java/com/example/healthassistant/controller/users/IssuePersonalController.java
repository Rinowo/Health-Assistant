package com.example.healthassistant.controller.users;

import com.example.healthassistant.model.IssuePersonal;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.IssuePersonalService;
import com.example.healthassistant.service.IssuePersonalServiceImpl;
import com.example.healthassistant.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/IssuePersonal")
public class IssuePersonalController {
    @Autowired
    private IssuePersonalServiceImpl issuePersonalService;

    @Autowired
    private UsersServiceImpl usersService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String indexIssuePersonal(){
        return "web/personal/index";
    }

    @RequestMapping(value = "/submitForm",method = RequestMethod.POST)
    public String postSearch(Model model,
                             @RequestParam("keyword") String keyword){
        List<IssuePersonal> lists = issuePersonalService.searchByName(keyword);
        List<IssuePersonal> listsPersonals = issuePersonalService.findAll();
        model.addAttribute("lists", lists);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listsPersonals", listsPersonals);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users u = usersService.findByUsername(username);
        model.addAttribute("user", u);
        return "web/personal/result";
    }


    @RequestMapping(value = "/category/{id}",method = RequestMethod.POST)
    public String categoryUser(Model model,
                           @PathVariable("id") Long id){
        List<IssuePersonal> lists = issuePersonalService.findAllByCategoryId(id);
        List<IssuePersonal> listsPersonals = issuePersonalService.findAll();
        model.addAttribute("lists", lists);
        model.addAttribute("keyword", id);
        model.addAttribute("listsPersonals", listsPersonals);
        return "web/user/assistant-user";
    }
}
