package com.example.buenasnochesapi.models.entities;

import com.example.buenasnochesapi.models.entities.compositekeys.GuestReservationRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(GuestReservationRoom.class)
@Table(name = "guest_reservation_room")
public class GuestReservationRoomEntity {

    @Id
    @Column(name="guest_id")
    private Long guestId;

    @Id
    @Column(name = "reservation_id")
    private Long reservationId;

    @Id
    @Column(name="room_id")
    private Long roomId;


}
