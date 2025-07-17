package com.example.buenasnochesapi.services;

import com.example.buenasnochesapi.models.dto.requests.CreateHotelRequest;
import com.example.buenasnochesapi.models.dto.requests.UpdateHotelRequest;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.models.entities.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHotelService {

    HotelResponse createHotel(CreateHotelRequest hotel);
    IResponseMessage deleteHotelById(String hotelId);
    List<HotelResponse> listAllHotels();
    IResponseMessage updateHotelById(String hotelId, UpdateHotelRequest updateHotelRequest);
    Page<HotelResponse> hotelPageable(Pageable pageable);
    HotelResponse getHotelById(String hotelId);
    HotelResponse getHotelByName(String hotelName);
}
