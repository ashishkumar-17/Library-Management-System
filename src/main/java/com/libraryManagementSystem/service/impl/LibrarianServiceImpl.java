package com.libraryManagementSystem.service.impl;


import com.libraryManagementSystem.dto.BookDTO;
import com.libraryManagementSystem.dto.BookItemDTO;
import com.libraryManagementSystem.mapper.BookItemMapper;
import com.libraryManagementSystem.mapper.BookMapper;
import com.libraryManagementSystem.model.*;
import com.libraryManagementSystem.repository.BookItemRepository;
import com.libraryManagementSystem.repository.BookRepository;
import com.libraryManagementSystem.repository.MemberRepository;
import com.libraryManagementSystem.repository.TransactionRepository;
import com.libraryManagementSystem.service.LibrarianService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    private final BookRepository bookRepository;
    private final BookItemRepository bookItemRepository;
    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;
    private final BookMapper bookMapper;
    private final BookItemMapper bookItemMapper;

    @Autowired
    public LibrarianServiceImpl(BookRepository bookRepository,
                                BookItemRepository bookItemRepository,
                                MemberRepository memberRepository,
                                TransactionRepository transactionRepository,
                                BookMapper bookMapper,
                                BookItemMapper bookItemMapper){
        this.bookRepository = bookRepository;
        this.bookItemRepository = bookItemRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
        this.bookMapper = bookMapper;
        this.bookItemMapper = bookItemMapper;
    }

    // Add a new book to the library
    @Transactional
    public BookDTO addBook(BookDTO bookDTO){
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }


    // Update book in library
    @Transactional
    public BookDTO updateBook(Long bookId, BookDTO bookDTO){
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setISBN(bookDTO.getISBN());
        existingBook.setCategory(bookDTO.getCategory());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());
        existingBook.setCopiesAvailable(bookDTO.getCopiesAvailable());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    // Delete book from library
    @Transactional
    public void deleteBook(Long bookId) {
        if (bookItemRepository.existsByBookId(bookId)) {
            throw new RuntimeException("Cannot delete book. Copies exist.");
        }
        bookRepository.deleteById(bookId);
    }

    // Add book item in library
    @Transactional
    public BookItemDTO addBookItem(Long bookId, BookItemDTO bookItemDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));

        BookItem bookItem = bookItemMapper.toEntity(bookItemDTO);
        bookItem.setBook(book);
        bookItem.setStatus(BookItemStatus.AVAILABLE);

        return bookItemMapper.toDTO(bookItemRepository.save(bookItem));
    }

    // Update book item status
    @Transactional
    public BookItemDTO updateBookItemStatus(Long bookItemId, BookItemStatus status){
        BookItem bookItem = bookItemRepository.findById(bookItemId)
                .orElseThrow(() -> new EntityNotFoundException("Book Item not found with id: " + bookItemId));

        bookItem.setStatus(status);
        return bookItemMapper.toDTO(bookItemRepository.save(bookItem));
    }

    // Delete book item
    @Transactional
    public void deleteBookItem(Long bookItemId){
        BookItem bookItem = bookItemRepository.findById(bookItemId)
                .orElseThrow(() -> new EntityNotFoundException("Book Item not found with id: " + bookItemId));

        bookItemRepository.delete(bookItem);
    }

    // Issue a book to a member
    @Transactional
    public String issueBook(Long memberId, Long bookItemId){
        Optional<BookItem> bookItemOptional = bookItemRepository.findById(bookItemId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if(bookItemOptional.isEmpty()) return "Book Item not found";
        if(memberOptional.isEmpty()) return "Member not found";

        BookItem bookItem = bookItemOptional.get();
        if(!bookItem.getStatus().equals(BookItemStatus.AVAILABLE)){
            return "Book is not available";
        }

        bookItem.setStatus(BookItemStatus.BORROWED);
        bookItem.setBorrowedBy(memberOptional.get());
        bookItem.setBorrowedDate(LocalDate.now());
        bookItem.setDueDate(LocalDate.now().plusDays(14));
        bookItemRepository.save(bookItem);


        Transaction transaction = new Transaction();
        transaction.setBookItem(bookItem);
        transaction.setBorrower(memberOptional.get());
        transaction.setIssueDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusDays(14));
        transactionRepository.save(transaction);

        return "Book issued Successfully.";
    }

    // Return a book and update transaction
    @Transactional
    public String returnBook(Long bookItemId){
        Optional<BookItem> bookItemOptional = bookItemRepository.findById(bookItemId);

        if(bookItemOptional.isEmpty()) return "Book item not found";

        BookItem bookItem = bookItemOptional.get();
        if(!bookItem.getStatus().equals(BookItemStatus.BORROWED)){
            return "Book is not borrowed";
        }

        Optional<Transaction> transactionOptional = transactionRepository.findByBookItemAndReturnDateIsNull(bookItem);

        if(transactionOptional.isEmpty()){
            return "No active transaction found.";
        }

        Transaction transaction = transactionOptional.get();

        transaction.setReturnDate(LocalDate.now());
        transactionRepository.save(transaction);

        bookItem.setStatus(BookItemStatus.AVAILABLE);
        bookItem.setBorrowedBy(null);
        bookItem.setBorrowedDate(null);
        bookItem.setDueDate(null);
        bookItemRepository.save(bookItem);

        return "Book returned successfully.";
    }
}