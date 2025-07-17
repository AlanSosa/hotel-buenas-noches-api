package com.example.buenasnochesapi.exceptions.notfound;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestNotFoundException  extends RuntimeException implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1l;

    private String message;
}
