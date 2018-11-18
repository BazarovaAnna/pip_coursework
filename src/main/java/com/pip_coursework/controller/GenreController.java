package com.pip_coursework.controller;

import com.pip_coursework.entity.Genre;
import com.pip_coursework.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController {
    @Autowired
    GenreRepository repository;

    @RequestMapping("/GenreController/add")
    public  String add(@RequestParam("name") String name){
        String executiongStatus = "";

        try {
            if(repository.findByName(name).size() > 0){
                throw new DataIntegrityViolationException("");
            }

            repository.save(new Genre(name));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Такой жанр уже существует";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("/GenreController/findall")
    public  String findAll(){
        String result = "";

        for(Genre genre : repository.findAll()){
            result += genre.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/GenreController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        if (result.equals("")) {
            return "There're no genres with this is";
        }
        return  result;
    }

    @RequestMapping("/GenreController/findbyid/findusers")
    public String findUsersById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).get(0).getGenresUsers().toString();
        if (result.equals("")) {
            return "There're no genres with this id";
        }
        return  result;
    }
}
