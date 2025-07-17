package com.example.buenasnochesapi.models.dto.responses.status;

import java.io.Serializable;

public class ResponseMessage implements IResponseMessage, Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    public ResponseMessage(){

    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String setMessage(String message){
        return message;
    }

    public String getMessage(){
        return this.message;
    }
}
