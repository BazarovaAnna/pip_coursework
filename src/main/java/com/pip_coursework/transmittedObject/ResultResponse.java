package com.pip_coursework.transmittedObject;

import org.springframework.http.HttpStatus;

public class ResultResponse {
    public ResultResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

    public ResultResponse() {
        this.message = "OK";
        this.status = HttpStatus.OK;
    }

    private String message;
    private HttpStatus status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
