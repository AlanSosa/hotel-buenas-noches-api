package com.example.buenasnochesapi.models.dto.requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelRequest implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "name field is required")
    @NotNull(message = "name field is required")
    private String name;

    @NotEmpty(message = "description field is required")
    @NotNull(message = "description field is required")
    private String description;

    @NotEmpty(message = "stars field is required")
    @NotNull(message = "stars field is required")
    private Integer stars;
}