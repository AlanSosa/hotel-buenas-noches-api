package com.example.buenasnochesapi.mappers;

public interface EntityToResponseOnlyMapper<R,E>{

    R mapEntityToResponse(E entity);
}
