package com.pip_coursework.controller;

import com.pip_coursework.entity.Member;
import com.pip_coursework.entity.Session;
import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.Game;
import com.pip_coursework.repository.CharacterRepository;
import com.pip_coursework.repository.GameRepository;
import com.pip_coursework.repository.MemberRepository;
import com.pip_coursework.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SessionController {
    @Autowired
    SessionRepository repository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CharacterRepository characterRepository;
    @Autowired
    GameRepository gameRepository;

    @RequestMapping("/SessionController/add")
    public  String add(@RequestParam("game_id") long gameId, @RequestParam("gms_rating") float gmsRating){
        String executiongStatus = "";
        Game game = gameRepository.findById(gameId).get(0);
        repository.save(new Session(gameId, game, gmsRating));
        executiongStatus = "Done";
        return executiongStatus;
    }


    @RequestMapping("/SessionController/findall")
    public  String findAll(){
        String result = "";

        for(Session session : repository.findAll()){
            result += session.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/SessionController/findbyid")
    public  String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findById(id).toString();
        if (result.equals("")) {
            return "There're no sessions with this is";
        }
        return result;
    }

    @RequestMapping("/SessionController/findmembers")
    public String findMembersById(@RequestParam("id") long id) {
        String result = "";
        result = memberRepository.findBySessionId(id).toString();
        if (result.equals("")) {
            return "There're no members referenced to session with this id";
        }
        return  result;
    }


    @RequestMapping("/SessionController/setmember")
    public String setMemberById(@RequestParam("id") long id, long memberId, float charactersRating){
        String result = "done";

        try {
            Character member = characterRepository.findById(memberId).get(0);
            Session session = repository.findById(id).get(0);
            session.addMemberToSession(member);
            characterRepository.findById(memberId).get(0).addSessionToCharacter(session);
            repository.save(session);
            characterRepository.save(member);
            memberRepository.save(new Member(id, memberId, session, member, charactersRating));
        }

        catch (DataIntegrityViolationException e) {
            result = "There're no session or character with this id";
        }

        finally {
            return result;
        }
    }

    //TODO: removemember
}
