package com.libraryManagementSystem.repository;

import com.libraryManagementSystem.model.BookItem;
import com.libraryManagementSystem.model.BookItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {

    List<BookItem> findByStatus(BookItemStatus bookItemStatus);

    boolean existsByBookId(Long id);

    int countByBookIdAndStatus(Long id, BookItemStatus status);

    List<BookItem> findByBorrowedById(Long memberId);
}