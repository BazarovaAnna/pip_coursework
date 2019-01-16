package com.pip_coursework.controller;

import com.pip_coursework.entity.Rules;
import com.pip_coursework.entity.User;
import com.pip_coursework.repository.RulesRepository;
import com.pip_coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RulesController {
    @Autowired
    RulesRepository repository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/RulesController/addCommon")
    public  String add(@RequestParam("title") String title, @RequestParam("description") String description){
        String executiongStatus = "";

        try {
            if(repository.findByTitle(title).size() > 0){
                throw new DataIntegrityViolationException("");
            }

            repository.save(new Rules(title, description));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Такие правила уже существуют";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("/RulesController/customerAdd")
    public  String addWithCreator(@RequestParam("creator_id") long creator_id, @RequestParam("title") String title, @RequestParam("description") String description){
        String executiongStatus = "";

        try {
            if(repository.findByTitle(title).size() > 0){
                throw new DataIntegrityViolationException("");
            }

            User creator =  getUser(creator_id);
            //TODO: нужно как-то проверить наличие юзера

            repository.save(new Rules(creator, title, description));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Такие правила уже существуют";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("/RulesController/findall")
    public  String findAll(){
        String result = "";

        for(Rules rules : repository.findAll()){
            result += rules.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/RulesController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        if (result.equals("")) {
            return "There're no rules with this is";
        }
        return  result;
    }

    private User getUser(long userId){
        ArrayList<User> curUser = userRepository.findById(userId);
        return curUser.get(0);
    }
}
