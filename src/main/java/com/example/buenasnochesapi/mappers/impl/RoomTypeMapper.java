package com.example.buenasnochesapi.mappers.impl;

import com.example.buenasnochesapi.mappers.EntityToResponseOnlyMapper;
import com.example.buenasnochesapi.models.dto.responses.RoomTypeResponse;
import com.example.buenasnochesapi.models.entities.RoomTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component // So the bean can be injected.
@AllArgsConstructor //So Lombok can do construction injection.
public class RoomTypeMapper implements EntityToResponseOnlyMapper<RoomTypeResponse, RoomTypeEntity> {

    @Override
    public RoomTypeResponse mapEntityToResponse(RoomTypeEntity entity) {
        return RoomTypeResponse.builder()
                .name(entity.getName())
                .build();
    }
}
