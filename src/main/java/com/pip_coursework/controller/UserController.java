package com.pip_coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pip_coursework.entity.User;
import com.pip_coursework.repository.UserRepository;

import java.util.Date;

@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/UserController/add")
    public String add(@RequestParam("login") String login,
                      @RequestParam("password") char[] password,
                      @RequestParam("mail") String mail,
                      @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                      @RequestParam("lang") char[] lang,
                      @RequestParam("sex") char sex){
        String executiongStatus = "";

        try{
            if(repository.findByLogin(login).size()  > 0){
                throw  new DataIntegrityViolationException("");
            }

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

        for(User user: repository.findByLogin(login)){
            result += user.toString() + "<br>";
        }

        return  result;
    }
}