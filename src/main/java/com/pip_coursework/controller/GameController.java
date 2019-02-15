package com.pip_coursework.controller;

import com.pip_coursework.entity.Game;
import com.pip_coursework.entity.Genre;
import com.pip_coursework.entity.Rules;
import com.pip_coursework.entity.User;
import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.Group;
import com.pip_coursework.repository.*;
import com.pip_coursework.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value= "/gamefield", method = RequestMethod.GET)
    public String gamefield(@AuthenticationPrincipal User user,
                            Model model){

        model.addAttribute("login", user.getLogin());

        return "gamefield";
    }










    @Autowired
    GameRepository repository;
    @Autowired
    RulesRepository rulesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    CharacterRepository characterRepository;

    @RequestMapping("/GameController/add")
    public  String add(@RequestParam("genre_id") long genre_id, @RequestParam("rules_id") long rules_id,
                       @RequestParam("gm_id") long gm_id, @RequestParam("state") String state){
        String executiongStatus = "";
        Genre genre = getGenre(genre_id);
        Rules rules = getRules(rules_id);
        User gm = getUser(gm_id);


        executiongStatus = "Done";
        return  executiongStatus;
    }

    @RequestMapping("/GameController/findall")
    public  String findAll(){
        String result = "";

        for(Game game : repository.findAll()){
            result += game.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/GameController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        if (result.equals("")) {
            return "There're no rules with this is";
        }
        return  result;
    }

    @RequestMapping("/GameController/findcharacters")
    public String findCharactersById(@RequestParam("id") long id){
        String result = "";
        result = groupRepository.findByGameId(id).toString();
        if (result.equals("")) {
            return "There're no genres referenced to user with this id";
        }
        return  result;
    }

    @RequestMapping("/GameController/setcharacter")
    public String setCharaterById(@RequestParam("id") long id, long characterId){
        String result = "done";

        try {
            Character character = characterRepository.findById(characterId).get(0);
            Game game = repository.findById(id).get(0);
            repository.save(game);
            characterRepository.save(character);
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


    private User getUser(long userId){
        ArrayList<User> curUser = userRepository.findById(userId);
        return curUser.get(0);
    }

    private Rules getRules(long rulesId){
        ArrayList<Rules> curRules = rulesRepository.findById(rulesId);
        return curRules.get(0);
    }

    private Genre getGenre(long genreId){
        ArrayList<Genre> curGenre = genreRepository.findById(genreId);
        return curGenre.get(0);
    }
}
