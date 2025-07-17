package com.example.buenasnochesapi.services;

import com.example.buenasnochesapi.models.dto.requests.CreateGuestRequest;
import com.example.buenasnochesapi.models.dto.responses.GuestResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;

public interface IGuestService {
    GuestResponse createGuest(CreateGuestRequest createGuestRequest);
    IResponseMessage deleteGuestByName(String name);
    IResponseMessage deleteGuestByEmail(String email);
    IResponseMessage deleteGuestByGuestId(String guestId);
}
