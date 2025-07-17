package com.example.buenasnochesapi.mappers;

public interface Mapper <T,S,E>{
    E mapRequestToEntity(T request);
    S mapEntityToResponse(E entity);
}
