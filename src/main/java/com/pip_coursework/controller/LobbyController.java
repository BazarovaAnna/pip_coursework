package com.pip_coursework.controller;

import com.pip_coursework.entity.User;
import com.pip_coursework.service.CharacterService;
import com.pip_coursework.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LobbyController {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private GameService gameService;


    @RequestMapping(value="/lobby", method = RequestMethod.GET)
    public String lobby(@AuthenticationPrincipal User user,
                        Model model){

        model.addAttribute("login", user.getLogin());

        return "lobby";
    }


}
