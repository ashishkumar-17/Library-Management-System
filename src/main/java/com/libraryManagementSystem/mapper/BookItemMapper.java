package com.libraryManagementSystem.mapper;

import com.libraryManagementSystem.dto.BookItemDTO;
import com.libraryManagementSystem.model.BookItem;
import com.libraryManagementSystem.repository.BookRepository;
import com.libraryManagementSystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookItemMapper {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public BookItemDTO toDTO(BookItem bookItem){
        if(bookItem == null){
            return null;
        }

        return new BookItemDTO(
                bookItem.getId(),
                bookItem.getBook() != null ? bookItem.getBook().getId() : null,
                bookItem.getStatus(),
                bookItem.getBorrowedDate(),
                bookItem.getDueDate(),
                bookItem.getBorrowedBy() != null ? bookItem.getBorrowedBy().getId() : null
        );
    }

    public BookItem toEntity(BookItemDTO bookItemDTO) {
        if (bookItemDTO == null) {
            return null;
        }

        BookItem bookItem = new BookItem(
                bookItemDTO.getId(),
                bookItemDTO.getBook() != null
                        ? bookRepository.findById(bookItemDTO.getBook()).orElse(null)
                        : null,
                bookItemDTO.getStatus()
        );

        bookItem.setBorrowedDate(bookItemDTO.getBorrowedDate());
        bookItem.setDueDate(bookItemDTO.getDueDate());

        if (bookItemDTO.getBorrowedById() != null) {
            bookItem.setBorrowedBy(
                    memberRepository.findById(bookItemDTO.getBorrowedById()).orElse(null)
            );
        }

        return bookItem;
    }

    public List<BookItemDTO> toDTOList(List<BookItem> bookItems) {
        return bookItems.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookItem> toEntityList(List<BookItemDTO> bookItemDTOs) {
        return bookItemDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
