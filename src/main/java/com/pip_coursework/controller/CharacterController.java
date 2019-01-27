package com.pip_coursework.controller;

import com.pip_coursework.entity.*;
import com.pip_coursework.entity.Character;
import com.pip_coursework.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class CharacterController {
    private final double MAXWEIGHT = 100;
    private final long INITIALLEVEL = 1;

    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    MemberRepository memberRepository;

    // TODO Нужно добавить сесси для передачи сущности юзера
    // TODO Добавить валидацию расс и классов из списка
    // TODO Каждой рассе и классу должен соответствовать параметр максимального веса, которые должны складываться
    @RequestMapping("/CharacterController/add")
    public String add(@RequestParam("userId") long userId,
                      @RequestParam("name") String name,
                      @RequestParam("userClass") String userClass,
                      @RequestParam("race") String race,
                      @RequestParam("story") String story,
                      @RequestParam("sex") char sex,
                      @RequestParam("condition") char[] condition){
        String executiongStatus = "";

        try{
            User curUser = getUser(userId);

            if(characterRepository.findByName(name).size()  > 0){
                throw  new DataIntegrityViolationException("");
            }

            executiongStatus = "Done";
        }
        catch (DataIntegrityViolationException e){
            executiongStatus = "Персонаж с таким ником уже существует уже существует";
        }
        catch (NullPointerException e){
            executiongStatus = "Неверный уникальный идентификатор пользователя";
        }
        finally {
            return  executiongStatus;
        }
    }

    @RequestMapping("/CharacterController/findall")
    public  String findAll(){
        String result = "";

        for(Character character : characterRepository.findAll()){
            result += character.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/CharacterController/findgames")
    public String findGamesById(@RequestParam("id") long id){
        String result = "";
        result = groupRepository.findByCharacterId(id).toString();
        if (result.equals("")) {
            return "There're no genres referenced to user with this id";
        }
        return  result;
    }

    @RequestMapping("/CharacterController/setgame")
    public String setGameById(@RequestParam("id") long id, long gameId){
        String result = "done";

        try {
            Game game = gameRepository.findById(gameId).get(0);
            Character character = characterRepository.findById(id).get(0);
            characterRepository.save(character);
            gameRepository.save(game);
            groupRepository.save(new Group(game, character));
        }

        catch (DataIntegrityViolationException e) {
            result = "There're no game or character with this id";
        }

        finally {
            return result;
        }
    }

    //TODO: @RequestMapping("/UserController/deletegenre")

    @RequestMapping("/CharacterController/findsessions")
    public String findSessionsById(@RequestParam("id") long id) {
        String result = "";
        result = memberRepository.findByCharacterId(id).toString();
        if (result.equals("")) {
            return "There're no sessions referenced to Character with this id";
        }
        return  result;
    }


    @RequestMapping("/CharacterController/setsession")
    public String setSessionById(@RequestParam("id") long id, long sessionId, float charactersRating){
        String result = "done";

        try {
            Session member = sessionRepository.findById(sessionId).get(0);
            Character character = characterRepository.findById(id).get(0);
            characterRepository.save(character);
            sessionRepository.save(member);
            memberRepository.save(new Member(member, character, charactersRating));
        }

        catch (DataIntegrityViolationException e) {
            result = "There're no session or character with this id";
        }

        finally {
            return result;
        }
    }

    //TODO: removesession и нормально предыдущий метод обозвать

    private User getUser(long userId){
        ArrayList<User> curUser = userRepository.findById(userId);
        return curUser.get(0);
    }
}
