package com.example.buenasnochesapi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hotel")
public class HotelEntity implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialversionUID = 1l;

    //Id column in this table is BIG SERIAL. That works as autoincrement,
    //and also Data type is bigger than INTEGER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private long id;

    @Column(name="hotel_uuid", nullable = false)
    private String hotelId;

    @Column(name= "hotel_name", nullable = false)
    private String name;

    @Column(name= "hotel_description", nullable = false)
    private String description;

    @Column(name = "stars", nullable = false)
    private Integer stars;

    @OneToMany( mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<RoomEntity> rooms;
}
