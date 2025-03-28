package com.libraryManagementSystem.service;

import com.libraryManagementSystem.dto.BookDTO;
import com.libraryManagementSystem.model.Catalog;

import java.util.List;

public interface CatalogService {
    public List<BookDTO> getAllBooks();
    public List<Catalog> searchByTitle(String title);
    public List<Catalog> searchByAuthor(String author);
    public List<Catalog> searchByCategory(String category);
}