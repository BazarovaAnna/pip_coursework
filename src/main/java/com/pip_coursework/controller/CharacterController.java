package com.pip_coursework.controller;

import com.pip_coursework.entity.*;
import com.pip_coursework.entity.Character;
import com.pip_coursework.repository.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;


@RestController
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EffectRepository effectRepository;
    @Autowired
    private CharacterEffectRepository characterEffectRepository;
    @Autowired
    private AbilityRepository abilityRepository;
    @Autowired
    private CharacterAbilityRepository characterAbilityRepository;

    @RequestMapping(value = "/character/getMoney", method = RequestMethod.GET)
    public String getMoneyById(@RequestParam("id") long id) {
        /*Logger logger = LoggerFactory.getLogger(CharacterController.class);
        Double result = characterRepository.findById(id).get(0).getPersMoney();
        logger.info(Double.toString(result)); */
        return Double.toString(characterRepository.findById(id).get(0).getPersMoney());
    }

    @RequestMapping(value = "/character/addMoney", method = RequestMethod.GET)
    public String getMoneyById(@RequestParam("id") long id, @RequestParam("money") double money) {
        /*Logger logger = LoggerFactory.getLogger(CharacterController.class);
        Double result = characterRepository.findById(id).get(0).getPersMoney();
        logger.info(Double.toString(result)); */
        String status = "success";
        try {
            Character character = characterRepository.findById(id).get(0);
            double persMoney = character.getPersMoney();
            if (persMoney + money < 0) {
                status = "Can not remove more money than character has!";
            } else {
                character.setPersMoney(persMoney + money);
                characterRepository.save(character);
            }
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }

    @RequestMapping(value = "/character/getCharacterInfo", method = RequestMethod.GET)
    public String getInfoByCharacterId(@RequestParam("id") long id) {
        return characterRepository.findById(id).get(0).toString();
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

    @RequestMapping(value = "/character/buyItem", method = RequestMethod.GET)
    public String addItemToCharacter(@RequestParam("character_id") long cId, @RequestParam("item_name") String iName) {
        String status = "success";
        try {
            Item addedItem = itemRepository.findByName(iName).get(0);
            Character character = characterRepository.findById(cId).get(0);
            if (character.getPersMoney() < addedItem.getPrice()) {
                status = "This item has too high price.";
            } else if (character.getMaxWeight() - getCurWeight(cId) < addedItem.getWeight()) {
                status = "This item is too heavy.";
            } else {
                character.setPersMoney(character.getPersMoney() - addedItem.getPrice());
                if (inventoryRepository.findByCharacterIdAndItemId(cId, addedItem.getId()).size() == 0) {
                    Inventory newInventory = new Inventory(addedItem, character);
                    inventoryRepository.save(newInventory);
                } else {
                    Inventory inventory = inventoryRepository.findByCharacterIdAndItemId(cId, addedItem.getId()).get(0);
                    if (inventory.getTimeSelling() == null || inventory.getTimeSelling().before(inventory.getTimeGetting())) {
                        status = "You already have this item!";
                    } else {
                        inventory.setTimeGetting();
                        inventoryRepository.save(inventory);
                    }
                }
                characterRepository.save(character);
            }
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }

    @RequestMapping(value = "/character/addItemToCharacter", method = RequestMethod.GET)
    public String addItemToCharacter(@RequestParam("character_id") long cId, @RequestParam("name") String iName,
                                     @RequestParam("description") String iDesc, @RequestParam("price") double iPrice,
                                     @RequestParam("weight") double iWeight) {
        String status = "success";
        try {
            ArrayList<Item> items = itemRepository.findByName(iName);
            Item item;
            if (items.size() == 0) {
                item = new Item(iName, iDesc, iPrice, iWeight);
                itemRepository.save(item);
            } else {
                item = items.get(0);
                if (item.getPrice() != iPrice || item.getWeight() != iWeight || !item.getDescription().equals(iDesc)) {
                    status = "Item with this name already exists!";
                    return status;
                }
                ArrayList<Inventory> inventories = inventoryRepository.findByCharacterIdAndItemId(cId, item.getId());
                if (inventories.size() > 0) {
                    if (inventories.get(0).getTimeSelling() == null ||
                            inventories.get(0).getTimeSelling().before(inventories.get(0).getTimeGetting())){
                        status = "This character already has this item!";
                        return status;
                    }
                }
            }
            Character character = characterRepository.findById(cId).get(0);
            if (character.getMaxWeight() - getCurWeight(cId) >= iWeight) {
                Inventory newInventory = new Inventory(item, character);
                inventoryRepository.save(newInventory);
            } else {
                status = "This item is too heavy.";
            }
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }

    @RequestMapping(value = "/character/removeItemFromCharacter", method = RequestMethod.GET)
    public String removeItemFromCharacter(@RequestParam("character_id") long cId, @RequestParam("item_name") String iName) {
        String status = "success";
        try {
            long iId = itemRepository.findByName(iName).get(0).getId();
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

    @RequestMapping(value = "/character/getCharacterEffects", method = RequestMethod.GET)
    public String getEffectsByCharacterId(@RequestParam("id") long id) {
        String result = "[ ";
        try {
            for (CharactersEffects charactersEffects : characterEffectRepository.findByCharacterId(id)) {
                result += charactersEffects.getEffect().toString() + ",";
            }
        } catch (Exception e) {
            result = "Unexpected fault";
        }
        result = result.substring(0, result.length() - 1) + "]";
        return result;
    }

    @RequestMapping(value = "/character/addEffectToCharacter", method = RequestMethod.GET)
    public String addEffectToCharacter(@RequestParam("character_id") long cId, @RequestParam("name") String eName,
                                     @RequestParam("description") String eDesc) {
        String status = "success";
        try {
            ArrayList<Effect> effects = effectRepository.findByName(eName);
            Effect effect;
            if (effects.size() == 0) {
                effect = new Effect(eName, eDesc);
                effectRepository.save(effect);
            } else {
                effect = effects.get(0);
                if (!effect.getDescription().equals(eDesc)) {
                    status = "Effect with this name already exists!";
                    return status;
                }
                if (characterEffectRepository.findByCharacterIdAndEffectId(cId, effect.getId()).size() > 0) {
                    status = "This character already has this effect!";
                    return status;
                }
            }
            Character character = characterRepository.findById(cId).get(0);
            CharactersEffects charactersEffects = new CharactersEffects(effect, character);
            characterEffectRepository.save(charactersEffects);
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }

    @RequestMapping(value = "/character/removeEffectFromCharacter", method = RequestMethod.GET)
    public String removeEffectFromCharacter(@RequestParam("character_id") long cId, @RequestParam("effect_name") String eName) {
        String status = "success";
        try {
            long eId = effectRepository.findByName(eName).get(0).getId();
            characterEffectRepository.deleteByCharacterIdAndEffectId(cId, eId);
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }

    @RequestMapping(value = "/character/getCharacterAbilities", method = RequestMethod.GET)
    public String getAbilitiesByCharacterId(@RequestParam("id") long id) {
        String result = "[ ";
        try {
            for (CharactersAbilities charactersAbilities : characterAbilityRepository.findByCharacterId(id)) {
                result += charactersAbilities.getAbility().toString() + ",";
            }
        } catch (Exception e) {
            result = "Unexpected fault";
        }
        result = result.substring(0, result.length() - 1) + "]";
        return result;
    }

    @RequestMapping(value = "/character/addAbilityToCharacter", method = RequestMethod.GET)
    public String addAbilityToCharacter(@RequestParam("character_id") long cId, @RequestParam("name") String aName,
                                       @RequestParam("description") String aDesc, @RequestParam("p_a") char pa) {
        String status = "success";
        try {
            ArrayList<Ability> abilities = abilityRepository.findByName(aName);
            Ability ability;
            if (abilities.size() == 0) {
                ability = new Ability(aName, aDesc, pa);
                abilityRepository.save(ability);
            } else {
                ability = abilities.get(0);
                if (!ability.getDescription().equals(aDesc)) {
                    status = "Ability with this name already exists!";
                    return status;
                }
                if (characterAbilityRepository.findByCharacterIdAndAbilityId(cId, ability.getId()).size() > 0) {
                    status = "This character already has this ability/perk!";
                    return status;
                }
            }
            Character character = characterRepository.findById(cId).get(0);
            CharactersAbilities charactersAbilities = new CharactersAbilities(ability, character, new Date());
            characterAbilityRepository.save(charactersAbilities);
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }

    @RequestMapping(value = "/character/removeAbilityFromCharacter", method = RequestMethod.GET)
    public String removeAbilityFromCharacter(@RequestParam("character_id") long cId, @RequestParam("ability_name") String aName) {
        String status = "success";
        try {
            long aId = abilityRepository.findByName(aName).get(0).getId();
            characterAbilityRepository.deleteByCharacterIdAndAbilityId(cId, aId);
        } catch (Exception e) {
            status = "Unexpected fault";
        }
        return status;
    }

    @RequestMapping(value = "/character/getCurWeight", method = RequestMethod.GET)
    public String getTestWeight(@RequestParam("character_id") long cId) {
        return Double.toString(getCurWeight(cId));
    }

    private double getCurWeight(long cId) {
        long curWeight = 0;
        for (Inventory inventory : inventoryRepository.findByCharacterId(cId)) {
            if (inventory.getTimeSelling() == null || inventory.getTimeSelling().before(inventory.getTimeGetting())) {
                curWeight += inventory.getItem().getWeight();
            }
        }
        return  curWeight;
    }
}
