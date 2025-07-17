package com.example.buenasnochesapi.mappers.impl;

import com.example.buenasnochesapi.mappers.Mapper;
import com.example.buenasnochesapi.models.dto.requests.RoomRequest;
import com.example.buenasnochesapi.models.dto.responses.RoomResponse;
import com.example.buenasnochesapi.models.entities.RoomEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component // So the bean can be injected.
@AllArgsConstructor //So Lombok can do construction injection.
public class RoomMapper implements Mapper<RoomRequest, RoomResponse, RoomEntity> {

    private RoomTypeMapper roomTypeMapper;

    @Override
    public RoomEntity mapRequestToEntity(RoomRequest request) {
        return RoomEntity.builder()
                .name( request.getName())
                .description(request.getDescription())
                .floor(request.getFloor())
                .maxGuests(request.getMaxGuests())
                .build();
    }

    @Override
    public RoomResponse mapEntityToResponse(RoomEntity entity) {

        return RoomResponse.builder()
                .id(entity.getRoomId())
                .name(entity.getName())
                .description(entity.getDescription())
                .floor(entity.getFloor())
                .maxGuests(entity.getMaxGuests())
                .roomType(roomTypeMapper.mapEntityToResponse(entity.getRoomType()))
                .build();
    }
}
