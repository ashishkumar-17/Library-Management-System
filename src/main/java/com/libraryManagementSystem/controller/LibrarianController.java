package com.libraryManagementSystem.controller;

import com.libraryManagementSystem.dto.BookDTO;
import com.libraryManagementSystem.dto.BookItemDTO;
import com.libraryManagementSystem.dto.FineDTO;
import com.libraryManagementSystem.dto.ReservationDTO;
import com.libraryManagementSystem.model.BookItemStatus;
import com.libraryManagementSystem.service.FineService;
import com.libraryManagementSystem.service.LibrarianService;
import com.libraryManagementSystem.service.ReservationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

    private final LibrarianService librarianService;
    private final FineService fineService;
    private final ReservationService reservationService;


    @Autowired
    public LibrarianController(LibrarianService librarianService,
                               FineService fineService,
                               ReservationService reservationService){
        this.librarianService = librarianService;
        this.fineService = fineService;
        this.reservationService = reservationService;
    }

    /**
     * Manage Books
     */


    @PostMapping("/books/add-book")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(librarianService.addBook(bookDTO));
    }

    @PutMapping("/books/update-book/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId,@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(librarianService.updateBook(bookId, bookDTO));
    }

    @DeleteMapping("/books/delete-book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        librarianService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Manage Book Items (Copies of Books)
     */


    @PostMapping("/book-items/add-book-item/{bookId}")
    public ResponseEntity<BookItemDTO> addBookItem(@PathVariable Long bookId, @RequestBody BookItemDTO bookItemDTO){
        return ResponseEntity.ok(librarianService.addBookItem(bookId, bookItemDTO));
    }


    @PutMapping("/book-items/update-book-item/{bookItemId}/status")
    public ResponseEntity<BookItemDTO> updateBookItemStatus(@PathVariable Long bookItemId,@PathVariable BookItemStatus status){
        return ResponseEntity.ok(librarianService.updateBookItemStatus(bookItemId, status));
    }

    @DeleteMapping("/book-items/delete-book-item/{bookItemId}")
    public ResponseEntity<Void> deleteBookItem(@PathVariable Long bookItemId){
        librarianService.deleteBookItem(bookItemId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Issue and return Books
     */

    @PostMapping("/issue/{memberId}/{bookItemId}")
    public ResponseEntity<String> issueBook(@PathVariable Long memberId,@PathVariable Long bookItemId){
        return ResponseEntity.ok(librarianService.issueBook(memberId, bookItemId));
    }

    @PostMapping("/return/{bookItemId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookItemId){
        return ResponseEntity.ok(librarianService.returnBook(bookItemId));
    }

    /**
     * fine system
     */

    @GetMapping("/fine/calculate-fine/{memberId}")
    public ResponseEntity<FineDTO> calculateFine(@PathVariable Long memberId){
        return ResponseEntity.ok(fineService.calculateFine(memberId));
    }

    @GetMapping("/fine/pending-fine/{memberId}")
    public ResponseEntity<List<FineDTO>> getPendingFines(@PathVariable Long memberId){
        return ResponseEntity.ok(fineService.getPendingFines(memberId));
    }

    @PostMapping("/fine/pay/{fineId}")
    public ResponseEntity<FineDTO> payFine(@PathVariable Long fineId){
        return ResponseEntity.ok(fineService.payFine(fineId));
    }

    /**
     * Reservation system
     */

    @PostMapping("/reserve/reserve-book/{memberId}/{bookItemId}")
    public ResponseEntity<ReservationDTO> reserveBook(@PathVariable Long memberId,@PathVariable Long bookItemId){
        return ResponseEntity.ok(reservationService.reserveBook(memberId, bookItemId));
    }

    @DeleteMapping("/reserve/reserve-cancel/{memberId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long memberId){
        reservationService.cancelReservation(memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reserve/reservations/{bookItemId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsForBook(@PathVariable Long bookItemId){
        return ResponseEntity.ok(reservationService.getReservationsForBook(bookItemId));
    }

    @PutMapping("/reserve/fulfill/{bookItemId}")
    public ResponseEntity<Void> fulfillReservation(@PathVariable Long bookItemId){
        reservationService.fulfillReservation(bookItemId);
        return ResponseEntity.noContent().build();
    }
}