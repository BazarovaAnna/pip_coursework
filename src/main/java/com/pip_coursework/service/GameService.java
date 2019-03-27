package com.pip_coursework.service;

import com.pip_coursework.entity.*;
import com.pip_coursework.entity.Character;
import com.pip_coursework.repository.CharacterRepository;
import com.pip_coursework.repository.GameRepository;
import com.pip_coursework.repository.GroupRepository;
import com.pip_coursework.repository.SessionRepository;
import com.pip_coursework.transmittedObject.CharacterInfoResponse;
import com.pip_coursework.transmittedObject.GameInfoResponse;
import com.pip_coursework.transmittedObject.SessionResponse;
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
    public ArrayList<GameInfoResponse> getListActiveGame() {
        ArrayList<Game> allGames = gameRepository.findAllByState(GameState.ACTIVESTATE);

        ArrayList<GameInfoResponse> gameInfoResponses = new ArrayList<>();

        allGames.forEach(game -> gameInfoResponses.add(new GameInfoResponse(game)));

        return gameInfoResponses;
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
    public ArrayList<GameInfoResponse> getUserGames(User user) {
        ArrayList<Game> games = gameRepository.findAllByGm(user);

        ArrayList<GameInfoResponse> gameInfoResponses = new ArrayList<>();

        games.forEach(game -> gameInfoResponses.add(new GameInfoResponse(game)));

        return gameInfoResponses;
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
    public boolean connectToGroup(long gameId, Character character) {
        Game game = gameRepository.findById(gameId);

        if(game != null){
            ArrayList<Group> groups = groupRepository.findAllByGame(game);

            if(character != null && game.getPersonCount() > groups.size()){

                Group group = new Group(game, character);

                groupRepository.save(group);

                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }


    /**
     *  Отключение персонажа от игровой группы
     * @param gameId Идентификатор игры
     * @param character Персонаж игрока
     * @throws Exception
     */
    public void disconnectFromGroup(long gameId, Character character) throws Exception {
        Game game = gameRepository.findById(gameId);

        if(character != null){
            Group group = new Group(game, character);

            groupRepository.delete(group);
        }
    }

    /**
     * Получение списка персонажей в группе
     * @param gameId Идентификатор игры
     * @return Список персонажей в группе
     * @throws Exception
     */
    public ArrayList<CharacterInfoResponse> getCharacterInGroup(long gameId) throws Exception {
        Game game = gameRepository.findById(gameId);

        if(game != null){
            ArrayList<CharacterInfoResponse> characters = new ArrayList<>();

            groupRepository.
                    findAllByGame(game).
                    forEach(group -> characters.add(new CharacterInfoResponse(group.character, gameId, group.isReady())));

            return characters;
        }
        else{
            throw new Exception("Попытка подключиться к несуществующей игре");
        }
    }

    /**
     * Оповещение о готовности начала игры
     */
    public void setReadyToStartGame(long gameId, String characterName){
        Character character = characterRepository.findByName(characterName);

        if(character != null){
            Group group = groupRepository.findByGameIdAndCharacterId(gameId, character.getId());
            if(group != null){
                group.setReady(true);

                groupRepository.save(group);
            }
        }
    }

    /**
     * Проверка, что все игроки нажали готов
     * @param gameId Идентификатор игры
     */
    public boolean checkAllIsReady(long gameId){
        Game game = gameRepository.findById(gameId);

        if(game != null){
            ArrayList<Group> groups = groupRepository.findAllByGame(game);
            int readyCount = 0;

            for(Group group : groups){
                if(group.isReady()){
                    readyCount++;
                }
            }

            return readyCount == game.getPersonCount();
        }

        return false;
    }

    /**
     * Создание игровой сессии
     * @param gameId Идентификатор игры
     * @return
     */
    public SessionResponse getGameSession(long gameId){
        Game game = gameRepository.findById(gameId);

        if(game != null){
            long lastId = -1;

            for(Session session : sessionRepository.findByGame(game)) {
                if(session.getId() > lastId){
                    lastId = session.getId();
                }
            }
            if(lastId != -1){
                return  new SessionResponse(sessionRepository.findById(lastId));
            }

        }

        return new SessionResponse();
    }
}
