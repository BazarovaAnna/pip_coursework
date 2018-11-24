package com.pip_coursework.controller;

import com.pip_coursework.entity.Genre;
import com.pip_coursework.entity.UserGenre;
import com.pip_coursework.repository.UserGenreRepository;
import com.pip_coursework.repository.GenreRepository;
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
    @Autowired
    UserGenreRepository user_genreRepository;
    @Autowired
    GenreRepository genreRepository;

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

    @RequestMapping("/UserController/findById")
    public String findById(@RequestParam("id") long id){
        String result = "";

        for(User user: repository.findById(id)){
            result += user.toString() + "<br>";
        }

        return  result;
    }

    @RequestMapping("/UserController/findgenres")
    public String findGenresById(@RequestParam("id") long id){
        String result = "";
        result = user_genreRepository.findByUserId(id).toString();
        if (result.equals("")) {
            return "There're no genres referenced to user with this id";
        }
        return  result;
    }

    @RequestMapping("/UserController/setgenre")
    public String setGenreById(@RequestParam("id") long id, long genreId){
        String result = "done";

        try {
            Genre genre = genreRepository.findById(genreId).get(0);
            User user = repository.findById(id).get(0);
            repository.save(user);
            genreRepository.save(genre);
            user_genreRepository.save(new UserGenre(user, genre));
        }

        catch (DataIntegrityViolationException e) {
            result = "There're no user or genre with this id";
        }

        finally {
            return result;
        }
    }

    //TODO: @RequestMapping("/UserController/deletegenre")
}