package com.libraryManagementSystem.mapper;

import com.libraryManagementSystem.dto.ReservationDTO;
import com.libraryManagementSystem.model.BookItem;
import com.libraryManagementSystem.model.Member;
import com.libraryManagementSystem.model.Reservation;
import com.libraryManagementSystem.repository.BookItemRepository;
import com.libraryManagementSystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    @Autowired
    private BookItemRepository bookItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    public ReservationDTO toDTO(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        return new ReservationDTO(
                reservation.getId(),
                reservation.getBookItem() != null ? reservation.getBookItem().getId() : null,
                reservation.getMember() != null ? reservation.getMember().getId() : null,
                reservation.getReservationDate(),
                reservation.getStatus()
        );
    }

    public Reservation toEntity(ReservationDTO reservationDTO) {
        if (reservationDTO == null) {
            return null;
        }

        BookItem bookItem = reservationDTO.getBookItemId() != null
                ? bookItemRepository.findById(reservationDTO.getBookItemId()).orElse(null)
                : null;

        Member member = reservationDTO.getMemberId() != null
                ? memberRepository.findById(reservationDTO.getMemberId()).orElse(null)
                : null;

        Reservation reservation = new Reservation(
                member,
                bookItem,
                reservationDTO.getReservationDate(),
                reservationDTO.getStatus()
        );

        reservation.setId(reservationDTO.getId());

        return reservation;
    }

    public List<ReservationDTO> toDTOList(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Reservation> toEntityList(List<ReservationDTO> reservationDTOs) {
        return reservationDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
