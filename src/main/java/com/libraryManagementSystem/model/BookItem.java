package com.libraryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookItem {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookItemStatus status; // Available, borrowed, reserved

    private LocalDate borrowedDate;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "borrowed_by")
    private Member borrowedBy;

    public BookItem() {}


    public BookItem(Long id, Book book, BookItemStatus status){
        this.id = id;
        this.book = book;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public Member getBorrowedBy() {
        return borrowedBy;
    }
    public void setBorrowedBy(Member borrowedBy) {
        this.borrowedBy = borrowedBy;
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

    @Override
    public String toString() {
        return "BookItem{barcode='" + id + "', status='" + status + "'}";
    }
}
