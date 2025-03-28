package com.libraryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bookItem_id")
    private BookItem bookItem;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public Reservation() {}

    public Reservation(Member member, BookItem bookItem, LocalDate reservationDate, ReservationStatus status) {
        this.member = member;
        this.bookItem = bookItem;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public BookItem getBookItem() {
        return bookItem;
    }
    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", bookItem=" + bookItem +
                ", member=" + member +
                ", reservationDate=" + reservationDate +
                ", status=" + status +
                '}';
    }
}
