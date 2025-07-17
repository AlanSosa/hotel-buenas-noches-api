package com.example.buenasnochesapi.mappers.impl;

import com.example.buenasnochesapi.mappers.Mapper;
import com.example.buenasnochesapi.models.dto.requests.CreateHotelRequest;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.entities.HotelEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component // So the bean can be injected.
@AllArgsConstructor //So Lombok can do construction injection.
public class HotelMapper implements Mapper<CreateHotelRequest, HotelResponse, HotelEntity> {

    private RoomMapper roomMapper;

    @Override
    public HotelEntity mapRequestToEntity(CreateHotelRequest request) {
        return HotelEntity.builder()
                .name( request.getName() )
                .description( request.getDescription())
                .stars( request.getStars() )
                .build();
    }

    @Override
    public HotelResponse mapEntityToResponse(HotelEntity entity) {

        if(entity.getRooms() == null || entity.getRooms().isEmpty()){
            return HotelResponse.builder()
                    .id(entity.getHotelId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .stars(entity.getStars())
                    .build();
        }

        return HotelResponse.builder()
                .id(entity.getHotelId())
                .name(entity.getName())
                .description(entity.getDescription())
                .stars(entity.getStars())
                .rooms(entity.getRooms().stream().map(
                        roomEntity -> roomMapper.mapEntityToResponse(roomEntity)
                ).collect(Collectors.toList()))
                .build();
    }
}
