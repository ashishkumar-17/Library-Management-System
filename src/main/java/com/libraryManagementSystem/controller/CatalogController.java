package com.libraryManagementSystem.controller;

import com.libraryManagementSystem.dto.BookDTO;
import com.libraryManagementSystem.model.Catalog;
import com.libraryManagementSystem.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(catalogService.getAllBooks());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Catalog>> searchByTitle(@PathVariable String title){
        return ResponseEntity.ok(catalogService.searchByTitle(title));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Catalog>> searchByAuthor(@PathVariable String author){
        return ResponseEntity.ok(catalogService.searchByAuthor(author));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Catalog>> searchByCategory(@PathVariable String category){
        return ResponseEntity.ok(catalogService.searchByCategory(category));
    }
}