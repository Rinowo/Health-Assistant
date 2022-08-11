package com.example.healthassistant.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(path = {"/index", "/", "/home"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping(path = "/user")
    public String indexUser(Model model) {
        return "index-user";
    }

    @GetMapping(path = "/health-assistant")
    public String assistant(Model model) {
        return "assistant";
    }

    @GetMapping(path = "/personal/")
    public String personal(Model model) {
        return "personal";
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        return "register";
    }
}
