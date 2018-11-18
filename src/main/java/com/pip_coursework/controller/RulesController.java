package com.pip_coursework.controller;

import com.pip_coursework.entity.Rules;
import com.pip_coursework.repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {
    @Autowired
    RulesRepository repository;

    @RequestMapping("RulesController/add")
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

    @RequestMapping("RulesController/add")
    public  String add(@RequestParam("creator_id") long creator_id, @RequestParam("title") String title, @RequestParam("description") String description){
        String executiongStatus = "";

        try {
            if(repository.findByTitle(title).size() > 0){
                throw new DataIntegrityViolationException("");
            }

            repository.save(new Rules(creator_id, title, description));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Такие правила уже существуют";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("RulesController/findall")
    public  String findAll(){
        String result = "";

        for(Rules rules : repository.findAll()){
            result += rules.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("RulesController/findbyid")
    public  String findById(@RequestParam("id") long id){
        return repository.findById(id).toString();
    }
}
