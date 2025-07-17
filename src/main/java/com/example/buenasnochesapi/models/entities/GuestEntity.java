package com.example.buenasnochesapi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="guest")
public class GuestEntity implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialversionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private long id;

    @Column(name= "guest_uuid", nullable = false)
    private String guestId;

    @Column(name = "guest_first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "guest_last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "guest_email", nullable = false, length = 100)
    private String email;

    @Column(name = "guest_active", nullable = false)
    private Boolean guestActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "guest_type_id")
    @JsonIgnoreProperties
    private GuestTypeEntity guestType;
}