package com.pip_coursework.controller;

import com.pip_coursework.entity.User;
import com.pip_coursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(User user,
                          Map<String, Object> model){
        if(!userService.addUser(user)){
            model.put("message", "Пользователь с таким логином сущесвует!");
            return "registration";
        }

        return "redirect:/login";
    }
}
