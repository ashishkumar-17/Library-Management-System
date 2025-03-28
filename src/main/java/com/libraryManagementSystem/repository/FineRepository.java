package com.libraryManagementSystem.repository;

import com.libraryManagementSystem.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {

    List<Fine> findByMemberIdAndPaidStatusFalse(Long memberId);
}
