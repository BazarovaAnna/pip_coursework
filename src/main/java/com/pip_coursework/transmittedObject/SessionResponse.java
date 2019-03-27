package com.pip_coursework.transmittedObject;

import com.pip_coursework.entity.Session;

public class SessionResponse {
    public SessionResponse(Session session){
        this.sessionId = session.getId();
        this.isReady = true;
    }

    public SessionResponse(){
        this.isReady = false;
    }

    private long sessionId;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    private boolean isReady;

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
