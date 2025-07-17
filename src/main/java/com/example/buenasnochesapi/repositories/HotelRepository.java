package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//So spring can translate all unchecked exceptions into Spring DataAccessException
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    HotelEntity findByName(String name);
    HotelEntity findByHotelId(String uuid);
}
