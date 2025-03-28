package com.libraryManagementSystem.service;

import com.libraryManagementSystem.dto.BookDTO;
import com.libraryManagementSystem.dto.BookItemDTO;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.model.BookItemStatus;

public interface LibrarianService {
    public BookDTO addBook(BookDTO bookDTO);
    public BookDTO updateBook(Long bookId, BookDTO bookDTO);
    public void deleteBook(Long bookId);
    public BookItemDTO addBookItem(Long bookId, BookItemDTO bookItemDTO);
    public BookItemDTO updateBookItemStatus(Long bookItemId, BookItemStatus status);
    public void deleteBookItem(Long bookItemId);
    public String issueBook(Long memberId, Long bookItemId);
    public String returnBook(Long bookItemId);
}