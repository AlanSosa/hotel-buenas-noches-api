package com.example.buenasnochesapi.services.impl;

import com.example.buenasnochesapi.exceptions.notfound.GuestNotFoundException;
import com.example.buenasnochesapi.mappers.impl.GuestMapper;
import com.example.buenasnochesapi.mappers.impl.GuestTypeMapper;
import com.example.buenasnochesapi.models.dto.requests.CreateGuestRequest;
import com.example.buenasnochesapi.models.dto.responses.GuestResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.models.dto.responses.status.ResponseMessageAndModel;
import com.example.buenasnochesapi.models.entities.GuestEntity;
import com.example.buenasnochesapi.models.entities.GuestTypeEntity;
import com.example.buenasnochesapi.repositories.GuestRepository;
import com.example.buenasnochesapi.repositories.GuestTypeRepository;
import com.example.buenasnochesapi.repositories.ReservationLogRepository;
import com.example.buenasnochesapi.services.IGuestService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements IGuestService {

    private GuestTypeRepository guestTypeRepository;
    private GuestRepository guestRepository;
    private ReservationLogRepository reservationLogRepository;

    private GuestMapper guestMapper;

    @Override
    @Transactional
    public GuestResponse createGuest(CreateGuestRequest createGuestRequest) {
        GuestTypeEntity guestTypeEntity = guestTypeRepository.findByName("NORMAL");

        GuestEntity guestToSave = guestMapper.mapRequestToEntity(createGuestRequest);
        guestToSave.setGuestId(UUID.randomUUID().toString());
        guestToSave.setGuestActive(true);
        guestToSave.setGuestType(guestTypeEntity);

        GuestEntity savedGuest = guestRepository.save(guestToSave);

        GuestResponse guestResponse = guestMapper.mapEntityToResponse(savedGuest);
        return guestResponse;
    }

    @Override
    @Transactional
    public IResponseMessage deleteGuestByName(String name) {
        GuestEntity guestToDelete = guestRepository.findByFirstName(name);

        System.out.println("Guest is Active : " + guestToDelete.getGuestActive().toString());
        if(guestToDelete == null ||
                StringUtils.equals(
                        guestToDelete.getGuestActive().toString(),"false")
        ){
            throw new GuestNotFoundException("Guest not found");
        }

        guestToDelete.setGuestActive(false);
        GuestEntity deletedGuest = guestRepository.save(guestToDelete);

        GuestResponse guestResponse = guestMapper.mapEntityToResponse(deletedGuest);
        IResponseMessage response = new ResponseMessageAndModel("Success", guestResponse);
        return response;
    }

    @Override
    @Transactional
    public IResponseMessage deleteGuestByEmail(String email) {
        GuestEntity guestToDelete = guestRepository.findByEmail(email);

        if(guestToDelete == null ||
                StringUtils.equals(
                        guestToDelete.getGuestActive().toString(),"false")
        ){
            throw new GuestNotFoundException("Guest not found");
        }

        guestToDelete.setGuestActive(false);
        GuestEntity deletedGuest = guestRepository.save(guestToDelete);

        GuestResponse guestResponse = guestMapper.mapEntityToResponse(deletedGuest);
        IResponseMessage response = new ResponseMessageAndModel("Success", guestResponse);
        return response;
    }

    @Override
    @Transactional
    public IResponseMessage deleteGuestByGuestId(String guestId) {
        GuestEntity guestToDelete = guestRepository.findByGuestId(guestId);
        System.out.println("Guest is Active : " + guestToDelete.getGuestActive().toString());
        if(guestToDelete == null ||
                StringUtils.equals(
                        guestToDelete.getGuestActive().toString(),"false")
            ){
            throw new GuestNotFoundException("Guest not found");
        }

        guestToDelete.setGuestActive(false);
        GuestEntity deletedGuest = guestRepository.save(guestToDelete);

        GuestResponse guestResponse = guestMapper.mapEntityToResponse(deletedGuest);
        IResponseMessage response = new ResponseMessageAndModel("Success", guestResponse);
        return response;
    }
}
