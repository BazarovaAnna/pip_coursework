package com.pip_coursework.controller;

import com.pip_coursework.entity.Effect;
import com.pip_coursework.repository.EffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EffectController {
    @Autowired
    EffectRepository repository;

    @RequestMapping("EffectController/add")
    public  String add(@RequestParam("name") String name,
                       @RequestParam("description") String description){
           String executiongStatus = "";

        try {
            if(repository.findByName(name).size() > 0){
                throw new DataIntegrityViolationException("");
            }

            repository.save(new Effect(name, description));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Такой эффект уже существует";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("EffectController/findall")
    public  String findAll(){
        String result = "";

        for(Effect effect : repository.findAll()){
            result += effect.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("EffectController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        if (result.equals("")) {
            return "There're no effects with this is";
        }
        return  result;
    }
}
