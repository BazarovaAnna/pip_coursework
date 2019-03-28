package com.pip_coursework.transmittedObject;

import com.pip_coursework.entity.Character;
import com.pip_coursework.entity.Race;

public class CharacterInfoResponse {
    public CharacterInfoResponse(Character character, long gameId, boolean isReady){
        this.characterName = character.getName();
        this.race = character.getRace();
        this.gameId = gameId;
        this.isReady = isReady;
    }

    public CharacterInfoResponse(){}

    private String characterName;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    private Race race;

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    private long gameId;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    private boolean isReady;

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
