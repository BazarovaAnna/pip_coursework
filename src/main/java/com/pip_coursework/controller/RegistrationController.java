package com.pip_coursework.controller;

import com.pip_coursework.entity.Role;
import com.pip_coursework.entity.User;
import com.pip_coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(User user,
                          Map<String, Object> model){
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if(userFromDb != null){
            model.put("message", "Пользователь с таким логином сущесвует!");
            return "registration";
        }

        user.setActive(true);
        user.setDateRegister(new Date());
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
