package com.example.buenasnochesapi.repositories;

import com.example.buenasnochesapi.models.entities.ReservationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository//So spring can translate all unchecked exceptions into Spring DataAccessException
public interface ReservationLogRepository extends JpaRepository<ReservationLogEntity, Long> {

    @Query(value="SELECT COUNT(*) FROM reservation_log where guest_email = :guestEmail", nativeQuery = true)
    Long countReservationFromGuestEmail(@Param("guestEmail") String guestEmail);
}
