package com.example.buenasnochesapi.models.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
public class ReservationEntity implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialversionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;

    @Column(name= "reservation_uuid", nullable = false)
    private String reservationId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name="check_in", nullable = true)
    private Date checkIn;

    @Column(name="check_out", nullable = true)
    private Date checkOut;

    /*@OneToMany(mappedBy = "reservation" , fetch = FetchType.LAZY)
    private List<RoomEntity> rooms;*/
}