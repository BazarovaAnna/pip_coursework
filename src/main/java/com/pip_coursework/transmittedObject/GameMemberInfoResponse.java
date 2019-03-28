package com.pip_coursework.transmittedObject;

public class GameMemberInfoResponse {
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
