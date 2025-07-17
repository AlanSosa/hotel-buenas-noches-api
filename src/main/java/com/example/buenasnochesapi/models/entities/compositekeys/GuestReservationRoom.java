package com.example.buenasnochesapi.models.entities.compositekeys;

import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestReservationRoom implements Serializable {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    private Long guestId;
    private Long reservationId;
    private Long roomId;
}