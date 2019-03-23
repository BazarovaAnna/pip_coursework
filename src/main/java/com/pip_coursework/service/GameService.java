package com.pip_coursework.service;

import com.pip_coursework.entity.*;
import com.pip_coursework.entity.Character;
import com.pip_coursework.repository.CharacterRepository;
import com.pip_coursework.repository.GameRepository;
import com.pip_coursework.repository.GroupRepository;
import com.pip_coursework.repository.SessionRepository;
import com.pip_coursework.transmittedObject.CharacterInfoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CharacterRepository characterRepository;

    /**
     * Возвращает список игр, ожидающих игроков
      */
    public ArrayList<Game> getListActiveGame() {
        ArrayList<Game> allGames = gameRepository.findAllByState(GameState.ACTIVESTATE);
        return allGames != null ? allGames : new ArrayList<>();
    }

    /**
     * Делает игру активной для поиска
      */
    public void setGameActive(User gm, String name){
        Game game = gameRepository.findByGmAndName(gm, name);
        game.setState(GameState.ACTIVESTATE);
        gameRepository.save(game);
    }

    /**
     * Делает игру неактивной для поиска
      */
    public void setGameInactive(User gm, String name){
        Game game = gameRepository.findByGmAndName(gm, name);
        game.setState(GameState.INACTIVESTATE);
        gameRepository.save(game);
    }

    /**
     * Создание новой игры
      */
    public void createNewGame(User gm,
                               String name,
                               int personCount,
                               String password,
                               String description) throws Exception {

        if (!gameRepository.existsByNameAndState(name, GameState.INACTIVESTATE)) {
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


    /**
     * Получение списка игр для конкретного пользователя
      */
    public ArrayList<Game> getUserGames(User user) {
        ArrayList<Game> games = gameRepository.findAllByGm(user);
        games.forEach(x -> x.setGm(null));

        return games;
    }

    /**
     * Создание игровой сессии, привязанной к конкретному ГМу
     * @param gm конкретный ГМ
     */
    public void createGameSession(User gm){
        ArrayList<Game> games = gameRepository.findAllByGmAndState(gm, GameState.ACTIVESTATE);

        if(games.size() > 0){
            Session session = new Session(games.get(0));

            sessionRepository.save(session);
        }
    }

    /**
     * Сокрытие игровой сессии, привязанной к конкретному ГМу
     * @param gm конкретный ГМ
     */
    public void hideGameSession(User gm){
        ArrayList<Game> games = gameRepository.findAllByGmAndState(gm, GameState.INACTIVESTATE);

        if(games.size() > 0){
            Session session = new Session(games.get(0));

            sessionRepository.save(session);
        }
    }

    /**
     * Подключение персонажа к игровой группе
      * @param gameId Идентификатор игры
     * @param character Персонаж игрока
     * @throws Exception
     */
    public void connectToGroup(long gameId, Character character) throws Exception {
        Game game = gameRepository.findById(gameId);

        if(game != null){
            if(character != null){
                Group group = new Group(game, character);

                groupRepository.save(group);
            }
        }
        else{
            throw new Exception("Попытка подключиться к несуществующей игре");
        }
    }

    /**
     * Получение списка персонажей в группе
     * @param gameId Идентификатор игры
     * @return Список персонажей в группе
     * @throws Exception
     */
    public ArrayList<CharacterInfoMessage> getCharacterInGroup(long gameId) throws Exception {
        Game game = gameRepository.findById(gameId);

        if(game != null){
            ArrayList<CharacterInfoMessage> characters = new ArrayList<>();
            CharacterInfoMessage test = new CharacterInfoMessage();
            test.setCharacterName("Тест");
            characters.add(test);

            groupRepository.
                    findAllByGame(game).
                    forEach(group -> characters.add(new CharacterInfoMessage(group.character)));

            return characters;
        }
        else{
            throw new Exception("Попытка подключиться к несуществующей игре");
        }
    }
}
