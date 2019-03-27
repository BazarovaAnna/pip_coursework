package com.pip_coursework.transmittedObject;

import com.pip_coursework.entity.Game;

public class GameInfoResponse {
    public GameInfoResponse() { }

    public GameInfoResponse(Game game){
        this.name = game.getName();
        this.personCount = game.getPersonCount();
        this.description = game.getDescription();
        this.gameMaseterName = game.getGm().getLogin();
        this.gameId = game.getId();
    }

    private long gameId;

    private String name;

    private int personCount;

    private String description;

    private String gameMaseterName;

    public String getGameMaseterName() {
        return gameMaseterName;
    }

    public void setGameMaseterName(String gameMaseterName) {
        this.gameMaseterName = gameMaseterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
}
