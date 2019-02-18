package com.pip_coursework.service;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.Game;
import com.pip_coursework.entity.GameState;
import com.pip_coursework.entity.User;
import com.pip_coursework.repository.GameRepository;
import com.pip_coursework.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Возвращает список игр, ожидающих игроков
    public ArrayList<Game> getListActiveGame() {
        ArrayList<Game> allGames = gameRepository.findAllByState(GameState.ACTIVESTATE);
        return allGames != null ? allGames : new ArrayList<>();
    }

    // Делает игру активной для поиска
    public void setGameActive(User gm, String name){
        Game game = gameRepository.findByGmAndName(gm, name);
        game.setState(GameState.ACTIVESTATE);
        gameRepository.save(game);
    }

    // Делает игру неактивной для поиска
    public void setGameInactive(User gm, String name){
        Game game = gameRepository.findByGmAndName(gm, name);
        game.setState(GameState.INACTIVESTATE);
        gameRepository.save(game);
    }

    // Создание новой игры
    public void createNewGame(User gm,
                               String name,
                               int personCount,
                               String password,
                               String description) throws Exception {

        if (!gameRepository.existsByNameAndState(name, GameState.ACTIVESTATE)) {
            Game game = new Game(gm, name, password, description, personCount);

            try {
                gameRepository.save(game);
                return;
            } catch (Exception ex) {
                throw new Exception("Что-то пошло не так во время записи в бд!");
            }
        }

        throw new Exception("Название такой игры уже существует!");
    }

    private ArrayList<Character> getPlayers(Game game){
        return groupRepository.findAllByGame(game);
    }

    // Получение списка игр для конкретного пользователя
    public ArrayList<Game> getUserGames(User user) {
        ArrayList<Game> games = gameRepository.findAllByGmAndState(user, GameState.ACTIVESTATE);
        games.forEach(x -> x.setGm(null));

        return games;
    }
}
