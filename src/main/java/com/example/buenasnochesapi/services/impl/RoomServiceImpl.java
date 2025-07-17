package com.example.buenasnochesapi.services.impl;

import com.example.buenasnochesapi.exceptions.notfound.HotelNotFoundException;
import com.example.buenasnochesapi.exceptions.notfound.RoomNotFoundException;
import com.example.buenasnochesapi.exceptions.notfound.RoomTypeNotFoundException;
import com.example.buenasnochesapi.mappers.impl.HotelMapper;
import com.example.buenasnochesapi.mappers.impl.RoomMapper;
import com.example.buenasnochesapi.models.dto.requests.RoomRequest;
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
import com.example.buenasnochesapi.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements IRoomService {

    HotelRepository hotelRepository;
    RoomRepository roomRepository;
    RoomTypeRepository roomTypeRepository;

    RoomMapper roomMapper;
    HotelMapper hotelMapper;


    @Override
    @Transactional
    public HotelResponse createRoomByHotelId(String hotelId, RoomRequest roomRequest) {
        HotelEntity hotel = hotelRepository.findByHotelId(hotelId);
        if(hotel == null){
            throw new HotelNotFoundException("Hotel not found");
        }

        RoomTypeEntity roomType = roomTypeRepository.findByName(roomRequest.getRoomType());
        if(roomType == null){
            throw new RoomTypeNotFoundException("Room Type not found");
        }

        RoomEntity roomToSave = roomMapper.mapRequestToEntity(roomRequest);
        roomToSave.setRoomId(UUID.randomUUID().toString());
        roomToSave.setRoomType(roomType);
        roomToSave.setHotel(hotel);

        RoomEntity savedRoom = roomRepository.save(roomToSave);
        hotel.setRooms(Stream.of(savedRoom).collect(Collectors.toList()));

        HotelResponse hotelResponse = hotelMapper.mapEntityToResponse(hotel);
        return hotelResponse;
    }

    @Override
    @Transactional
    public RoomResponse updateRoomByRoomId(String roomId, RoomRequest updateRoomRequest) {
        RoomEntity roomEntity = roomRepository.findByRoomId(roomId);

        if(roomEntity == null){
            throw new RoomNotFoundException("Room not found");
        }

        if(updateRoomRequest.getRoomType() != null || !updateRoomRequest.getRoomType().isEmpty()){
            RoomTypeEntity roomType = roomTypeRepository.findByName(updateRoomRequest.getRoomType());
            if(roomType == null){
                throw new RoomTypeNotFoundException("Room Type Not Found");
            }

            roomEntity.setRoomType(roomType);
        }

        if(updateRoomRequest.getDescription() != null || !updateRoomRequest.getDescription().isEmpty()){
            roomEntity.setDescription( updateRoomRequest.getDescription());
        }
        if(updateRoomRequest.getFloor() != null || updateRoomRequest.getFloor() > - 1){
            roomEntity.setFloor(updateRoomRequest.getFloor());
        }
        if(updateRoomRequest.getMaxGuests() != null || updateRoomRequest.getMaxGuests() > -1){
            roomEntity.setMaxGuests(updateRoomRequest.getMaxGuests());
        }
        if(updateRoomRequest.getName() != null || !updateRoomRequest.getName().isEmpty()){
            roomEntity.setName(updateRoomRequest.getName());
        }

        RoomEntity roomSaved = roomRepository.save(roomEntity);

        RoomResponse roomReponse = roomMapper.mapEntityToResponse(roomSaved);

        return roomReponse;
    }

    @Override
    @Transactional
    public IResponseMessage removeRoomByRoomId(String roomId) {
        RoomEntity roomToDelete = roomRepository.findByRoomId(roomId);
        if(roomToDelete == null){
            throw new HotelNotFoundException("Hotel not found");
        }

        RoomResponse roomToReturn = roomMapper.mapEntityToResponse(roomToDelete);
        roomRepository.delete(roomToDelete);
        IResponseMessage response = new ResponseMessageAndModel("Success", roomToReturn);
        return response;
    }

    @Override
    public RoomResponse findRoomByRoomId(String roomId) {
        RoomEntity room = roomRepository.findByRoomId(roomId);

        if(room == null){
            throw new RoomNotFoundException("Room not found");
        }

        RoomResponse roomToReturn = roomMapper.mapEntityToResponse(room);
        return roomToReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> findRoomAvailableByHotelId(String hotelId, Date startDate, Date endDate) {
        HotelEntity hotelEntity = hotelRepository.findByHotelId(hotelId);

        if(hotelEntity == null){
            throw new HotelNotFoundException("Hotel Not found Exception");
        }

        List<RoomEntity> availableRooms = roomRepository.findAvailableRoomsInHotelId(hotelEntity.getId(), startDate, endDate);

        if( availableRooms.isEmpty() || availableRooms == null){
            throw new RoomNotFoundException("No available rooms were found");
        }

        List<RoomResponse> availableRoomsResponse = availableRooms.stream().map(
                roomEntity -> roomMapper.mapEntityToResponse(roomEntity)
        ).collect(Collectors.toList());

        return availableRoomsResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public RoomResponse findRoomAvailableByHotelIdAndRoomId(String hotelId, String roomId, Date startDate, Date endDate) {
        RoomEntity roomEntity = roomRepository.findByRoomId(roomId);

        if(roomEntity == null){
            throw new RoomNotFoundException("Room Not Found Exception");
        }

        HotelEntity hotelEntity = hotelRepository.findByHotelId(hotelId);

        if(hotelEntity == null){
            throw new HotelNotFoundException("Hotel Not found Exception");
        }

        RoomEntity availableRoom =  roomRepository.findAvailableRoomInHotelId(hotelEntity.getId(), roomEntity.getId(), startDate, endDate);

        if(availableRoom == null){
            throw new RoomNotFoundException("Room is not Available");
        }

        RoomResponse roomResponse = roomMapper.mapEntityToResponse(availableRoom);

        return roomResponse;
    }
}
