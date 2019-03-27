package com.pip_coursework.transmittedObject;

public class ReadyGroupMessage extends BaseGroupMessage {
    private boolean isReady;

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    private boolean isStart;

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }
}
