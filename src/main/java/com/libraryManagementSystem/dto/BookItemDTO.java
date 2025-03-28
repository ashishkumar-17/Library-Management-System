package com.libraryManagementSystem.dto;

import com.libraryManagementSystem.model.BookItemStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class BookItemDTO implements Serializable {

    private Long id;
    private Long bookId;
    private BookItemStatus status;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private Long borrowedById;

    public BookItemDTO() {}

    public BookItemDTO(Long id, Long bookId, BookItemStatus status,
                       LocalDate borrowedDate, LocalDate dueDate, Long borrowedById) {
        this.id = id;
        this.bookId = bookId;
        this.status = status;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.borrowedById = borrowedById;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getBook() {
        return bookId;
    }
    public void setBook(Long bookId) {
        this.bookId = bookId;
    }

    public BookItemStatus getStatus() {
        return status;
    }
    public void setStatus(BookItemStatus status) {
        this.status = status;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }
    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Long getBorrowedById() {
        return borrowedById;
    }
    public void setBorrowedById(Long borrowedById) {
        this.borrowedById = borrowedById;
    }
}
