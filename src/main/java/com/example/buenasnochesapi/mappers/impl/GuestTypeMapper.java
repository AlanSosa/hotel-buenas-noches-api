package com.example.buenasnochesapi.mappers.impl;

import com.example.buenasnochesapi.mappers.EntityToResponseOnlyMapper;
import com.example.buenasnochesapi.models.dto.responses.GuestTypeResponse;
import com.example.buenasnochesapi.models.entities.GuestTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GuestTypeMapper implements EntityToResponseOnlyMapper<GuestTypeResponse, GuestTypeEntity> {

    @Override
    public GuestTypeResponse mapEntityToResponse(GuestTypeEntity entity) {
        return GuestTypeResponse.builder()
                .name(entity.getName())
                .build();
    }
}
