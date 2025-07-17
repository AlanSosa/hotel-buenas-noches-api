package com.example.buenasnochesapi.mappers.impl;

import com.example.buenasnochesapi.mappers.Mapper;
import com.example.buenasnochesapi.models.dto.requests.CreateGuestRequest;
import com.example.buenasnochesapi.models.dto.responses.GuestResponse;
import com.example.buenasnochesapi.models.entities.GuestEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GuestMapper implements Mapper<CreateGuestRequest, GuestResponse, GuestEntity> {

    private GuestTypeMapper guestTypeMapper;

    @Override
    public GuestEntity mapRequestToEntity(CreateGuestRequest request) {
        return GuestEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

    @Override
    public GuestResponse mapEntityToResponse(GuestEntity entity) {
        return GuestResponse.builder()
                .id(entity.getGuestId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .guestType(guestTypeMapper.mapEntityToResponse(entity.getGuestType()))
                .build();
    }
}
