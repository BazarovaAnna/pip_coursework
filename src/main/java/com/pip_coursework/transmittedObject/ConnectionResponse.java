package com.pip_coursework.transmittedObject;

import com.pip_coursework.entity.Character;

public class ConnectionResponse {
    private String characterName;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public ConnectionResponse(Character character){
        characterName = character.getName();
    }
}
