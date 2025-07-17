package com.example.buenasnochesapi.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "guestType")
public class GuestTypeEntity implements Serializable {

    private static final long serialversionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_type_id")
    private Integer id;

    @Column(name= "guest_type_name", nullable = false)
    private String name;

    //@OneToOne( mappedBy = "guestType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany( mappedBy = "guestType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GuestEntity> guests;
}
