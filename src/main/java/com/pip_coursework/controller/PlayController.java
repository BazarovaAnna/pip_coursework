package com.pip_coursework.controller;

import com.pip_coursework.repository.AbilityRepository;
import com.pip_coursework.repository.EffectRepository;
import com.pip_coursework.repository.InventoryRepository;
import com.pip_coursework.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class PlayController {
    @Autowired
    EffectRepository effectRepository;
    @Autowired
    AbilityRepository abilityRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value= "/getAllItems", method = RequestMethod.GET)
    public String getAllItems(){
        //
        return "";
    }
}
