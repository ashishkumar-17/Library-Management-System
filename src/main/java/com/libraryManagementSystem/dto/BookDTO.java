package com.libraryManagementSystem.dto;

import java.io.Serializable;

public class BookDTO implements Serializable {
    private Long id;
    private String title;
    private String author;
    private String ISBN;
    private String category;
    private int publicationYear;
    private int copiesAvailable;

    public BookDTO() {}

    public BookDTO(Long id, String title, String author, String ISBN,
                   String category, int publicationYear, int copiesAvailable){
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.category = category;
        this.publicationYear = publicationYear;
        this.copiesAvailable = copiesAvailable;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
}