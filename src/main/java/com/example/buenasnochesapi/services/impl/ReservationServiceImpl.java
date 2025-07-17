package com.example.buenasnochesapi.services.impl;

import com.example.buenasnochesapi.exceptions.notfound.HotelNotFoundException;
import com.example.buenasnochesapi.exceptions.notfound.RoomNotFoundException;
import com.example.buenasnochesapi.models.dto.requests.CheckInOutRequest;
import com.example.buenasnochesapi.models.dto.requests.CreateReservationRequest;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.models.dto.responses.status.ResponseMessage;
import com.example.buenasnochesapi.models.entities.*;
import com.example.buenasnochesapi.repositories.*;
import com.example.buenasnochesapi.services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private GuestTypeRepository guestTypeRepository;
    private ReservationRepository reservationRepository;
    private GuestRoomReservationRepository guestRoomReservationRepository;
    private ReservationLogRepository reservationLogRepository;

    @Override
    public IResponseMessage createReservation(CreateReservationRequest reservationRequest) {

        RoomEntity roomEntity = roomRepository.findByRoomId(reservationRequest.getRoomId());

        if(roomEntity == null){
            throw new RoomNotFoundException("Room not found");
        }

        HotelEntity hotelEntity = hotelRepository.findById(roomEntity.getHotel().getId()).orElse(null);

        if(hotelEntity == null){
            throw new HotelNotFoundException("Hotel doesn't exists");
        }

        //Validate if room is available
        RoomEntity isRoomAvailable = roomRepository.findAvailableRoomInHotelId(hotelEntity.getId(),
                roomEntity.getId(), reservationRequest.getStartDate(), reservationRequest.getEndDate());

        if(isRoomAvailable == null){
            throw new RoomNotFoundException("Room is not available");
        }

        //Validate if Guest Count is equal or less than Room max_guests
        if(reservationRequest.getGuests().size() > isRoomAvailable.getMaxGuests()){
            //TODO: Create custom exception for this.
            throw new RuntimeException("Max guest error");
        }

        //Check if guest exist in DB
        //IF guest exists, validate if they're VIP
        reservationRequest.getGuests().stream().forEach( guest -> {
            GuestEntity isGuestFound = guestRepository.findByEmail(guest.getEmail());

            //If guest doesn't exists create it and saved it to DB
            if(isGuestFound == null){
                isGuestFound = new GuestEntity();
                isGuestFound.setGuestId(UUID.randomUUID().toString());
                isGuestFound.setGuestActive(true);
                isGuestFound.setEmail(guest.getEmail());
                isGuestFound.setFirstName(guest.getFirstName());
                isGuestFound.setLastName(guest.getLastName());

                //TODO: Change "normal" to an ENUM var
                GuestTypeEntity guestType = guestTypeRepository.findByName("NORMAL");
                isGuestFound.setGuestType(guestType);

                GuestEntity savedGuest = guestRepository.save(isGuestFound);
            }

            //Create reservation
            ReservationEntity reservation = new ReservationEntity();
            reservation.setReservationId(UUID.randomUUID().toString());
            reservation.setStartDate(reservationRequest.getStartDate());
            reservation.setEndDate(reservationRequest.getEndDate());
            //reservation.setCheckOut(null);
            //reservation.setCheckIn(null);
            ReservationEntity savedReservation = reservationRepository.save(reservation);

            //Create Guest_Reservation_Room
            GuestReservationRoomEntity gRoomReservation = new GuestReservationRoomEntity();
            gRoomReservation.setGuestId(isGuestFound.getId());
            gRoomReservation.setRoomId(isRoomAvailable.getId());
            gRoomReservation.setReservationId(savedReservation.getId());
            guestRoomReservationRepository.save(gRoomReservation);

            //Save in Reservation log
            ReservationLogEntity reservationLog = new ReservationLogEntity();
            reservationLog.setEndDate(savedReservation.getEndDate());
            reservationLog.setStartDate(savedReservation.getStartDate());
            reservationLog.setGuestEmail(isGuestFound.getEmail());
            reservationLog.setGuestFirstName(isGuestFound.getFirstName());
            reservationLog.setGuestLastName(isGuestFound.getLastName());
            reservationLog.setHotelName(hotelEntity.getName());
            reservationLog.setRoomName(isRoomAvailable.getName());
            reservationLogRepository.save(reservationLog);

            // IF reservation count is 5 change GUEST VIP Status
            if(reservationLogRepository.countReservationFromGuestEmail(isGuestFound.getEmail()) >= 5){
                //TODO: Change "VIP" to an ENUM var
                GuestTypeEntity guestType = guestTypeRepository.findByName("VIP");
                isGuestFound.setGuestType(guestType);
                guestRepository.save(isGuestFound);
            }
        });

        IResponseMessage message = new ResponseMessage("Success");
        return message;
    }

    @Override
    public IResponseMessage checkInReservation(CheckInOutRequest checkRequest, String roomId) {
        RoomEntity roomEntity = roomRepository.findByRoomId(roomId);
        if(roomEntity == null){
            throw new RoomNotFoundException("Room Not Found");
        }




        return null;
    }
}
