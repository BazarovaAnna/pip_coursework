package com.pip_coursework.controller;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.User;
import com.pip_coursework.repository.CharacterRepository;
import com.pip_coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class CharacterController {
    private final double MAXWEIGHT = 100;
    private final double INITIALMONEY = 0;
    private final long INITIALLEVEL = 1;

    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    UserRepository userRepository;

    // TODO Нужно добавить сесси для передачи сущности юзера
    // TODO Добавить валидацию расс и классов из списка
    // TODO Каждой рассе и классу должен соответствовать параметр максимального веса, которые должны складываться
    @RequestMapping("/CharacterController/add")
    public String add(@RequestParam("userId") long userId,
                      @RequestParam("name") String name,
                      @RequestParam("userClass") String userClass,
                      @RequestParam("race") String race,
                      @RequestParam("story") String story,
                      @RequestParam("sex") char sex,
                      @RequestParam("condition") char[] condition){
        String executiongStatus = "";

        try{
            User curUser = getUser(userId);

            if(characterRepository.findByName(name).size()  > 0){
                throw  new DataIntegrityViolationException("");
            }

            characterRepository.save(new Character(curUser,name,userClass,
                    race,story,sex, condition, INITIALMONEY, MAXWEIGHT, INITIALLEVEL));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Персонаж с таким ником уже существует уже существует";
        }
        catch (NullPointerException e){
            executiongStatus = "Неверный уникальный идентификатор пользователя";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("/CharacterController/findall")
    public  String findAll(){
        String result = "";

        for(Character character : characterRepository.findAll()){
            result += character.toString() + "<br>";
        }

        return result;
    }

    private User getUser(long userId){
        ArrayList<User> curUser = userRepository.findById(userId);
        return curUser.get(0);
    }
}
