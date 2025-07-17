package com.example.buenasnochesapi.services;

import com.example.buenasnochesapi.models.dto.requests.CheckInOutRequest;
import com.example.buenasnochesapi.models.dto.requests.CreateReservationRequest;
import com.example.buenasnochesapi.models.dto.responses.RoomResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;

public interface IReservationService {

    IResponseMessage createReservation (CreateReservationRequest reservationRequest);
    IResponseMessage checkInReservation (CheckInOutRequest checkRequest, String roomId);
}
