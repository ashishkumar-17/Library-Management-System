package com.libraryManagementSystem.mapper;

import com.libraryManagementSystem.dto.BookDTO;
import com.libraryManagementSystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book){
        if(book == null){
            return null;
        }

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getISBN(),
                book.getCategory(),
                book.getPublicationYear(),
                book.getCopiesAvailable()
        );
    }

    public Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        Book book = new Book(
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getISBN(),
                bookDTO.getCategory(),
                bookDTO.getPublicationYear(),
                bookDTO.getCopiesAvailable()
        );
        book.setId(bookDTO.getId());
        return book;
    }

    public List<BookDTO> toDTOList(List<Book> books) {
        return books.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Book> toEntityList(List<BookDTO> bookDTOs) {
        return bookDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
