package com.pip_coursework.controller;

import com.pip_coursework.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index(
            @AuthenticationPrincipal User user,
            Model model){

        String rules = "Правила ресурса...";

        if(user != null){
            model.addAttribute("login", user.getLogin());
        }

        model.addAttribute("rules", rules);

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){

        return "login";
    }
}
