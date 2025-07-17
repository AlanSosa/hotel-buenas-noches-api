package com.example.buenasnochesapi.exceptions.badrequest;

import com.example.buenasnochesapi.exceptions.ErrorMessage;
import com.example.buenasnochesapi.exceptions.notfound.HotelNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class BadRequestExceptionHandler {

    private final HttpStatus HTTP_STATUS_CODE = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = HotelNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleHotelNoteFoundException(HotelNotFoundException exception,
                                                                      WebRequest webRequest){

        return new ResponseEntity<ErrorMessage>(
                ErrorMessage
                        .builder()
                        .message(exception.getMessage())
                        .date(new Date()).build(),
                new HttpHeaders(), HTTP_STATUS_CODE);
    }

    @ExceptionHandler(value = RequestRoomFieldEmptyException.class)
    public ResponseEntity<ErrorMessage> handleNotRoomFoundWhenCreatingHotel(RequestRoomFieldEmptyException exception,
                                                                      WebRequest webRequest){

        return new ResponseEntity<ErrorMessage>(
                ErrorMessage
                        .builder()
                        .message(exception.getMessage())
                        .date(new Date()).build(),
                new HttpHeaders(), HTTP_STATUS_CODE);
    }

    //TODO: add exception when there's a Internal Server error when inserting the Hotel

}
