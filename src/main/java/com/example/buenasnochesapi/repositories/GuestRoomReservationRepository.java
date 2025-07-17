package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.GuestReservationRoomEntity;
import com.example.buenasnochesapi.models.entities.compositekeys.GuestReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRoomReservationRepository extends JpaRepository<GuestReservationRoomEntity, GuestReservationRoom> {
}
