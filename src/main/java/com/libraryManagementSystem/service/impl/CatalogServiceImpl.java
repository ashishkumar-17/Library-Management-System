package com.libraryManagementSystem.service.impl;

import com.libraryManagementSystem.dto.BookDTO;
import com.libraryManagementSystem.mapper.BookMapper;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.model.Catalog;
import com.libraryManagementSystem.repository.BookRepository;
import com.libraryManagementSystem.repository.CatalogRepository;
import com.libraryManagementSystem.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository,
                              BookRepository bookRepository,
                              BookMapper bookMapper) {
        this.catalogRepository = catalogRepository;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDTOList(books);
    }

    // Search books by title
    @Transactional(readOnly = true)
    public List<Catalog> searchByTitle(String title) {
        return catalogRepository.findByTitleContainingIgnoreCase(title);
    }

    // Search books by author
    @Transactional(readOnly = true)
    public List<Catalog> searchByAuthor(String author) {
        return catalogRepository.findByAuthorContainingIgnoreCase(author);
    }

    // Search books by category
    @Transactional(readOnly = true)
    public List<Catalog> searchByCategory(String category) {
        return catalogRepository.findByCategoryIgnoreCase(category);
    }
}