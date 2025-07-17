package com.example.buenasnochesapi.services;

import com.example.buenasnochesapi.models.dto.requests.RoomRequest;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.dto.responses.RoomResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;

import java.util.Date;
import java.util.List;

public interface IRoomService {

    HotelResponse createRoomByHotelId(String hotelId, RoomRequest roomRequest);
    RoomResponse updateRoomByRoomId(String roomId, RoomRequest roomRequest);
    IResponseMessage removeRoomByRoomId(String roomId);
    RoomResponse findRoomByRoomId(String roomId);
    List<RoomResponse> findRoomAvailableByHotelId(String hotelId, Date startDate, Date endDate);
    RoomResponse findRoomAvailableByHotelIdAndRoomId(String hotelId, String roomId, Date startDate, Date endDate);
}
