package com.example.buenasnochesapi.exceptions.notfound;

import com.example.buenasnochesapi.exceptions.ErrorMessage;
import com.example.buenasnochesapi.exceptions.badrequest.RequestRoomFieldEmptyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class NotFoundExceptionHandler {

    private final HttpStatus HTTP_STATUS_CODE = HttpStatus.NOT_FOUND;

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

    @ExceptionHandler(value = RoomTypeNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRoomTypeNotFoundException(RoomTypeNotFoundException exception,
                                                                      WebRequest webRequest){
        return new ResponseEntity<ErrorMessage>(
                ErrorMessage
                        .builder()
                        .message(exception.getMessage())
                        .date(new Date()).build(),
                new HttpHeaders(), HTTP_STATUS_CODE);
    }

    @ExceptionHandler(value = GuestNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleGuestNotFoundException(GuestNotFoundException exception,
                                                                        WebRequest webRequest){
        return new ResponseEntity<ErrorMessage>(
                ErrorMessage
                        .builder()
                        .message(exception.getMessage())
                        .date(new Date()).build(),
                new HttpHeaders(), HTTP_STATUS_CODE);
    }

    @ExceptionHandler(value = RoomNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleGuestNotFoundException(RoomNotFoundException exception,
                                                                     WebRequest webRequest){
        return new ResponseEntity<ErrorMessage>(
                ErrorMessage
                        .builder()
                        .message(exception.getMessage())
                        .date(new Date()).build(),
                new HttpHeaders(), HTTP_STATUS_CODE);
    }
}
