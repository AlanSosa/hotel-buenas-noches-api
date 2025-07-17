package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.HotelEntity;
import com.example.buenasnochesapi.models.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository//So spring can translate all unchecked exceptions into Spring DataAccessException
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    RoomEntity findByRoomId(String roomId);

    @Query(value = "SELECT * FROM room WHERE room.room_id NOT IN " +
                    "("+
                        "SELECT room.room_id " +
                        "FROM room " +
                        "JOIN hotel ON hotel.hotel_id = :hotelId AND room.hotel_id = :hotelId " +
                        "JOIN guest_reservation_room ON guest_reservation_room.room_id = room.room_id " +
                        "JOIN reservation ON reservation.reservation_id = guest_reservation_room.reservation_id " +
                        "Where :startDate BETWEEN reservation.start_date AND reservation.end_date " +
                        "OR :endDate BETWEEN reservation.start_date AND reservation.end_date"+
                    ")" +
                    "AND room.hotel_id = :hotelId", nativeQuery = true)
    List<RoomEntity> findAvailableRoomsInHotelId(@Param("hotelId") long hotelId,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM room WHERE room.room_id NOT IN " +
            "("+
            "SELECT room.room_id " +
            "FROM room " +
            "JOIN hotel ON hotel.hotel_id = :hotelId AND room.hotel_id = :hotelId " +
            "JOIN guest_reservation_room ON guest_reservation_room.room_id = room.room_id " +
            "JOIN reservation ON reservation.reservation_id = guest_reservation_room.reservation_id " +
            "Where :startDate BETWEEN reservation.start_date AND reservation.end_date " +
            "OR :endDate BETWEEN reservation.start_date AND reservation.end_date"+
            ")" +
            "AND room.hotel_id = :hotelId AND room.room_id = :roomId",
            nativeQuery = true)
    RoomEntity findAvailableRoomInHotelId(@Param("hotelId") long hotelId,
                                                 @Param("roomId") long roomId,
                                                 @Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);

    /*RoomEntity findRoomWhereGuestHasReservationOn(@Param("roomId") long roomId,
                                                  @Param("guestId") long guestId,
                                                  @Param("startDate") Date startDate,
                                                  @Param("endDate") Date endDate);*/
}
