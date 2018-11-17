package com.pip_coursework.controller;

import com.pip_coursework.entity.Abilitie;
import com.pip_coursework.entity.Effect;
import com.pip_coursework.exception.PerkAbilityException;
import com.pip_coursework.repository.AbilitieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbilitieController {
    @Autowired
    AbilitieRepository repository;

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

            repository.save(new Abilitie(name,description, perkAbil));

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

        for(Abilitie abilitie : repository.findAll()){
            result += abilitie.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("AbilitieController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        return  result;
    }
}
