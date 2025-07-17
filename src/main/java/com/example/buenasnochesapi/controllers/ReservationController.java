package com.example.buenasnochesapi.controllers;

import com.example.buenasnochesapi.models.dto.requests.CheckInOutRequest;
import com.example.buenasnochesapi.models.dto.requests.CreateHotelRequest;
import com.example.buenasnochesapi.models.dto.requests.CreateReservationRequest;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.dto.responses.RoomResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.services.IReservationService;
import com.example.buenasnochesapi.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/reservation")
@AllArgsConstructor
public class ReservationController {

    private IReservationService reservationService;
    private IRoomService roomService;

    @PostMapping
    public ResponseEntity<IResponseMessage> createHotel(@RequestBody @Valid CreateReservationRequest reservationRequest){
        return new ResponseEntity<IResponseMessage>(reservationService.createReservation(reservationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/checkin/{roomId}")
    public ResponseEntity<IResponseMessage> checkinHotel(@RequestBody @Valid CheckInOutRequest reservationRequest, @PathVariable String roomId){
        //IResponseMessage response = reservationService.checkInReservation(reservationRequest, roomId);
        return new ResponseEntity<IResponseMessage>(reservationService.checkInReservation(reservationRequest, roomId), HttpStatus.CREATED);
    }
}
