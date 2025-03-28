package com.libraryManagementSystem.repository;

import com.libraryManagementSystem.model.BookItem;
import com.libraryManagementSystem.model.Member;
import com.libraryManagementSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBorrowerAndDueDateBeforeAndReturnDateIsNull(Member member, LocalDate now);

    Optional<Transaction> findByBookItemAndReturnDateIsNull(BookItem bookItem);

    Transaction findByBookItem(BookItem bookItem);

    List<Transaction> findByDueDateBeforeAndReturnDateIsNull(LocalDate now);
}