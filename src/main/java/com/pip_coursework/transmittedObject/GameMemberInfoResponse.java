package com.pip_coursework.transmittedObject;

import com.pip_coursework.entity.Character;

public class GameMemberInfoResponse {
    public GameMemberInfoResponse(Character character, boolean isMe){
        this.characterId = character.getId();
        this.isGameMaster = false;
        this.isMe = isMe;
    }

    public  GameMemberInfoResponse(boolean isMe){
        this.isGameMaster = true;
        this.isMe = isMe;
    }

    public  GameMemberInfoResponse(){ }

    private long characterId;

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    private boolean isGameMaster;

    public boolean isGameMaster() {
        return isGameMaster;
    }

    public void setGameMaster(boolean gameMaster) {
        isGameMaster = gameMaster;
    }

    private boolean isMe;

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}
