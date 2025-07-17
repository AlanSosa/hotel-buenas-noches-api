package com.example.buenasnochesapi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "room")
public class RoomEntity implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialversionUID = 1l;

    //Id column in this table is BIG SERIAL. That works as autoincrement,
    //and also Data type is bigger than INTEGER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name="room_uuid", nullable = false)
    private String roomId;

    @Column(name = "room_name", nullable = false, length = 50)
    private String name;

    @Column(name = "room_description", nullable = false, length = 255)
    private String description;

    @Column(name = "room_floor", nullable = false)
    private Integer floor;

    @Column(name = "max_guests", nullable = false)
    private Integer maxGuests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "hotel_id")
    @JsonIgnoreProperties
    private HotelEntity hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "room_type_id")
    @JsonIgnoreProperties
    private RoomTypeEntity roomType;
}
