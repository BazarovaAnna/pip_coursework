package com.pip_coursework.controller;

import com.pip_coursework.entity.Ability;
import com.pip_coursework.exception.PerkAbilityException;
import com.pip_coursework.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbilityController {
    @Autowired
    AbilityRepository repository;

    @RequestMapping("AbilitieController/add")
    public  String add(@RequestParam("name") String name,
                       @RequestParam("perkabil") char perkAbil ,
                       @RequestParam("description") String description){
        String executiongStatus = "";

        try {
            if(repository.findByName(name).size() > 0){
                throw new DataIntegrityViolationException("");
            }

            if(perkAbil != 'a' && perkAbil != 'p'){
                throw new PerkAbilityException();
            }

            repository.save(new Ability(name,description, perkAbil));

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            String type = perkAbil == 'a'? "Абилка" : "Перк";
            executiongStatus =  type + " с таким названием уже существует";
        }
        catch (PerkAbilityException e){
            executiongStatus = "Вы попытались задать недопустимый тип";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("AbilitieController/findall")
    public  String findAll(){
        String result = "";

        for(Ability abilitie : repository.findAll()){
            result += abilitie.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("AbilitieController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        if (result.equals("")) {
            return "There're no abilities with this is";
        }
        return  result;
    }
}
