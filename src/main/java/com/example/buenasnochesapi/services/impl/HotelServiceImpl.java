package com.example.buenasnochesapi.services.impl;

import com.example.buenasnochesapi.exceptions.notfound.HotelNotFoundException;
import com.example.buenasnochesapi.exceptions.badrequest.RequestRoomFieldEmptyException;
import com.example.buenasnochesapi.exceptions.notfound.RoomTypeNotFoundException;
import com.example.buenasnochesapi.mappers.impl.HotelMapper;
import com.example.buenasnochesapi.mappers.impl.RoomMapper;
import com.example.buenasnochesapi.models.dto.requests.CreateHotelRequest;
import com.example.buenasnochesapi.models.dto.requests.UpdateHotelRequest;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.dto.responses.RoomResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.models.dto.responses.status.ResponseMessageAndModel;
import com.example.buenasnochesapi.models.entities.HotelEntity;
import com.example.buenasnochesapi.models.entities.RoomEntity;
import com.example.buenasnochesapi.models.entities.RoomTypeEntity;
import com.example.buenasnochesapi.repositories.HotelRepository;
import com.example.buenasnochesapi.repositories.RoomRepository;
import com.example.buenasnochesapi.repositories.RoomTypeRepository;
import com.example.buenasnochesapi.services.IHotelService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements IHotelService {


    //Lombok is doing Constructor Injection, so we don't need to do @Autowired
    private HotelRepository hotelRepository;
    private RoomTypeRepository roomTypeRepository;
    private RoomRepository roomRepository;

    private RoomMapper roomMapper;
    private HotelMapper hotelMapper;

    @Override
    @Transactional
    public HotelResponse createHotel(CreateHotelRequest hotelCreateRequest) {
        List<RoomEntity> roomEntitiesToSave = null;
        if(!CollectionUtils.isEmpty( hotelCreateRequest.getRooms())){
            roomEntitiesToSave = hotelCreateRequest.getRooms().stream().map(
                    room -> {

                        //TODO: Validate if we can remove this validate since GlobalExceptionHandler already handles this.
                        if( StringUtils.isEmpty(room.getRoomType()) ) {

                            //Throw a badRequest response
                            throw new RequestRoomFieldEmptyException("Room Type can't be empty");
                        }

                        RoomTypeEntity roomTypeToSave = roomTypeRepository.findByName(room.getRoomType());

                        if (roomTypeToSave == null) {
                            //Throw a notfound response
                            throw new RoomTypeNotFoundException("Room Type not found");
                        }

                        //RoomEntity roomEntityToReturn = mapper.map(room, RoomEntity.class);
                        RoomEntity roomEntityToReturn = roomMapper.mapRequestToEntity( room );
                        roomEntityToReturn.setRoomId(UUID.randomUUID().toString());
                        roomEntityToReturn.setRoomType(roomTypeToSave);
                        return roomEntityToReturn;
                    }
            ).collect(Collectors.toList());
        }

        HotelEntity hotelToSave = hotelMapper.mapRequestToEntity( hotelCreateRequest );
        hotelToSave.setHotelId(UUID.randomUUID().toString());

        //Save the hotel so we can have back with a primary key.
        HotelEntity hotelEntity = hotelRepository.save( hotelToSave );
        //Assign the hotel to the room elements.
        if(!CollectionUtils.isEmpty(roomEntitiesToSave)){
            roomEntitiesToSave.stream().forEach( room -> {
                room.setHotel(hotelEntity);
                roomRepository.save(room);
            });
        }

        //Set the rooms for the response.
        hotelEntity.setRooms(roomEntitiesToSave);

        HotelResponse response = hotelMapper.mapEntityToResponse(hotelEntity);
        return response;
    }

    @Override
    @Transactional
    public IResponseMessage deleteHotelById(String hotelId) {
        HotelEntity hotelToDelete = hotelRepository.findByHotelId(hotelId);
        if(hotelToDelete == null){
            throw new HotelNotFoundException("Hotel not found");
        }

        HotelResponse hotelToReturn = hotelMapper.mapEntityToResponse( hotelToDelete);
        hotelRepository.delete( hotelToDelete );
        IResponseMessage response = new ResponseMessageAndModel("Success", hotelToReturn);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelResponse> listAllHotels() {
        List<HotelEntity> allHotels = hotelRepository.findAll();

        if(allHotels.isEmpty() || allHotels == null){
            //Throw a Not Found response
            throw new HotelNotFoundException("No hotels were found");
        }

        List<HotelResponse> hotelResponse = allHotels
                .stream()
                .map( hotelEntity -> hotelMapper.mapEntityToResponse(hotelEntity))
                .collect(Collectors.toList());

        return hotelResponse;
    }

    @Override
    @Transactional
    public IResponseMessage updateHotelById(String hotelId, UpdateHotelRequest updateHotelRequest) {
        HotelEntity hotelToUpdate = hotelRepository.findByHotelId(hotelId);

        if(hotelToUpdate == null){
            throw new HotelNotFoundException("Hotel Not found");
        }

        HotelEntity hotel = hotelToUpdate;
        hotel.setName(updateHotelRequest.getName());
        hotel.setDescription(updateHotelRequest.getDescription());
        hotel.setStars(updateHotelRequest.getStars());

        final HotelEntity updatedHotel = hotelRepository.save(hotel);

        HotelResponse hotelToReturn = hotelMapper.mapEntityToResponse(updatedHotel);

        IResponseMessage response = new ResponseMessageAndModel("Success", hotelToReturn);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HotelResponse> hotelPageable(Pageable pageable) {
        Page<HotelEntity> hotels = hotelRepository.findAll(pageable);

        if(hotels.isEmpty()){
            throw new HotelNotFoundException("No hotels were found");
        }

        //Here we use Function functional interface to rewrite change from HotelEntity to HotelResponse.
        Page<HotelResponse> hotelsPageable = hotels.map(new Function<HotelEntity, HotelResponse>() {
            @Override
            public HotelResponse apply(HotelEntity entity) {
                HotelResponse dto = new HotelResponse();
                dto = hotelMapper.mapEntityToResponse(entity);
                return dto;
            }
        });

        return hotelsPageable;
    }

    @Override
    @Transactional(readOnly = true)
    public HotelResponse getHotelById(String hotelId) {
        HotelEntity hotelEntity = hotelRepository.findByHotelId(hotelId);

        if(hotelEntity == null){
            throw new HotelNotFoundException("Hotel Not found");
        }

        HotelResponse hotelResponse = hotelMapper.mapEntityToResponse(hotelEntity);
        return hotelResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public HotelResponse getHotelByName(String hotelName){
        HotelEntity hotelEntity = hotelRepository.findByName(hotelName);

        if(hotelEntity == null){
            throw new HotelNotFoundException("Hotel Not found");
        }

        HotelResponse hotelResponse = hotelMapper.mapEntityToResponse(hotelEntity);
        return hotelResponse;
    }
}