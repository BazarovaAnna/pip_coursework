package com.pip_coursework.controller;

import com.pip_coursework.entity.Game;
import com.pip_coursework.entity.Genre;
import com.pip_coursework.entity.Rule;
import com.pip_coursework.entity.User;
import com.pip_coursework.repository.GameRepository;
import com.pip_coursework.repository.RuleRepository;
import com.pip_coursework.repository.UserRepository;
import com.pip_coursework.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GameController {
    @Autowired
    GameRepository repository;
    @Autowired
    RuleRepository ruleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenreRepository genreRepository;

    @RequestMapping("GameController/add")
    public  String add(@RequestParam("genre_id") long genre_id, @RequestParam("rules_id") long rules_id,
                       @RequestParam("gm_id") long gm_id, @RequestParam("state") String state){
        String executiongStatus = "";
        Genre genre = getGenre(genre_id);
        Rule rules = getRules(rules_id);
        User gm = getUser(gm_id);
        repository.save(new Game(genre, rules, gm, state));

        executiongStatus = "Done";
        return  executiongStatus;
    }

    @RequestMapping("GameController/findall")
    public  String findAll(){
        String result = "";

        for(Game game : repository.findAll()){
            result += game.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("GameController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        if (result.equals("")) {
            return "There're no rules with this is";
        }
        return  result;
    }

    private User getUser(long userId){
        ArrayList<User> curUser = userRepository.findById(userId);
        return curUser.get(0);
    }

    private Rule getRules(long rulesId){
        ArrayList<Rule> curRule = ruleRepository.findById(rulesId);
        return curRule.get(0);
    }

    private Genre getGenre(long genreId){
        ArrayList<Genre> curGenre = genreRepository.findById(genreId);
        return curGenre.get(0);
    }
}
