package com.pip_coursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index(Model model){

        String rules = "Правила ресурса...";

        model.addAttribute("rules", rules);

        return "index";
    }
}
