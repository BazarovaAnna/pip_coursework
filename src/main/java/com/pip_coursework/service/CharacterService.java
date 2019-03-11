package com.pip_coursework.service;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.Race;
import com.pip_coursework.entity.User;
import com.pip_coursework.repository.CharacterRepository;
import com.pip_coursework.repository.RaceRepository;
import com.pip_coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CharacterService {
    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Получение списка доступных рас
      */
    public ArrayList<String> getAllRace(){

        ArrayList<String> types = raceRepository.findDistinctTypes();

        return types;
    }

    /**
     * Получение изображений для конкретной расы
      */
    public ArrayList<String> getImageForRace(String race){
        return raceRepository.findImgByRace(race);
    }

    /**
     * Создание нового персонажа
      */
    public boolean addNewCharacter(
            String name,
            char sex,
            String userClass,
            User user,
            String imgPath,
            String type) {

            Race race = raceRepository.findByImgPath(imgPath);
            if(race == null){
                race = raceRepository.findByImgPath("../../resources/default/img/race/женщина_воин_орк.jpg");
            }

        if (!characterRepository.existsByName(name)){
            Character newCharacter = new Character(name, sex, userClass, user, race);

            try {
                userRepository.save(newCharacter.getUser());
                characterRepository.save(newCharacter);
                return true;
            }
            catch (Exception e){
                return false;

            }
        }

        return false;

    }

    /**
     * Получение списка персонажей для конкретного пользователя
      */
    public Object getAllCharacter(User user) {
        ArrayList<Character> characters = new ArrayList<Character>();

        characterRepository.findAllByUser(user).
                forEach(character -> {
                         character.setUser(null);
                         characters.add(character);
                });

        return characters;
    }

    /**
     * Получение персонажа по его имени
      */
    public Character getCharacterByName(String name){
        if(!characterRepository.existsByName(name)){
            return null;
        }

        return characterRepository.findByName(name);
    }
}
