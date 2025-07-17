package com.example.buenasnochesapi.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity( name = "reservationLog")
public class ReservationLogEntity implements Serializable {

    private static final long serialversionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;

    @Column(name="hotel_name", nullable = false)
    private String hotelName;

    @Column(name="room_name", nullable = false)
    private String roomName;

    @Column(name="guest_first_name", nullable = false)
    private String guestFirstName;

    @Column(name="guest_last_name", nullable = false)
    private String guestLastName;

    @Column(name="guest_email", nullable = false)
    private String guestEmail;

    @Column(name="start_date", nullable = false)
    private Date startDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;
}
