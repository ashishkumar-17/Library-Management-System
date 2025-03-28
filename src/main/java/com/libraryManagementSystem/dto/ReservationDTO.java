package com.libraryManagementSystem.dto;

import com.libraryManagementSystem.model.ReservationStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class ReservationDTO implements Serializable {
    private Long id;
    private Long bookItemId;
    private Long memberId;
    private LocalDate reservationDate;
    private ReservationStatus status;


    public ReservationDTO() {}

    public ReservationDTO(Long id, Long bookItemId,
                          Long memberId, LocalDate reservationDate, ReservationStatus status) {
        this.id = id;
        this.bookItemId = bookItemId;
        this.memberId = memberId;
        this.reservationDate = reservationDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookItemId() {
        return bookItemId;
    }
    public void setBookItemId(Long bookItemId) {
        this.bookItemId = bookItemId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
}
