package com.pip_coursework.controller;

import com.pip_coursework.entity.Item;
import com.pip_coursework.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    ItemRepository repository;


    @RequestMapping("ItemController/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("description") String description,
                      @RequestParam("price") double price,
                      @RequestParam("weight") double weight) {
        String executiongStatus = "";

        try {
            if (price < 0) {
                throw new Exception("Стоимость предмета меньше нуля. Задайте неотрицательную стоимость предмета.");
            }
            if (weight <= 0) {
                throw new Exception("Вес предмета меньше нуля. Задайте положительный вес!");
            }

            if (repository.findByName(name).size() > 0) {
                throw new DataIntegrityViolationException("");
            }

            repository.save(new Item(name, description, price, weight));

            executiongStatus = "Done";
        } catch (DataIntegrityViolationException e) {
            executiongStatus = "Такой предмет уже существует";
        } catch (Exception e) {
            executiongStatus = e.getMessage();
        } finally {
            return executiongStatus;
        }
    }

    @RequestMapping("ItemController/findall")
    public  String findAll(){
        String result = "";

        for(Item item : repository.findAll()){
            result += item.toString() + "<br>";
        }

        return result;
    }
}
