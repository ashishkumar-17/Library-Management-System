package com.libraryManagementSystem.service.impl;

import com.libraryManagementSystem.dto.ReservationDTO;
import com.libraryManagementSystem.mapper.ReservationMapper;
import com.libraryManagementSystem.model.*;
import com.libraryManagementSystem.repository.BookItemRepository;
import com.libraryManagementSystem.repository.MemberRepository;
import com.libraryManagementSystem.repository.ReservationRepository;
import com.libraryManagementSystem.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookItemRepository bookItemRepository;
    private final MemberRepository memberRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  BookItemRepository bookItemRepository,
                                  MemberRepository memberRepository,
                                  ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.bookItemRepository = bookItemRepository;
        this.memberRepository = memberRepository;
        this.reservationMapper = reservationMapper;
    }

    // Reserve a book if it is not available.
    @Transactional
    public ReservationDTO reserveBook(Long memberId, Long bookItemId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
        BookItem bookItem = bookItemRepository.findById(bookItemId)
                .orElseThrow(() -> new EntityNotFoundException("Book item not found with id: " + bookItemId));

        if (bookItem.getStatus() == BookItemStatus.AVAILABLE) {
            throw new RuntimeException("Book is available, no need for reservation.");
        }

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setBookItem(bookItem);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setReservationDate(LocalDate.now());

        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    // Cancel a reservation.
    @Transactional
    public void cancelReservation(Long memberId) {
        List<Reservation> reservations = reservationRepository.findByMemberId(memberId);

        if (reservations.isEmpty()) {
            throw new RuntimeException("No reservations found for this member.");
        }

        for (Reservation reservation : reservations) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(reservation);
        }
    }

    // Check reservation status of a book.
    @Transactional(readOnly = true)
    public List<ReservationDTO> getReservationsForBook(Long bookItemId) {
        List<Reservation> reservationList = reservationRepository.findByBookItemId(bookItemId);
        return reservationMapper.toDTOList(reservationList);
    }

    // Fulfill reservations when a book is returned.
    @Transactional
    public void fulfillReservation(Long bookItemId) {
        List<Reservation> reservations = reservationRepository.findByBookItemId(bookItemId);

        if (!reservations.isEmpty()) {
            Reservation nextReservation = reservations.getFirst(); // Get first in queue
            nextReservation.setStatus(ReservationStatus.FULFILLED);
            reservationRepository.save(nextReservation);

            // Assign the book to the member
            BookItem bookItem = nextReservation.getBookItem();
            bookItem.setStatus(BookItemStatus.BORROWED);
            bookItemRepository.save(bookItem);
        }
    }
}
