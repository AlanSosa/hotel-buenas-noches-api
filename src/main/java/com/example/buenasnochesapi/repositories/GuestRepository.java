package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository//So spring can translate all unchecked exceptions into Spring DataAccessException
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    GuestEntity findByFirstName(String firstName);
    GuestEntity findByEmail(String email);
    GuestEntity findByGuestId(String guestId);
}
