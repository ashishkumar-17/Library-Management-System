package com.libraryManagementSystem.repository;

import com.libraryManagementSystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findByBookItemId(Long bookItemId);

    List<Reservation> findByMemberId(Long memberId);
}
