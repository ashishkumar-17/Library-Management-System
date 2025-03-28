package com.libraryManagementSystem.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String ISBN;
    private String category;
    private int publicationYear;
    private int copiesAvailable;

    @OneToMany(mappedBy = "book")
    private List<BookItem> bookItems;

    public Book() {}

    public Book (String title, String author, String ISBN, String category, int publicationYear, int copiesAvailable){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.category = category;
        this.publicationYear = publicationYear;
        this.copiesAvailable = copiesAvailable;
    }

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

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "', ISBN='" + ISBN + "'}";
    }
}
