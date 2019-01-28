package com.pip_coursework.controller;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.Race;
import com.pip_coursework.entity.User;
import com.pip_coursework.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class NewCharacterController {
    @Autowired
    private CharacterService characterService;

    @RequestMapping(value="/newcharacter", method = RequestMethod.GET)
    public String newcharacter(@AuthenticationPrincipal User user,
                               Model model){

        model.addAttribute("login", user.getLogin());

        return "newcharacter";
    }

    // Получение всех рас
    @RequestMapping(value = "/newcharacter/allrace", method = RequestMethod.POST)
    public ResponseEntity<?> getAllType(@AuthenticationPrincipal User user,
                                        Model model){

        model.addAttribute("races", characterService.getAllRace());

        return new ResponseEntity<>(characterService.getAllRace(), HttpStatus.OK);
    }

    // Получение изображений конкретной расы
    @RequestMapping(value = "/newcharacter/race", method = RequestMethod.POST)
    public ResponseEntity<?> getImg(@AuthenticationPrincipal User user,
                         Model model,
                         @RequestParam("race") String race){

        return new ResponseEntity<>((ArrayList<String>)characterService.getImageForRace(race), HttpStatus.OK);
    }


    // Добавление нового пользователя
    @RequestMapping(value = "/newcharacter/add", method = RequestMethod.POST)
    public ResponseEntity<?> addNewCharacter(
            @RequestParam("sex") char sex,
            @RequestParam("imgPath") String imgPath,
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("userClass") String userClass,
            @AuthenticationPrincipal User user,
            Model model
            ){
        if(characterService.addNewCharacter(name, sex, userClass ,user, imgPath, type)){

            return new ResponseEntity<>((String)"Персонаж успешно создан!", HttpStatus.OK);
        }

        return new ResponseEntity<>((String)"Персонаж с таким именем уже существует!", HttpStatus.OK);
    }
}


