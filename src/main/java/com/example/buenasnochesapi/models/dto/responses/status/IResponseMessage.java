package com.example.buenasnochesapi.models.dto.responses.status;

public interface IResponseMessage<T> {

    String setMessage(String message);
    String getMessage();
}
