package com.pip_coursework.controller;

import com.pip_coursework.entity.Inventory;
import com.pip_coursework.entity.Item;
import com.pip_coursework.entity.Character;
import com.pip_coursework.repository.CharacterRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.pip_coursework.repository.InventoryRepository;
import com.pip_coursework.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/character/getMoney", method = RequestMethod.GET)
    public String getMoneyById(@RequestParam("id") long id) {
        /*Logger logger = LoggerFactory.getLogger(CharacterController.class);
        Double result = characterRepository.findById(id).get(0).getPersMoney();
        logger.info(Double.toString(result)); */
        return Double.toString(characterRepository.findById(id).get(0).getPersMoney());
    }

    @RequestMapping(value = "/character/getCharacterItems", method = RequestMethod.GET)
    public String getItemsByCharacterId(@RequestParam("id") long id) {
        String result = "[ ";
        try {
            for (Inventory inventory : inventoryRepository.findByCharacterId(id)) {
                if (inventory.getTimeSelling() == null || inventory.getTimeSelling().before(inventory.getTimeGetting())) {
                    result += inventory.getItem().toString() + ",";
                }
            }
        } catch (Exception e) {
            result = "Unexpected fault";
        }
        result = result.substring(0, result.length() - 1) + "]";
        return result;
    }

    @RequestMapping(value = "/character/addItemToCharacter", method = RequestMethod.GET)
    public String addItemToCharacter(@RequestParam("character_id") long cId, @RequestParam("item_id") long iId) {
        String status = "success";
        //try {
            Item addedItem = itemRepository.findById(iId).get(0);
            Character character = characterRepository.findById(cId).get(0);
            long curWeight = 0;
            for (Inventory inventory : inventoryRepository.findByCharacterId(cId)) {
                curWeight += inventory.getItem().getWeight();
            }
            if (character.getPersMoney() < addedItem.getPrice()) {
                status = "This item has too high price.";
            } else if (character.getMaxWeight() - curWeight < addedItem.getWeight()) {
                status = "This item is too heavy.";
            } else {
                character.setPersMoney(character.getPersMoney() - addedItem.getPrice());
                if (inventoryRepository.findByCharacterIdAndItemId(cId, iId).size() == 0) {
                    Inventory newInventory = new Inventory(addedItem, character);
                    inventoryRepository.save(newInventory);
                } else {
                    Inventory inventory = inventoryRepository.findByCharacterIdAndItemId(cId, iId).get(0);
                    inventory.setTimeGetting();
                    inventoryRepository.save(inventory);
                }
                characterRepository.save(character);
            }
        /*} catch (Exception e) {
            status = "Unexpected fault";
        }*/
        return status;
    }

    @RequestMapping(value = "/character/removeItemFromCharacter", method = RequestMethod.GET)
    public String removeItemFromCharacter(@RequestParam("character_id") long cId, @RequestParam("item_id") long iId) {
        String status = "success";
        try {
            if (inventoryRepository.findByCharacterIdAndItemId(cId, iId).size() > 0) {
                Item item = itemRepository.findById(iId).get(0);
                Character character = characterRepository.findById(cId).get(0);
                character.setPersMoney(character.getPersMoney() + item.getPrice());
                characterRepository.save(character);
                Inventory inventory = inventoryRepository.findByCharacterIdAndItemId(cId, iId).get(0);
                inventory.setTimeSelling();
                inventoryRepository.save(inventory);
            }
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }
}
