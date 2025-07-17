package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer> {

    public RoomTypeEntity findByName(String name);
}
