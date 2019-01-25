package com.pip_coursework.controller;

import com.pip_coursework.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewCharacterController {
    @RequestMapping(value="/newcharacter", method = RequestMethod.GET)
    public String newcharacter(@AuthenticationPrincipal User user,
                               Model model){

        model.addAttribute("login", user.getLogin());

        return "newcharacter";
    }
}
