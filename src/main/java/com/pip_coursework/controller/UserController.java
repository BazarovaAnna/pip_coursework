package com.pip_coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pip_coursework.entity.User;
import com.pip_coursework.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/UserController/save")
    public String add(@RequestParam("login") String login,
                      @RequestParam("password") char[] password,
                      @RequestParam("mail") String mail,
                      @RequestParam("date") Date date,
                      @RequestParam("lang") char[] lang,
                      @RequestParam("sex") char sex){
        String executiongStatus = "";

        try{
            repository.save(new User(login, password,
                    mail, date, lang, sex));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Такой логин уже существует";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("/UserController/findall")
    public String findAll(){
        String result = "";

        for(User user : repository.findAll()){
            result += user.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/UserController/findByLogin")
    public String fetchDataByLogin(@RequestParam("login") String login){
        String result = "";
        ArrayList<User> users = (ArrayList<User>) repository.findByLogin(login);

        for(User user: repository.findByLogin(login)){
            result += user.toString() + "<br>";
        }

        return  result;
    }
}
