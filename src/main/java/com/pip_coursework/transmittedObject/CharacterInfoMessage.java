package com.pip_coursework.transmittedObject;

import com.pip_coursework.entity.Character;

public class CharacterInfoMessage {
    private String characterName;

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public CharacterInfoMessage(Character character){
        this.characterName = character.getName();
    }

    public CharacterInfoMessage(){}
}
