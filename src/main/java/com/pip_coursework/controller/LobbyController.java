package com.pip_coursework.controller;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.User;
import com.pip_coursework.service.CharacterService;
import com.pip_coursework.service.GameService;
import com.pip_coursework.transmittedObject.AddingToGroupMessage;
import com.pip_coursework.transmittedObject.ConnectionResponse;
import com.pip_coursework.transmittedObject.GameInfoMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LobbyController {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private GameService gameService;


    @RequestMapping(value="/lobby", method = RequestMethod.GET)
    public String lobby(@AuthenticationPrincipal User user,
                        Model model){

        model.addAttribute("login", user.getLogin());

        return "lobby";
    }

    /**
     * Получение списка игр конкретного ГМа
      */
    @RequestMapping(value = "/lobby/usergames", method = RequestMethod.GET)
    public ResponseEntity<?> getUserGames(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(gameService.getUserGames(user), HttpStatus.OK);
    }

    /**
     * Получение списка активных игр
      */
    @RequestMapping(value = "/lobby/allgames", method = RequestMethod.GET)
    public ResponseEntity<?> getAllGames(@AuthenticationPrincipal User user){
        // TODO возможно нужно подгружать кусками
        return new ResponseEntity<>(gameService.getListActiveGame(), HttpStatus.OK);
    }

    /**
     * Создание новой игры
      */
    @RequestMapping(value = "/lobby/newgame", method = RequestMethod.POST)
    public ResponseEntity<?> createNewGame(@RequestParam("name") String name,
                                           @RequestParam("personCount") String personCount,
                                           @RequestParam("password") String password,
                                           @RequestParam("description") String description,
                                           @AuthenticationPrincipal User user){
        try {
            gameService.createNewGame(user, name, Integer.parseInt(personCount), password, description);
            return new ResponseEntity<>((String) "Игра успешно создана!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>((String)ex.getMessage(), HttpStatus.OK);
        }
    }

    /**
     * Расшаривание созданной игры для других игроков(Создание игровой сессии)
      */
    @RequestMapping(value = "/lobby/sharegame", method = RequestMethod.POST)
    public ResponseEntity<?> shareGame(@RequestBody GameInfoMessage gameInfoMessage,
                                       @AuthenticationPrincipal User gm){
        try {
            gameService.setGameActive(gm, gameInfoMessage.getGameName());
            gameService.createGameSession(gm);

            return new ResponseEntity<>((String) "Игра активна для поиска!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>((String)ex.getMessage(), HttpStatus.OK);
        }
    }

    /**
    * Сокрытие созданной игры для других игроков(сокрытие игровой сессии)
     */
    @RequestMapping(value = "/lobby/hidegame", method = RequestMethod.POST)
    public ResponseEntity<?> hideGame(@RequestBody GameInfoMessage gameInfoMessage,
                                      @AuthenticationPrincipal User gm){
        try {
            gameService.setGameInactive(gm, gameInfoMessage.getGameName());
            gameService.hideGameSession(gm);

            return new ResponseEntity<>((String) "Игра неактивна для поиска!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>((String)ex.getMessage(), HttpStatus.OK);
        }
    }

    /**
     * STOMP messaging
     * Подключение к ожидающей группе
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/lobby/connect")
    @SendTo("/topic/joining")
    public ConnectionResponse joiningToGroup(AddingToGroupMessage message) throws  Exception {

        Character character = characterService.getCharacterByName(message.getCharacterName());
        if(character != null){
            gameService.connectToGroup(message.getGameId(), character);

            return new ConnectionResponse(character);
        }

        throw new Exception();
    }
}
