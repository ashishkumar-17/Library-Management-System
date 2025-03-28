package com.libraryManagementSystem.service;

import com.libraryManagementSystem.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    public ReservationDTO reserveBook(Long memberId, Long bookItemId);
    public void cancelReservation(Long memberId);
    public List<ReservationDTO> getReservationsForBook(Long bookItemId);
    public void fulfillReservation(Long bookItemId);
}