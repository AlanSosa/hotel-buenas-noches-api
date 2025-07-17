package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.GuestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//So spring can translate all unchecked exceptions into Spring DataAccessException
public interface GuestTypeRepository extends JpaRepository<GuestTypeEntity, Integer> {
    GuestTypeEntity findByName(String name);
}