package com.example.buenasnochesapi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roomType")
public class RoomTypeEntity implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialversionUID = 1l;

    //Id column in this table is SERIAL. That works as autoincrement,
    //and also Data type is smaller than LONG
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Integer id;

    @Column(name = "room_type_name", nullable = false)
    private String name;

    @OneToMany( mappedBy = "roomType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<RoomEntity> rooms;
}
