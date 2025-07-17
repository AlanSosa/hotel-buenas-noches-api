package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.GuestEntity;
import com.example.buenasnochesapi.models.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
