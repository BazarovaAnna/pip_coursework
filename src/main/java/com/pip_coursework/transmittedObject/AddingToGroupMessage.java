package com.pip_coursework.transmittedObject;

public class AddingToGroupMessage extends BaseGroupMessage{
    private boolean isConnection;

    public boolean isConnection() {
        return isConnection;
    }

    public void setConnection(boolean connection) {
        isConnection = connection;
    }
}
