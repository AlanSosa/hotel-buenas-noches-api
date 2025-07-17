package com.example.buenasnochesapi.models.dto.responses;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListHotelsResponse {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Integer stars;
}
