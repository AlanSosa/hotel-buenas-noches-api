package com.example.buenasnochesapi.exceptions.badrequest;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoomFieldEmptyException extends RuntimeException implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1l;

    private String message;
}
